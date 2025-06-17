package com.example.fe_quanlyvattu.data.model.profile;

public class UpdateProfileResponse {
    private String message;
    private int status;
    private UProfile metadata;

    public UpdateProfileResponse(String message, int status, UProfile metadata) {
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

    public UProfile getMetadata() {
        return metadata;
    }

    public void setMetadata(UProfile metadata) {
        this.metadata = metadata;
    }
}

