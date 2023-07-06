package com.metric.boet.api.util.api;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractPublicWebAppEndpointHandler<REQUEST_CLASS extends AbstractWebAppEndpointApiRequest> extends AbstractWebAppEndpointHandler<REQUEST_CLASS> {
    protected abstract ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) throws Exception;

    protected abstract boolean authoriseRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) throws Exception;
}
