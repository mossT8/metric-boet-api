package com.metric.boet.api.util.api.request;

import java.util.LinkedHashMap;
import java.util.Map;

public class WebAppApiRequestHolderBean {
    private String packagePrefix;
    private String apiGroupKey;
    private String endpointClassName;
    private LinkedHashMap<String, Object> requestObject;
    private Map<String, Object> additionalProperties;

    public WebAppApiRequestHolderBean() {
    }

    public WebAppApiRequestHolderBean(String packagePrefix, String apiGroupKey, String endpointClassName, LinkedHashMap<String, Object> requestObject, Map<String, Object> additionalProperties) {
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

    public LinkedHashMap<String, Object> getRequestObject() {
        return requestObject;
    }

    public void setRequestObject(LinkedHashMap<String, Object> requestObject) {
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

