package com.example.fe_quanlyvattu.data.model.phieumuon;

public class RequestItem {
    private String groupEquipmentCode;
    private String name;
    private int quantity;
    private String note;
    private EquipmentType type;
    private Manufacturer manufacturer;

    // Getters và setters

    public String getGroupEquipmentCode() {
        return groupEquipmentCode;
    }

    public void setGroupEquipmentCode(String groupEquipmentCode) {
        this.groupEquipmentCode = groupEquipmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public EquipmentType getType() {
        return type;
    }

    public void setType(EquipmentType type) {
        this.type = type;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
