package com.metric.boet.api.util.api.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractWebAppEndpointPayload<T> implements IPrettyPayload {
    private Class<T> type;

    public T fromJson(String json) throws Exception {
        return deserialize(json, type);
    }

    public AbstractWebAppEndpointPayload() {
        type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public T deserialize(String json) throws Exception {
        return fromJson(json);
    }

    public T deserialize(String json, Class<T> requestClass) throws NoSuchMethodException, Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, requestClass);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
