import { RouteRecordRaw } from "vue-router";

// Import components
import HomePage from "@/pages/public/HomePage.vue";
import AboutPage from "@/pages/public/AboutPage.vue";
import UserSettingsPage from "@/pages/user/user-management/UserSettingsPage.vue";
import UserLoginPage from "@/pages/user/user-management/UserLoginPage.vue";
import UserLogoutPage from "@/pages/user/user-management/UserLogoutPage.vue";
import UserBoardPage from "@/pages/user/UserBoardPage.vue";
import UserRegisterPage from "@/pages/user/user-management/UserRegisterPage.vue";
import UserProfilePage from "@/pages/user/user-management/UserProfilePage.vue";
import AdminBoardPage from "@/pages/admin/AdminBoardPage.vue";
import HtmlListPage from "@/pages/admin/page-management/HtmlListPage.vue";
import HtmlUpdatePage from "@/pages/admin/page-management/HtmlUpdatePage.vue";
import UserListPage from "@/pages/admin/user-management/UserListPage.vue";
import UpdateUserPage from "@/pages/admin/user-management/UpdateUserPage.vue";
import ModeratorBoardPage from "@/pages/moderator/ModeratorBoardPage.vue";
import DeviceRegisterPage from "@/pages/moderator/device-management/DeviceRegisterPage.vue";
import DeviceUpdatePage from "@/pages/moderator/device-management/DeviceUpdatePage.vue";

// Define routes
const publicRoutes: RouteRecordRaw[] = [
  { path: "/home", name: "home", component: HomePage },
  { path: "/", redirect: { name: "home" } },
  { path: "/about", name: "about", component: AboutPage },
];

const userRoutes: RouteRecordRaw[] = [
  { path: "/settings", name: "settings", component: UserSettingsPage },
  { path: "/user-login", name: "user-login", component: UserLoginPage },
  { path: "/user-logout", name: "user-logout", component: UserLogoutPage },
  { path: "/user-board", name: "user-board", component: UserBoardPage },
  {
    path: "/user-register",
    name: "user-register",
    component: UserRegisterPage,
  },
  { path: "/user-profile", name: "user-profile", component: UserProfilePage },
];

const adminRoutes: RouteRecordRaw[] = [
  { path: "/admin-board", name: "admin-board", component: AdminBoardPage },
  {
    path: "/page-management",
    name: "page-management",
    component: HtmlListPage,
  },
  { path: "/html-edit/:url", name: "html-edit", component: HtmlUpdatePage },
  {
    path: "/user-management",
    name: "user-management",
    component: UserListPage,
  },
  {
    path: "/user-edit/:username",
    name: "user-edit",
    component: UpdateUserPage,
  },
];

const moderatorRoutes: RouteRecordRaw[] = [
  {
    path: "/moderator-board",
    name: "moderator",
    component: ModeratorBoardPage,
  },
  {
    path: "/device-register",
    name: "device-register",
    component: DeviceRegisterPage,
  },
  {
    path: "/device-update/:uid",
    name: "device-update",
    component: DeviceUpdatePage,
  },
];

// Combine routes
const routes: RouteRecordRaw[] = [
  ...publicRoutes,
  ...userRoutes,
  ...adminRoutes,
  ...moderatorRoutes,
  // Add catch-all route if needed
  // { path: "/:catchAll(.*)", redirect: { name: "home" } },
];

export default routes;
