package com.metric.boet.api.endpoints.open.auth;

import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;
import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;
import com.metric.boet.api.util.api.open.AbstractPublicWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PublicWebAppEndpoint
public class RegisterUser extends AbstractPublicWebAppEndpointHandler<UserRequestAbstract> {

    @Autowired
    SimpleAuthService simpleAuthService;

    @Override
    public boolean authoriseRequest(HttpServletRequest httpServletRequest, UserRequestAbstract payload) throws Exception {
        return noSecurityCheckNeeded();
    }

    @Override
    public ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, UserRequestAbstract payload) throws Exception {
        return ResponseEntity.ok(simpleAuthService.registerUser(payload));
    }
}
