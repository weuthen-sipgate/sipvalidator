package com.sipgate.sipvalidator.model;

import java.util.ArrayList;
import java.util.List;

public class ValidationResponse {
    private boolean valid;
    private List<String> messages = new ArrayList<>();
    private List<String> errors = new ArrayList<>();
    private List<HeaderInfo> headers = new ArrayList<>();

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<HeaderInfo> getHeaders() {
        return headers;
    }

    public void setHeaders(List<HeaderInfo> headers) {
        this.headers = headers;
    }
}