package com.metric.boet.api.util.api;

import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.api.request.WebAppApiRequestHolderBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWebAppService<ENDPOINT_TYPE extends Annotation, HANDLER_TYPE extends AbstractWebAppEndpointHandler<AbstractWebAppEndpointPayload>> {
    protected Map<String, HANDLER_TYPE> endpointHandlers;

    public AbstractWebAppService(ListableBeanFactory beanFactory) {
        populateEndpoints(beanFactory);
    }

    public void populateEndpoints(ListableBeanFactory beanFactory) {
        Class<ENDPOINT_TYPE> type = (Class<ENDPOINT_TYPE>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];


        Map<String, Object> rawEndpoints = beanFactory.getBeansWithAnnotation(type);

        endpointHandlers = new HashMap<>();

        rawEndpoints.forEach((s, o) -> endpointHandlers.put(s, (HANDLER_TYPE) o));
    }

    public abstract ResponseEntity<?> handleRequestProcess(HttpServletRequest request, WebAppApiRequestHolderBean payload);
}
