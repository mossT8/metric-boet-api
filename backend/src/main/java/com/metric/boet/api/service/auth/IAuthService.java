package com.metric.boet.api.service.auth;

import com.metric.boet.api.payloads.request.auth.LoginRequest;
import com.metric.boet.api.payloads.request.auth.RegisterRequest;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;

public interface IAuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);

    BasicAPIResponse registerUser(RegisterRequest signUpRequest);
}
