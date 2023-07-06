package com.metric.boet.api.payloads.request.basic;

import com.metric.boet.api.util.api.AbstractWebAppEndpointApiRequest;
import com.metric.boet.api.util.api.IPrettyObjects;

import javax.validation.constraints.NotBlank;

public class KeyApiRequestAbstract  extends AbstractWebAppEndpointApiRequest<KeyApiRequestAbstract> implements IPrettyObjects {
    @NotBlank
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
