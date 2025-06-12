package com.example.fe_quanlyvattu.data.model.loaivattu;

public class DonViDoLuong {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DonViDoLuong(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
