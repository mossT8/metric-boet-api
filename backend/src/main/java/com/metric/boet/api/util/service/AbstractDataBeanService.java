package com.metric.boet.api.util.service;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.mapper.imp.MapperService;
import com.metric.boet.api.util.api.ApiEndpointRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDataBeanService<REQUEST_BEAN_CLASS extends ApiEndpointRequest>
        implements IAbstractDataBeanService, ICRUDAbstractDataBeanService<REQUEST_BEAN_CLASS> {
    @Autowired
    protected MapperService mapperService;

    public static BasicAPIResponse getNegativeResponse() {
        return new BasicAPIResponse("Error: could not resolve request. Please contact system support if this error persist", false);
    }
}
