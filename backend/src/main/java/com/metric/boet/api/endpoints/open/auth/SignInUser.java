package com.metric.boet.api.endpoints.open.auth;

import com.metric.boet.api.payloads.request.auth.LoginRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;
import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;
import com.metric.boet.api.util.api.AbstractPublicWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PublicWebAppEndpoint
public class SignInUser extends AbstractPublicWebAppEndpointHandler<LoginRequestAbstract> {

    @Autowired
    SimpleAuthService simpleAuthService;

    @Override
    public boolean authoriseRequest(HttpServletRequest httpServletRequest, LoginRequestAbstract payload) throws Exception {
        return noSecurityCheckNeeded();
    }

    @Override
    public ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, LoginRequestAbstract payload) throws Exception {
        BasicAPIResponse response = simpleAuthService.authenticateUser(payload);

        if (response.getSuccessful()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }
}
