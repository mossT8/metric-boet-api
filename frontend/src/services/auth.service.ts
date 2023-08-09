import {
  LoginRequest,
  LoginResponse,
  RegisterResponse,
} from "@/types/auth/auth";
import { User } from "@/types/user/user";
import { ApiGatewayService, HTTP_PROTOCOLS } from "./api-gateway-service";

export const LOCAL_STOAGE_USER_KEY = "user";
export const LOCAL_STOAGE_USERNAME_KEY = "username";

const AUTH_API_PACKAGE_PREFIX = "com.metric.boet.api.endpoints.open";
const AUTH_API_GROUP_KEY = "auth";

const AUTH_API_SIGN_IN_ENDPOINT = "SignInUser";
const AUTH_API_SIGN_UP_ENDPOINT = "SignUpUser";

class AuthService {
  private apiService: ApiGatewayService;

  constructor() {
    this.apiService = new ApiGatewayService();
  }

  username() {
    if (localStorage.getItem(LOCAL_STOAGE_USERNAME_KEY)) {
      return localStorage.getItem(LOCAL_STOAGE_USERNAME_KEY) || "";
    }
    return "";
  }
  login(userRequest: LoginRequest): Promise<LoginResponse> {
    const response = this.apiService
      .callApiRequest<LoginResponse>(
        AUTH_API_PACKAGE_PREFIX,
        AUTH_API_GROUP_KEY,
        AUTH_API_SIGN_IN_ENDPOINT,
        userRequest,
        HTTP_PROTOCOLS.POST,
        undefined, // No additional properties needed for this request
        true // Public subdomain for login
      )
      .then((response) => {
        if (response.accessToken) {
          localStorage.setItem(LOCAL_STOAGE_USER_KEY, JSON.stringify(response));
          localStorage.setItem(LOCAL_STOAGE_USERNAME_KEY, userRequest.username);
        }
        return response;
      });

    return response;
  }

  logout(): void {
    localStorage.removeItem(LOCAL_STOAGE_USER_KEY);
    localStorage.removeItem(LOCAL_STOAGE_USERNAME_KEY);
  }

  async register(user: User): Promise<RegisterResponse> {
    const payload = {
      username: user.username,
      email: user.email,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
      phone: user.phone,
    };

    const response = await this.apiService.callApiRequest<RegisterResponse>(
      AUTH_API_PACKAGE_PREFIX,
      AUTH_API_GROUP_KEY,
      AUTH_API_SIGN_UP_ENDPOINT,
      payload,
      HTTP_PROTOCOLS.POST,
      undefined, // No additional properties needed for this request
      true // Public subdomain for registration
    );

    // Make sure the response data contains the expected properties
    if (response.data && typeof response.data.message === "string") {
      return response;
    } else {
      throw new Error("Invalid response from server");
    }
  }
}

export default new AuthService();
