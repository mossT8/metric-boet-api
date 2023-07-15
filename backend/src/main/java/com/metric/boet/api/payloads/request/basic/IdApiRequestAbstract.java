package com.metric.boet.api.payloads.request.basic;

import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;

import javax.validation.constraints.Positive;

public class IdApiRequestAbstract extends AbstractWebAppEndpointPayload<IdApiRequestAbstract> {
    @Positive
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
