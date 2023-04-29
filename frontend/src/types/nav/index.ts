import { Roles } from "@/types/roles/index";
export interface NavItem {
  route: string;
  icon: string;
  label: string;
  roles: Roles[];
  floatToeEnd?: boolean;
}

export const mainNavOptions: NavItem[] = [
  {
    route: "/user-board",
    icon: "fa-solid fa-blog",
    label: "User Board",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
  {
    route: "/admin-board",
    icon: "fa-solid fa-user-cog",
    label: "Admin Board",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/user-management",
    icon: "fa-solid fa-users",
    label: "User Management",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/page-management",
    icon: "fa-solid fa-file",
    label: "Page Management",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/home",
    icon: "fa-solid fa-tachometer-alt",
    label: "Site Dashboard",
    roles: [Roles.ROLE_ADMIN],
  },
];

export const subNavOptions: NavItem[] = [
  {
    route: "/user-profile",
    icon: "fa-solid fa-user-circle",
    label: "Profile",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
  {
    route: "/settings",
    icon: "fa-solid fa-cog",
    label: "Settings",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
  {
    route: "/user-logout",
    icon: "fa-solid fa-sign-out-alt",
    label: "Log out",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
];
