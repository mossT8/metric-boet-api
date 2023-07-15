package com.metric.boet.api.util.api;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractWebAppEndpointHandler<REQUEST_CLASS extends AbstractWebAppEndpointPayload> {
    Class<REQUEST_CLASS> type;

    public static boolean noSecurityCheckNeeded() {
        return true;
    }

    public AbstractWebAppEndpointHandler() {
        type = (Class<REQUEST_CLASS>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public REQUEST_CLASS getRequestFromString(String request) throws Exception {
        return (REQUEST_CLASS) (type.getDeclaredConstructor().newInstance()).fromJson(request);
    }

    public abstract ResponseEntity<BasicAPIResponse> processRequest(HttpServletRequest httpServletRequest, REQUEST_CLASS payload) throws Exception;

    public static String toJsonObject(String linkedHashMapString) {
        String jsonString = linkedHashMapString
                .replaceAll("^\\{", "{\"") // Remove the leading curly brace
                .replaceAll("\\}$", "\"}") // Remove the trailing curly brace
                .replaceAll(", ", "\", \"") // Replace the commas and spaces with double quotes and comma
                .replaceAll("=", "\":\""); // Replace the equal sign with double quote and equal sign
        return jsonString;
    }
}
