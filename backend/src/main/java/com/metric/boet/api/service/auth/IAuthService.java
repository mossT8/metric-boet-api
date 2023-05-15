package com.metric.boet.api.service.auth;

import com.metric.boet.api.payload.request.LoginRequest;
import com.metric.boet.api.payload.request.SignupRequest;
import com.metric.boet.api.payload.response.BasicMessageResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    BasicMessageResponse registerUser(SignupRequest signUpRequest);
}
