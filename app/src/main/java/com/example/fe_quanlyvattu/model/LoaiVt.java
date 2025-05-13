package com.example.fe_quanlyvattu.model;
import java.io.Serializable;

public class LoaiVt implements Serializable{
    private String tenloai;
    private String mota;
    private int soluong;
    private String lancapnhat;

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getLancapnhat() {
        return lancapnhat;
    }

    public void setLancapnhat(String lancapnhat) {
        this.lancapnhat = lancapnhat;
    }

    public LoaiVt(String tenloai, String mota, int soluong, String lancapnhat) {
        this.tenloai = tenloai;
        this.mota = mota;
        this.soluong = soluong;
        this.lancapnhat = lancapnhat;
    }
}
