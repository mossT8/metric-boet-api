package com.metric.boet.api.util.api;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractWebAppEndpointHandler<REQUEST_CLASS extends AbstractWebAppEndpointApiRequest> {
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
}
