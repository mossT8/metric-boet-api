package com.metric.boet.api.endpoints.auth;

import com.metric.boet.api.payloads.request.user.UserRequest;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;
import com.metric.boet.api.util.annotations.WebAppEndpoint;
import com.metric.boet.api.util.api.WebAppEndpointHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@WebAppEndpoint
public class RegisterEndpoint implements WebAppEndpointHandler<UserRequest, BasicAPIResponse> {

    @Autowired
    SimpleAuthService simpleAuthService;

    @Override
    public boolean authoriseRequest(HttpServletRequest httpServletRequest, UserRequest payload) throws Exception {
        return WebAppEndpointHandler.noSecurityCheckNeeded();
    }

    @Override
    public ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, UserRequest payload) throws Exception {
        return ResponseEntity.ok(simpleAuthService.registerUser(payload));
    }

    @Override
    public UserRequest getRequestFromString(String json) throws Exception {
        return new UserRequest().fromJson(json);
    }
}