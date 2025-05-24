package com.example.fe_quanlyvattu.data.model.auth;

import com.example.fe_quanlyvattu.data.model.common.Metadata;

public class LoginResponse {
    private String message;
    private int status;
    private Metadata metadata;

    // Getters and Setters

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
