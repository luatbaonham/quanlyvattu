package com.example.fe_quanlyvattu.model;

public class VatTu {
    private String title;
    private String maVt;
    private String tenVt;
    private String loai;
    private int slton;

    public VatTu(String title, String maVt, String tenVt, String loai, int slton) {
        this.title = title;
        this.maVt = maVt;
        this.tenVt = tenVt;
        this.loai = loai;
        this.slton = slton;
    }
    public String getTitle() {
        return title;

    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getMaVt() {
        return maVt;
    }

    public void setMaVt(String maVt) {
        this.maVt = maVt;
    }

    public int getSlton() {
        return slton;
    }

    public void setSlton(int slton) {
        this.slton = slton;
    }

    public String getTenVt() {
        return tenVt;
    }

    public void setTenVt(String tenVt) {
        this.tenVt = tenVt;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }


}