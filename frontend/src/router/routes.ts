import { RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "home",
    component: () => import("@/components/pages/Home.vue"),
  },
  {
    path: "/home",
    redirect: { name: "home" },
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/components/user/Login.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("@/components/user/Register.vue"),
  },
  {
    path: "/profile",
    name: "profile",
    component: () => import("@/components/user/Profile.vue"),
  },
  {
    path: "/admin",
    name: "admin",
    component: () => import("@/components/pages/BoardAdmin.vue"),
  },
  {
    path: "/mod",
    name: "moderator",
    component: () => import("@/components/pages/BoardModerator.vue"),
  },
  {
    path: "/user",
    name: "user",
    component: () => import("@/components/pages/BoardUser.vue"),
  },
  {
    path: "/device-add",
    name: "device-add",
    component: () => import("@/components/device/DeviceRegister.vue"),
  },
  {
    path: "/device-update/:uid",
    name: "device-update",
    component: () => import("@/components/device/DeviceUpdate.vue"),
  },
  {
    path: "/html-list",
    name: "html-list",
    component: () => import("@/components/pages/AdminPageEditorList.vue"),
  },
  {
    path: "/html-edit/:url",
    name: "html-edit",
    component: () => import("@/components/pages/UpdateHtmlPage.vue"),
  },
  {
    path: "/:catchAll(.*)",
    redirect: { name: "home" },
  },
];

export default routes;
