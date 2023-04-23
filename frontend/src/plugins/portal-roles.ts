import { Directive } from "vue";
import { Roles } from "@/types/roles";
import { User } from "@/types/user/user";

const rolesDirective: Directive<HTMLButtonElement> = {
  mounted(el, binding) {
    const elementRoles = {
      ...binding.modifiers,
      ...getRoleFromValue(binding.value),
    };

    const userStr = localStorage.getItem("user");
    const user: User | null = userStr ? JSON.parse(userStr) : null;

    const userRoles: string[] = user ? user["roles"] || [] : [];

    for (const userRole in userRoles) {
      if (userRole in Roles && !elementRoles[userRole]) {
        const hide = elementRoles.hide === true;
        if (hide) {
          el.style.display = "none";
        } else {
          el.disabled = true;
        }
      }
    }
  },
};

function getRoleFromValue(bindingValues: any) {
  const roleValue: any = {};
  if (bindingValues !== null && bindingValues !== undefined) {
    bindingValues.forEach((value: any) => {
      Reflect.set(roleValue, value, true);
    });
  }
  return roleValue;
}

export default rolesDirective;
