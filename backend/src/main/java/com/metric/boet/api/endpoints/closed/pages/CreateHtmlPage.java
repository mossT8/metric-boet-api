package com.metric.boet.api.endpoints.closed.pages;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.HtmlPageApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.HtmlPageServiceTracked;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class CreateHtmlPage extends AbstractPrivateWebAppEndpointHandler<HtmlPageApiRequestAbstract> {
    @Autowired
    HtmlPageServiceTracked htmlPageService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, HtmlPageApiRequestAbstract payload, User user) throws Exception {
        return noSecurityCheckNeeded();
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, HtmlPageApiRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok(htmlPageService.create(payload, user));
    }
}
