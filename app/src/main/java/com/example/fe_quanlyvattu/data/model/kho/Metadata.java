package com.example.fe_quanlyvattu.data.model.kho;



import java.util.List;

public class Metadata {
    private int code;
    private String message;
    private List<Equipment> metadata;
    private MetaInfo meta;

    public Metadata(int code, String message, List<Equipment> metadata, MetaInfo meta) {
        this.code = code;
        this.message = message;
        this.metadata = metadata;
        this.meta = meta;
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

    public List<Equipment> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Equipment> metadata) {
        this.metadata = metadata;
    }

    public MetaInfo getMeta() {
        return meta;
    }

    public void setMeta(MetaInfo meta) {
        this.meta = meta;
    }
}
