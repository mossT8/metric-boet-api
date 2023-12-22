import { User, UserMapped } from "@/types/user/user";
import {
  ApiGatewayService,
  EmptyReuqest,
  HTTP_PROTOCOLS,
  KeyRequest,
} from "./api-gateway-service";
import authHeader from "@/services/auth-header";
import { formatDate } from "@/types/dates/date-util";

const API_PACKAGE_PREFIX = "com.metric.boet.api.endpoints.closed";
const API_GROUP_KEY = "users";

const USER_GET_ENDPOINT = "GetUser";
const USER_CREATE_ENDPOINT = "CreateUser";
const USER_UPDATE_ENDPOINT = "UpdateUser";
const USER_LIST_ENDPOINT = "ListAllUsers";

class UserService {
  private apiService: ApiGatewayService;

  constructor() {
    this.apiService = new ApiGatewayService();
  }

  addUser(user: User) {
    const payload: User = user;
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      USER_CREATE_ENDPOINT,
      payload,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } }
    );
  }

  update(user: User) {
    const payload: User = user;
    return this.apiService.callApiRequest<void>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      USER_UPDATE_ENDPOINT,
      payload,
      HTTP_PROTOCOLS.POST,
      { headers: { Authorization: authHeader() } }
    );
  }

  getUserByAccountCode(accountCode: string): Promise<UserMapped> {
    const payload: KeyRequest = { key: accountCode };
    return this.apiService.callApiRequest<UserMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      USER_GET_ENDPOINT,
      payload,
      HTTP_PROTOCOLS.GET,
      { headers: { Authorization: authHeader() } }
    );
  }

  getUserByUsername(username: string): Promise<UserMapped> {
    const payload: KeyRequest = { key: username };
    return this.apiService.callApiRequest<UserMapped>(
      API_PACKAGE_PREFIX,
      API_GROUP_KEY,
      USER_GET_ENDPOINT,
      payload,
      HTTP_PROTOCOLS.GET,
      { headers: { Authorization: authHeader() } }
    );
  }

  getAllUsers(): Promise<UserMapped[]> {
    const payload: EmptyReuqest = {};
    return this.apiService
      .callApiRequest<UserMapped[]>(
        API_PACKAGE_PREFIX,
        API_GROUP_KEY,
        USER_LIST_ENDPOINT,
        payload,
        HTTP_PROTOCOLS.GET,
        { headers: { Authorization: authHeader() } }
      )
      .then((data) => {
        for (const record of data) {
          if (record.createdAt) {
            record.createdAt = formatDate(record.createdAt);
            record.updatedAt = formatDate(record.updatedAt);
          }
        }
        return data;
      });
  }
}

export default new UserService();
