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
import "@/assets/sass/main.scss";

/* for global scope of <font-awesome-icon>...</font-awesome-icon> */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

/* import the fontawesome core */
import { library } from "@fortawesome/fontawesome-svg-core";

/* add some free styles */
import { faTwitter } from "@fortawesome/free-brands-svg-icons";
import { faUserSecret } from "@fortawesome/free-solid-svg-icons";

library.add(faUserSecret);
library.add(faTwitter);

/* import specific icons */
import { faHome } from "@fortawesome/free-solid-svg-icons/faHome";
import { faBars } from "@fortawesome/free-solid-svg-icons/faBars";
import { faSearch } from "@fortawesome/free-solid-svg-icons/faSearch";
import { faUser } from "@fortawesome/free-solid-svg-icons/faUser";
import { faUserPlus } from "@fortawesome/free-solid-svg-icons/faUserPlus";
import { faSignInAlt } from "@fortawesome/free-solid-svg-icons/faSignInAlt";
import { faSignOutAlt } from "@fortawesome/free-solid-svg-icons/faSignOutAlt";

library.add(faHome);
library.add(faBars);
library.add(faSearch);
library.add(faUser);
library.add(faUserPlus);
library.add(faSignInAlt);
library.add(faSignOutAlt);

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
