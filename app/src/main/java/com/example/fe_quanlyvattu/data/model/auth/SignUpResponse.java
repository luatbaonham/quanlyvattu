package com.example.fe_quanlyvattu.data.model.auth;

import com.example.fe_quanlyvattu.data.model.common.Metadata;

public class SignUpResponse {
    private String message;
    private int status;
    private Metadata metadata;

    public SignUpResponse(String message, int status, Metadata metadata) {
        this.message = message;
        this.status = status;
        this.metadata = metadata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
