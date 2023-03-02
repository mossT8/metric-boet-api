import { LoginRequest, LoginResponse, RegisterResponse } from '@/types/auth';
import { User } from '@/types/user';
import axios, { AxiosResponse } from 'axios';

const AUTH_API_URL = 'http://localhost:8080/api/v1/auth/';

class AuthService {
  login(user: LoginRequest): Promise<LoginResponse> {
    return axios
      .post<LoginResponse>(AUTH_API_URL + 'signin', {
        username: user.username,
        password: user.password
      })
      .then((response: AxiosResponse<LoginResponse>) => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout(): void {
    localStorage.removeItem('user');
  }

  async register(user: User): Promise<RegisterResponse> {
    const response = await axios.post(AUTH_API_URL + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password,
      firstName: user.firstName,
      lastName: user.lastName,
      phone: user.phone
    });

    // Make sure the response data contains the expected properties
    if (
      response.data &&
      typeof response.data.message === 'string'
    ) {
      return response.data;
    } else {
      throw new Error('Invalid response from server');
    }
  }

}

export default new AuthService();
