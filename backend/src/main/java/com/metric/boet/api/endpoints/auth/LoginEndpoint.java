package com.metric.boet.api.endpoints.auth;

import com.metric.boet.api.payloads.request.auth.LoginRequest;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;
import com.metric.boet.api.util.annotations.WebAppEndpoint;
import com.metric.boet.api.util.api.WebAppEndpointHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@WebAppEndpoint
public class LoginEndpoint implements WebAppEndpointHandler<LoginRequest, JwtResponse> {

    @Autowired
    SimpleAuthService simpleAuthService;

    @Override
    public boolean authoriseRequest(HttpServletRequest httpServletRequest, LoginRequest payload) throws Exception {
        return WebAppEndpointHandler.noSecurityCheckNeeded();
    }

    @Override
    public ResponseEntity<JwtResponse> processRequest(HttpServletRequest httpServletRequest, LoginRequest payload) throws Exception {
        JwtResponse response = simpleAuthService.authenticateUser(payload);

        if (response.getSuccessful()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }

    @Override
    public LoginRequest getRequestFromString(String request) throws Exception {
        return new LoginRequest().fromJson(request);
    }
}
