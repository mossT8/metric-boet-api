package com.metric.boet.api.endpoints.open.pages;

import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.beans.HtmlPageService;
import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;
import com.metric.boet.api.util.api.open.AbstractPublicWebAppEndpointHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

@PublicWebAppEndpoint
public class ViewHtmlPage extends AbstractPublicWebAppEndpointHandler<KeyApiRequestAbstract> {

    @Autowired
    HtmlPageService htmlPageService;

    @Override
    public boolean authoriseRequest(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload) throws Exception {
        BasicAPIResponse htmlPageResponse = htmlPageService.findByUrl(payload.getKey());

        if (htmlPageResponse.getSuccessful()) {
            HtmlPage htmlPage = (HtmlPage) htmlPageResponse.getData();

            if (htmlPage.getVisibleForUsers()) {
                return true;
            }

            if (htmlPage.getVisibleForModerators()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, KeyApiRequestAbstract payload) throws Exception {
        return ResponseEntity.ok().body(htmlPageService.findDtoByUrl(payload.getKey()));
    }
}

