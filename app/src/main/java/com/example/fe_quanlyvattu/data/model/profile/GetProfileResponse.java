package com.example.fe_quanlyvattu.data.model.profile;

public class GetProfileResponse {
    private String message;
    private int status;
    private GProfile metadata;

    public GetProfileResponse(String message, int status, GProfile metadata) {
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

    public GProfile getMetadata() {
        return metadata;
    }

    public void setMetadata(GProfile metadata) {
        this.metadata = metadata;
    }
}
