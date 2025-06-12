package com.example.fe_quanlyvattu.data.model.phong;

import androidx.annotation.NonNull;

import com.example.fe_quanlyvattu.data.model.phieumuon.Department;

public class Room {
    String id;
    String name;
    Department department;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
}
