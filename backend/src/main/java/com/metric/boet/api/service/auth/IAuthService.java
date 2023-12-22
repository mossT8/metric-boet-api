package com.metric.boet.api.service.auth;

import com.metric.boet.api.payloads.request.auth.LoginRequestAbstract;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;

public interface IAuthService {
    BasicAPIResponse authenticateUser(LoginRequestAbstract loginRequest);

    BasicAPIResponse registerUser(UserRequestAbstract signUpRequest);
}
