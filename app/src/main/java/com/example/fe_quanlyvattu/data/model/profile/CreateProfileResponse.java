package com.example.fe_quanlyvattu.data.model.profile;

public class CreateProfileResponse {
    private String message;
    private int status;
    private Profile metadata;

    public CreateProfileResponse(String message, int status, Profile metadata) {
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

    public Profile getMetadata() {
        return metadata;
    }

    public void setMetadata(Profile metadata) {
        this.metadata = metadata;
    }
}
