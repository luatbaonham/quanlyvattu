package com.example.fe_quanlyvattu.data.model.donvitinh;

public class DonViTinh {
    private int id;
    private String name;
    private String description;
    private Boolean isDelete;
    private Boolean isActive;

    public DonViTinh(int id, String name, String description, Boolean isDelete, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDelete = isDelete;
        this.isActive = isActive;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
