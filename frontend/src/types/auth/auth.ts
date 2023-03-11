import { User } from "@/types/user/user";

export interface RegisterResponse {
  data: User;
}

export interface LoginRequest {
  username: string;
  password: string;
}

export interface LoginResponse {
  accessToken: string;
}

export interface AuthHeader {
  Authorization?: string;
  "x-access-token"?: string;
}

export interface AuthState {
  status: {
    loggedIn: boolean;
  };
  user: User | null;
}

export interface AuthActionContext {
  commit: (mutation: string, payload?: any) => void;
}

export interface AuthActions {
  login(context: AuthActionContext, user: User): Promise<User>;
  logout(context: AuthActionContext): void;
  register(context: AuthActionContext, user: User): Promise<any>;
}

export interface AuthMutations {
  loginSuccess(state: AuthState, user: User): void;
  loginFailure(state: AuthState): void;
  logout(state: AuthState): void;
  registerSuccess(state: AuthState): void;
  registerFailure(state: AuthState): void;
}
