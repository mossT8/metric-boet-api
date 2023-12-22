package com.metric.boet.api.endpoints.closed.users;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.UserService;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class CreateUser extends AbstractPrivateWebAppEndpointHandler<UserRequestAbstract> {

    @Autowired
    UserService userService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, UserRequestAbstract payload, User user) throws Exception {
        return noSecurityCheckNeeded();
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, UserRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok(userService.create(payload, user.getAudit()));
    }
}
