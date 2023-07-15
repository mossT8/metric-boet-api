package com.metric.boet.api.payloads.request.basic;

import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;

import javax.validation.constraints.NotBlank;

public class KeyApiRequestAbstract  extends AbstractWebAppEndpointPayload<KeyApiRequestAbstract> {
    @NotBlank
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
