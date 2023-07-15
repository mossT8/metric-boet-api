package com.metric.boet.api.util.api.open;

import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.api.AbstractWebAppEndpointHandler;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractPublicWebAppEndpointHandler<REQUEST_CLASS extends AbstractWebAppEndpointPayload> extends AbstractWebAppEndpointHandler<REQUEST_CLASS> {
    public abstract boolean authoriseRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) throws Exception;
}
