package com.example.fe_quanlyvattu.data.model.vattu.kieu;



import java.util.List;

public class Metadata {
    private int code;
    private String message;
    private List<Kieu> metadata;
    private MetaInfo meta;

    public Metadata(int code, String message, List<Kieu> metadata, MetaInfo meta) {
        this.code = code;
        this.message = message;
        this.metadata = metadata;
        this.meta = meta;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }

    public List<Kieu> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Kieu> metadata) {
        this.metadata = metadata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
