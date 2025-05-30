package com.example.fe_quanlyvattu.data.model.phieunhap;

public class PhieuNhapResponse {
    private String message;
    private int status;
    private PhieuNhapWrapper metadata;

    public PhieuNhapResponse(String message, int status, PhieuNhapWrapper metadata) {
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

    public PhieuNhapWrapper getMetadata() {
        return metadata;
    }

    public void setMetadata(PhieuNhapWrapper metadata) {
        this.metadata = metadata;
    }
// Getters & Setters
}
