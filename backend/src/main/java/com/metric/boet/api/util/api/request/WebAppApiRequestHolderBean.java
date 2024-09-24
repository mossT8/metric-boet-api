package com.metric.boet.api.util.api.request;

import javax.validation.constraints.NotBlank;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebAppApiRequestHolderBean {
    @NotBlank
    private String packagePrefix;
    @NotBlank
    private String apiGroupKey;
    @NotBlank
    private String endpointClassName;
    @NotBlank
    private LinkedHashMap<String, Object> payload;

    private Map<String, Object> additionalProperties;

    public WebAppApiRequestHolderBean() {
    }

    public WebAppApiRequestHolderBean(String packagePrefix, String apiGroupKey, String endpointClassName, LinkedHashMap<String, Object> payload, Map<String, Object> additionalProperties) {
        this.packagePrefix = packagePrefix;
        this.apiGroupKey = apiGroupKey;
        this.endpointClassName = endpointClassName;
        this.payload = payload;
        this.additionalProperties = additionalProperties;
    }

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getApiGroupKey() {
        return apiGroupKey;
    }

    public void setApiGroupKey(String apiGroupKey) {
        this.apiGroupKey = apiGroupKey;
    }

    public String getEndpointClassName() {
        return endpointClassName;
    }

    public void setEndpointClassName(String endpointClassName) {
        this.endpointClassName = endpointClassName;
    }

    public LinkedHashMap<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(LinkedHashMap<String, Object> payload) {
        this.payload = payload;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public String getFullyQualifiedClassName() {
        return packagePrefix + "." + apiGroupKey + "." + endpointClassName;
    }

    public String getFullyQualifiedPackageName() {
        return packagePrefix + "." + apiGroupKey;
    }
}

