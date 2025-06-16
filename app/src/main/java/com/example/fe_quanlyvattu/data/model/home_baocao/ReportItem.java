package com.example.fe_quanlyvattu.data.model.home_baocao;

public class ReportItem {
    private String loaiPhieu; // import, borrow, transfer, liquidation
    private String status;
    private int count;

    public ReportItem(String loaiPhieu, String status, int count) {
        this.loaiPhieu = loaiPhieu;
        this.status = status;
        this.count = count;
    }

    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
