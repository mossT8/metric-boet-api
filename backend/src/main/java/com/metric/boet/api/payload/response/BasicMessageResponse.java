package com.metric.boet.api.payload.response;

public class BasicMessageResponse {
    private String message;
    private Boolean successful;

    public BasicMessageResponse(String message, Boolean successful) {
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
