import { createApp } from "vue";
import App from "@/App.vue";
import router from "@/router";
import store from "@/store";
import rolesDirective from "@/plugins/portal-roles";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { faB } from "@fortawesome/free-solid-svg-icons";

library.add(fas);
library.add(faB);

createApp(App)
  .directive("roles", rolesDirective)
  .component("font-awesome-icon", FontAwesomeIcon)
  .use(router)
  .use(store)
  .mount("#app");
