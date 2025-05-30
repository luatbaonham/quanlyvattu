package com.example.fe_quanlyvattu.data.model.phieunhap;

import java.util.List;

public class PhieuNhapWrapper {
    private int code;
    private String message;
    private List<PhieuNhap> metadata;  // Đây là danh sách các phiếu

    public PhieuNhapWrapper(int code, String message, List<PhieuNhap> metadata) {
        this.code = code;
        this.message = message;
        this.metadata = metadata;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PhieuNhap> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<PhieuNhap> metadata) {
        this.metadata = metadata;
    }
}