package com.metric.boet.api.payloads.request.basic;

import com.metric.boet.api.util.api.AbstractWebAppEndpointApiRequest;
import com.metric.boet.api.util.api.IPrettyObjects;

import javax.validation.constraints.Positive;

public class IdApiRequestAbstract extends AbstractWebAppEndpointApiRequest<IdApiRequestAbstract> implements IPrettyObjects {
    @Positive
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
