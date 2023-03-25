package com.metric.boet.api.payload.response;

public class MessageResponse {
    private String message;
    private Boolean successful;

    public MessageResponse(String message, Boolean successful) {
        this.message = message;
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
