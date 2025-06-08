package com.example.fe_quanlyvattu.data.model.phieumuon;

import java.util.List;

public class Metadata {
    private int code;
    private String message;
    private List<BorrowReceipt> metadata;
    private MetaInfo meta;

    // Getters và setters

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

    public List<BorrowReceipt> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<BorrowReceipt> metadata) {
        this.metadata = metadata;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }
}

