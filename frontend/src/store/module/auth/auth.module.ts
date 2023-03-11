import { AuthActionContext, AuthState } from '@/types/auth/auth';
import { User } from '@/types/user/user';
import AuthService from '@/services/auth.service';

const userStr = localStorage.getItem('user');
const user: User | null = userStr ? JSON.parse(userStr) : null;

const initialState: AuthState = user
  ? { status: { loggedIn: true }, user }
  : { status: { loggedIn: false }, user: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    async login({ commit }: AuthActionContext, user: User): Promise<User> {
    await AuthService.login(user).then(
        response  => {
          commit('loginSuccess', response);
          return Promise.resolve(user);
        },
        error => {
          commit('loginFailure');
          return Promise.reject(error);
        }
      );

    return user;
    },
    logout({ commit }: AuthActionContext): void {
      AuthService.logout();
      commit('logout');
    },
    register({ commit }: AuthActionContext, user: User): Promise<any> {
      return AuthService.register(user).then(
        response => {
          commit('registerSuccess');
          return Promise.resolve(response.data);
        },
        error => {
          commit('registerFailure');
          return Promise.reject(error);
        }
      );
    }
  },
  mutations: {
    loginSuccess(state: AuthState, user: User): void {
      state.status.loggedIn = true;
      state.user = user;
    },
    loginFailure(state: AuthState): void {
      state.status.loggedIn = false;
      state.user = null;
    },
    logout(state: AuthState): void {
      state.status.loggedIn = false;
      state.user = null;
    },
    registerSuccess(state: AuthState): void {
      state.status.loggedIn = false;
    },
    registerFailure(state: AuthState): void {
      state.status.loggedIn = false;
    }
  }
};
