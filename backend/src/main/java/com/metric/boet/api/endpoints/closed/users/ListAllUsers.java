package com.metric.boet.api.endpoints.closed.users;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.EmptyApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.UserService;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class ListAllUsers extends AbstractPrivateWebAppEndpointHandler<EmptyApiRequestAbstract> {

    @Autowired
    UserService userService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, EmptyApiRequestAbstract payload, User user) throws Exception {
        return noSecurityCheckNeeded();
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, EmptyApiRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok(userService.findResponseByCreatedUser(user));
    }
}
