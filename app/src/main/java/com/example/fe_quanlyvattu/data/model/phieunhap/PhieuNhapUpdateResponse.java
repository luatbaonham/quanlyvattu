package com.example.fe_quanlyvattu.data.model.phieunhap;

public class PhieuNhapUpdateResponse {
    private String message;
    private int status;
    private PhieuNhapUpdateWrapper metadata;

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

    public PhieuNhapUpdateWrapper getMetadata() {
        return metadata;
    }

    public void setMetadata(PhieuNhapUpdateWrapper metadata) {
        this.metadata = metadata;
    }

    public PhieuNhapUpdateResponse(String message, int status, PhieuNhapUpdateWrapper metadata) {
        this.message = message;
        this.status = status;
        this.metadata = metadata;
    }
}
