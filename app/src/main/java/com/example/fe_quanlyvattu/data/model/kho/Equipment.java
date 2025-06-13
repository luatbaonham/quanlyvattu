package com.example.fe_quanlyvattu.data.model.kho;

public class Equipment {
    private String serialNumber;
    private String dayOfFirstUse;
    private String description;
    private String location;
    private String status;
    private GroupEquipment groupEquipment;
    private ImportReceipt importReceipt;
    private String createdAt;
    private String updatedAt;

    public Equipment(String serialNumber, String dayOfFirstUse, String description, String location, String status, GroupEquipment groupEquipment, ImportReceipt importReceipt, String createdAt, String updatedAt) {
        this.serialNumber = serialNumber;
        this.dayOfFirstUse = dayOfFirstUse;
        this.description = description;
        this.location = location;
        this.status = status;
        this.groupEquipment = groupEquipment;
        this.importReceipt = importReceipt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDayOfFirstUse() {
        return dayOfFirstUse;
    }

    public void setDayOfFirstUse(String dayOfFirstUse) {
        this.dayOfFirstUse = dayOfFirstUse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GroupEquipment getGroupEquipment() {
        return groupEquipment;
    }

    public void setGroupEquipment(GroupEquipment groupEquipment) {
        this.groupEquipment = groupEquipment;
    }

    public ImportReceipt getImportReceipt() {
        return importReceipt;
    }

    public void setImportReceipt(ImportReceipt importReceipt) {
        this.importReceipt = importReceipt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
