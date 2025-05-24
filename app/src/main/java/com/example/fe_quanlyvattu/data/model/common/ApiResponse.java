package com.example.fe_quanlyvattu.data.model.common;

public class ApiResponse<T> {
    private String message;
    private int status;
    private T metadata;

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

    public T getMetadata() {
        return metadata;
    }

    public void setMetadata(T metadata) {
        this.metadata = metadata;
    }
}
