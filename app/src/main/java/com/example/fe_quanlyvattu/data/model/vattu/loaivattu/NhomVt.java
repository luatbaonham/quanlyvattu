package com.example.fe_quanlyvattu.data.model.vattu.loaivattu;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class NhomVt {
    private String code;
    private String name;
    private String description;
    private DonViDoLuong unitOfMeasure;
    private LoaiVt type;
    private HangSX manufacturer;
    @SerializedName("equipmentStatusCounts")
    private Map<String, Integer> equipmentStatusCounts;

    private Boolean isDeleted;
    private Boolean isActive;
    private String createdAt;
    private String updatedAt;

    public NhomVt(String code, String name, String description, DonViDoLuong unitOfMeasure, LoaiVt type, HangSX manufacturer, Map<String, Integer> equipmentStatusCounts , Boolean isDeleted, Boolean isActive, String createdAt, String updatedAt) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.unitOfMeasure = unitOfMeasure;
        this.type = type;
        this.manufacturer = manufacturer;
        this.equipmentStatusCounts = equipmentStatusCounts;
        this.isDeleted = isDeleted;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DonViDoLuong getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(DonViDoLuong unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public LoaiVt getType() {
        return type;
    }

    public void setType(LoaiVt type) {
        this.type = type;
    }

    public HangSX getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(HangSX manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
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

    public Map<String, Integer> getEquipmentStatusCounts() {
        return equipmentStatusCounts;
    }

    public void setEquipmentStatusCounts(Map<String, Integer> equipmentStatusCounts) {
        this.equipmentStatusCounts = equipmentStatusCounts;
    }
}
