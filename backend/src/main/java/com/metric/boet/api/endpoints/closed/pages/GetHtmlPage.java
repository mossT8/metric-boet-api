package com.metric.boet.api.endpoints.closed.pages;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.HtmlPageServiceTracked;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.closed.AbstractPrivateWebAppEndpointHandler;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PrivateWebAppEndpoint
public class GetHtmlPage extends AbstractPrivateWebAppEndpointHandler<KeyApiRequestAbstract> {
    @Autowired
    HtmlPageServiceTracked htmlPageService;

    @Override
    protected boolean isRequestForUser(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload, User user) throws Exception {
        BasicAPIResponse htmlPageResponse = htmlPageService.findByUrl(payload.getKey());

        if (htmlPageResponse.getSuccessful()) {
            return htmlPageService.doesBelongToUser((AbstractTrackedDataBean) htmlPageResponse.getData(), user);
        }

        return false;
    }

    @Override
    protected ResponseEntity<BasicAPIResponse> performRequest(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload, User user) throws Exception {
        return ResponseEntity.ok().body(htmlPageService.findDtoByUrl(payload.getKey()));
    }
}