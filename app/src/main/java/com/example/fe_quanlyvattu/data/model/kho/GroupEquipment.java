package com.example.fe_quanlyvattu.data.model.kho;

public class GroupEquipment {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupEquipment(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
