package com.example.fe_quanlyvattu.data.model.vattu.loaivattu;

public class EquipmentStatusCounts {
    private int available;
    private int liquidation;

    public int getLiquidation() {
        return liquidation;
    }

    public void setLiquidation(int liquidation) {
        this.liquidation = liquidation;
    }

    public EquipmentStatusCounts(int available, int liquidation) {
        this.available = available;
        this.liquidation = liquidation;
    }

    public EquipmentStatusCounts(int available) {
        this.available = available;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
