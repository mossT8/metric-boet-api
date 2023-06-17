package com.metric.boet.api.util.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ApiEndpointRequest<T extends ApiEndpointRequest<T>> {
    public abstract T fromJson(String json);

    protected T deserialize(String json, Class<T> requestClass) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, requestClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
