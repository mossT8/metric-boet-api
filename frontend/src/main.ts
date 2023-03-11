import { createApp } from "vue";
import App from "@/App.vue";
import router from "@/router";
import store from "@/store";
import rolesDirective from "@/plugins/portal-roles";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

createApp(App)
  .directive("roles", rolesDirective)
  .use(router)
  .use(store)
  .mount("#app");
