/* vue */
import { createApp } from "vue";
import App from "@/App.vue";

/* router */
import router from "@/router/router";

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
