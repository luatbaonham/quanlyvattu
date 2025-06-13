package com.example.fe_quanlyvattu.data.model.vattu.kieu;



public class Kieu {
    private int id;
    private String name;
    private String description;
    private String prefix;
    private Boolean isActive;
    private Boolean isDelete;

    public Kieu(int id, String name, String description, String prefix, Boolean isActive, Boolean isDelete) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.prefix = prefix;
        this.isActive = isActive;
        this.isDelete = isDelete;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
