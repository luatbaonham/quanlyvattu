package com.example.fe_quanlyvattu.data.model.phieunhap;

import java.util.List;

public class PhieuNhapUpdateWrapper {
    private int code;
    private String message;

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

    public UpdateMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(UpdateMetadata metadata) {
        this.metadata = metadata;
    }

    public PhieuNhapUpdateWrapper(UpdateMetadata metadata, String message, int code) {
        this.metadata = metadata;
        this.message = message;
        this.code = code;
    }

    private UpdateMetadata metadata;
}
