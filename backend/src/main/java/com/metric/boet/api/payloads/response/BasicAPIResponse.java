package com.metric.boet.api.payloads.response;

public class BasicAPIResponse {
    private String message;
    private Boolean successful;
    private Object data;

    public BasicAPIResponse(String message, Boolean successful, Object data) {
        this.message = message;
        this.successful = successful;
        this.data = data;
    }

    public BasicAPIResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
        this.data = null;
    }

    public BasicAPIResponse(String message) {
        this.message = message;
        this.successful = true;
        this.data = null;
    }

    public BasicAPIResponse() {
        this.message = "Successful Request!";
        this.successful = true;
        this.data = null;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
