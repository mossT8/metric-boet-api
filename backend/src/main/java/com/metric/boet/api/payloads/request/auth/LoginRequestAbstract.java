package com.metric.boet.api.payloads.request.auth;

import com.metric.boet.api.util.api.AbstractWebAppEndpointApiRequest;
import com.metric.boet.api.util.api.IPrettyObjects;

import javax.validation.constraints.NotBlank;

public class LoginRequestAbstract extends AbstractWebAppEndpointApiRequest<LoginRequestAbstract> implements IPrettyObjects {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
