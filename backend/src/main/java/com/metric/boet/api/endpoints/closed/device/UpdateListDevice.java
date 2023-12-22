package com.metric.boet.api.endpoints.closed.device;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.DeviceApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.DeviceService;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class UpdateListDevice extends AbstractPrivateWebAppEndpointHandler<DeviceApiRequestAbstract> {
    @Autowired
    DeviceService deviceService;

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, DeviceApiRequestAbstract payload, User user) throws Exception {
        return  ResponseEntity.ok(deviceService.update(payload, user));
    }

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, DeviceApiRequestAbstract payload, User user) throws Exception {
        return false;
    }
}
