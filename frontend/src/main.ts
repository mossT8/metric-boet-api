import jwt_decode from "jwt-decode";
/* vue */
import { createApp } from "vue";
import App from "@/App.vue";

/* router */
import router from "@/router/router";

/* constants */
import { LOCAL_STOAGE_USER_KEY } from "@/services/auth.service";

/* store */
import store from "@/store";

import rolesDirective from "@/plugins/portal-roles";
import clickOutsideDirective from "@/plugins/click-outside";

/* styling */
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "@/assets/sass/_index.scss";

/* icons */
import "@/plugins/font-awesome";

/* for global scope of <font-awesome-icon>...</font-awesome-icon> */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { useAuthModuleFeatures } from "./composables/store/useAuthModule";
import { User } from "./types/user/user";

/* declare app */
const app = createApp(App)
  .directive("roles", rolesDirective)
  .directive("click-outside", clickOutsideDirective)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(router)
  .use(store);

if (process.env.NODE_ENV === "development") {
  app.config.performance = true;
}

/* mount app*/
app.mount("#app");

// Define the session timeout period (in milliseconds)
const SESSION_TIMEOUT = 30 * 60 * 1000; // 30 minutes

let sessionTimer: ReturnType<typeof setTimeout> | null = null;

function resetSessionTimer() {
  if (sessionTimer) {
    clearTimeout(sessionTimer);
  }
  sessionTimer = setTimeout(logoutUser, SESSION_TIMEOUT);
}

// Sample function to check if the JWT token has expired
function isTokenExpired(token: string): boolean {
  try {
    const decodedToken: { exp: number } = jwt_decode(token);
    const expirationDate = new Date(decodedToken.exp * 1000);
    const currentDate = new Date();
    return expirationDate < currentDate;
  } catch (error) {
    // Handle invalid token or other errors
    return true;
  }
}

const { logout } = useAuthModuleFeatures();

const userStr = localStorage.getItem(LOCAL_STOAGE_USER_KEY);
const user: User | null = userStr ? JSON.parse(userStr) : null;

const token = user ? user["accessToken"] : "";

const isExpired = isTokenExpired(token);
console.log("Checking Token expiry...");

if (isExpired) {
  // Handle token expiration, e.g., log the user out or request a new token
  console.log("Token has expired");
  logout();
} else {
  // Token is still valid, proceed with application setup
  console.log("Token is valid");
}

function logoutUser() {
  // Clear the user's session data (e.g., JWT token) from local storage
  logout();

  // Redirect the user to the login page or display a message to re-login
  // Example: router.push('/login');
}

// Add event listeners to track user activity
document.addEventListener("mousemove", resetSessionTimer);
document.addEventListener("mousedown", resetSessionTimer);
document.addEventListener("keydown", resetSessionTimer);

// Start the session timer when the app is initialized
resetSessionTimer();
