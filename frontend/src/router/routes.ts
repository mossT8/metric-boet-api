import { RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  /** public routes */
  {
    path: "/home",
    name: "home",
    component: () => import("@/pages/public/HomePage.vue"),
  },
  {
    path: "/",
    redirect: { name: "home" },
  },
  {
    path: "/about",
    name: "about",
    component: () => import("@/pages/public/AboutPage.vue"),
  },

  /** user routes */
  {
    path: "/settings",
    name: "settings",
    component: () =>
      import("@/pages/user/user-management/UserSettingsPage.vue"),
  },
  {
    path: "/user-login",
    name: "user-login",
    component: () => import("@/pages/user/user-management/UserLoginPage.vue"),
  },
  {
    path: "/user-logout",
    name: "user-logout",
    component: () => import("@/pages/user/user-management/UserLogoutPage.vue"),
  },
  {
    path: "/user-board",
    name: "user-board",
    component: () => import("@/pages/user/UserBoardPage.vue"),
  },
  {
    path: "/user-register",
    name: "user-register",
    component: () =>
      import("@/pages/user/user-management/UserRegisterPage.vue"),
  },
  {
    path: "/user-profile",
    name: "user-profile",
    component: () => import("@/pages/user/user-management/UserProfilePage.vue"),
  },

  /** admin routes */
  {
    path: "/admin-board",
    name: "admin-board",
    component: () => import("@/pages/admin/AdminBoardPage.vue"),
  },
  {
    path: "/page-management",
    name: "page-management",
    component: () => import("@/pages/admin/page-management/HtmlListPage.vue"),
  },
  {
    path: "/html-edit/:url",
    name: "html-edit",
    component: () => import("@/pages/admin/page-management/HtmlUpdatePage.vue"),
  },
  {
    path: "/user-management",
    name: "user-management",
    component: () => import("@/pages/admin/user-management/UserListPage.vue"),
  },
  {
    path: "/user-edit/:username",
    name: "user-edit",
    component: () => import("@/pages/admin/user-management/UpdateUserPage.vue"),
  },

  /** moderator routes */
  {
    path: "/moderator-board",
    name: "moderator",
    component: () => import("@/pages/moderator/ModeratorBoardPage.vue"),
  },
  {
    path: "/device-register",
    name: "device-register",
    component: () =>
      import("@/pages/moderator/device-management/DeviceRegisterPage.vue"),
  },
  {
    path: "/device-update/:uid",
    name: "device-update",
    component: () =>
      import("@/pages/moderator/device-management/DeviceUpdatePage.vue"),
  },
  /*  {
    path: "/:catchAll(.*)",
    redirect: { name: "home" },
  }, */
];

export default routes;
