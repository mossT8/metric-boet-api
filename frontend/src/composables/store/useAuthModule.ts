import { useStore } from "vuex";
import { User } from "@/types/user/user";
import { AuthActionContext, AuthState, LoginResponse } from "@/types/auth/auth";
import AuthService from "@/services/auth.service";
import { ComputedRef, computed } from "vue";
import { Roles } from "@/types/roles";

interface AuthModule {
  user: ComputedRef<User | null>;
  loggedIn: ComputedRef<boolean>;
  isAdmin: ComputedRef<boolean>;
  isModertor: ComputedRef<boolean>;
  isUser: ComputedRef<boolean>;
  login(user: User): Promise<LoginResponse>;
  logout(): void;
  register(user: User): Promise<any>;
}

export function useAuthModuleFeatures(): AuthModule {
  const store = useStore();
  const user = computed(() => store.state.auth.user);
  const loggedIn = computed<boolean>(() => store.state.auth.user != null);
  const isAdmin = computed<boolean>(
    () => loggedIn.value && user.value.roles.includes(Roles.ROLE_ADMIN)
  );
  const isModertor = computed<boolean>(
    () => loggedIn.value && user.value.roles.includes(Roles.ROLE_MODERATOR)
  );
  const isUser = computed<boolean>(
    () => loggedIn.value && user.value.roles.includes(Roles.ROLE_USER)
  );

  async function login(user: User): Promise<LoginResponse> {
    return AuthService.login(user)
      .then((response) => {
        store.commit("auth/loginSuccess", response);
        return response;
      })
      .catch((error) => {
        store.commit("auth/loginFailure");
        return Promise.reject(error);
      });
  }

  function logout(): void {
    AuthService.logout();
    store.commit("auth/logout");
  }

  function register(user: User): Promise<any> {
    return AuthService.register(user)
      .then((response) => {
        store.commit("auth/registerSuccess");
        return response.data;
      })
      .catch((error) => {
        store.commit("auth/registerFailure");
        return Promise.reject(error);
      });
  }

  return {
    user,
    loggedIn,
    isAdmin,
    isModertor,
    isUser,
    login,
    logout,
    register,
  };
}
