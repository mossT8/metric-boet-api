package com.metric.boet.api.endpoints.closed.device;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.DeviceServiceTracked;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class GetDevice extends AbstractPrivateWebAppEndpointHandler<KeyApiRequestAbstract> {

    @Autowired
    DeviceServiceTracked deviceService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload, User user) throws Exception {
        BasicAPIResponse htmlPageResponse = deviceService.getBeanByUiid(payload.getKey());

        if (htmlPageResponse.getSuccessful()) {
            return deviceService.doesBelongToUser((AbstractTrackedDataBean) htmlPageResponse.getData(), user);
        }

        return false;
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok(deviceService.findByUiid(payload.getKey()));
    }
}
