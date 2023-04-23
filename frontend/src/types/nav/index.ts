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
    route: "/",
    icon: "home",
    label: "Home",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
  {
    route: "/",
    icon: "home",
    label: "User Board",
    roles: [Roles.ROLE_ADMIN, Roles.ROLE_USER, Roles.ROLE_MODERATOR],
  },
  {
    route: "/",
    icon: "home",
    label: "Admin Board",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/",
    icon: "home",
    label: "User Management",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/",
    icon: "home",
    label: "Page Management",
    roles: [Roles.ROLE_ADMIN],
  },
  {
    route: "/",
    icon: "home",
    label: "Site Dashboard",
    roles: [Roles.ROLE_ADMIN],
  },
];
