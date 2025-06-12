package com.example.fe_quanlyvattu.data.model.phong;

import java.util.List;

public class Metadata {
    private int code;
    private String message;
    private List<Room> metadata;

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

    public List<Room> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<Room> metadata) {
        this.metadata = metadata;
    }
}
