package com.metric.boet.api.payloads.response;

public class BasicAPIResponse {
    private String message;
    private Boolean successful;

    public BasicAPIResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public void setSuccessful(Boolean successful) {
        this.successful = successful;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
