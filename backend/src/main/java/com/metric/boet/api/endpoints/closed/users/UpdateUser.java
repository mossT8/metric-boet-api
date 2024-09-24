package com.metric.boet.api.endpoints.closed.users;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.UserServiceTracked;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static com.metric.boet.api.util.api.ApiUtil.DoesUserBeanBelongToUser;

@PrivateWebAppEndpoint
public class UpdateUser extends AbstractPrivateWebAppEndpointHandler<UserRequestAbstract> {
    @Autowired
    UserServiceTracked userService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, UserRequestAbstract payload, User user) throws Exception {
        KeyApiRequestAbstract authPayload = new KeyApiRequestAbstract();
        authPayload.setKey(payload.getUsername());

        return DoesUserBeanBelongToUser(userService, user, authPayload);
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, UserRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok(userService.update(payload, user));
    }


}
