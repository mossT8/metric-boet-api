package com.metric.boet.api.util.api;

import java.util.Map;

public class ApiRequest<T> {
    private String packagePrefix;
    private String apiGroupKey;
    private String endpointClassName;
    private T requestObject;
    private Map<String, Object> additionalProperties;

    public ApiRequest() {
    }

    public ApiRequest(String packagePrefix, String apiGroupKey, String endpointClassName, T requestObject, Map<String, Object> additionalProperties) {
        this.packagePrefix = packagePrefix;
        this.apiGroupKey = apiGroupKey;
        this.endpointClassName = endpointClassName;
        this.requestObject = requestObject;
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

    public T getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(T requestObject) {
        this.requestObject = requestObject;
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

