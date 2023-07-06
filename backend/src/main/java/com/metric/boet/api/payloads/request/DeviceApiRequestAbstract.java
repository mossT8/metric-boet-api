package com.metric.boet.api.payloads.request;

import com.metric.boet.api.util.api.AbstractWebAppEndpointApiRequest;
import com.metric.boet.api.util.api.IPrettyObjects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeviceApiRequestAbstract extends AbstractWebAppEndpointApiRequest<DeviceApiRequestAbstract> implements IPrettyObjects {
    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String uuid;

    @NotBlank
    @Size(max = 50)
    private String type;

    @NotBlank
    @Size(max = 150)
    private String location;

    @NotNull
    private Boolean status;

    @NotBlank
    @NotBlank
    @Size(max = 150)
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
