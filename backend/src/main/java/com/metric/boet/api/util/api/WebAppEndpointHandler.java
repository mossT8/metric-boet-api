package com.metric.boet.api.util.api;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface WebAppEndpointHandler<P, R> {
    static boolean noSecurityCheckNeeded() {
        return true;
    }

    boolean authoriseRequest(HttpServletRequest httpServletRequest, P payload) throws Exception;

    ResponseEntity<R> processRequest(HttpServletRequest httpServletRequest, P payload) throws Exception;

    P getRequestFromString(String request) throws Exception;
}
