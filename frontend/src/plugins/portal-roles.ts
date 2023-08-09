import { Directive } from "vue";
import { Roles } from "@/types/roles";
import { User } from "@/types/user/user";
import { LOCAL_STOAGE_USER_KEY } from "@/services/auth.service";

const rolesDirective: Directive<HTMLButtonElement> = {
  mounted(el, binding) {
    const elementRoles = {
      ...binding.modifiers,
      ...getRoleFromValue(binding.value),
    };

    const userStr = localStorage.getItem(LOCAL_STOAGE_USER_KEY);
    const user: User | null = userStr ? JSON.parse(userStr) : null;

    const userRoles: Roles[] = user ? user["roles"] || [] : [];
    console.log(userRoles);

    console.log(elementRoles[0]);
    console.log(elementRoles[Roles.ROLE_PUBLIC]);


    if (userRoles.length === 0 ) {
      if (elementRoles[Roles.ROLE_PUBLIC]) {
        el.disabled = true;

      } else {
        el.style.display = "none";
      }
      el.disabled = true;
    } else {
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
