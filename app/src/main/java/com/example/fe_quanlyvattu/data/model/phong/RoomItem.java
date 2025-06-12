package com.example.fe_quanlyvattu.data.model.phong;

public class RoomItem {
    private final String id;
    private final String name;

    public RoomItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    @Override
    public String toString() {
        return name; // Hiển thị name trong dropdown
    }
}

