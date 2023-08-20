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
    route: "/home",
    icon: "fa-solid fa-home",
    label: "Home",
    roles: [Roles.ROLE_PUBLIC],
  },
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
    route: "/site-management",
    icon: "fa-solid fa-tachometer-alt",
    label: "Site Management",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/about",
    icon: "fa-solid fa-info",
    label: "About",
    roles: [Roles.ROLE_PUBLIC],
  },
];