package com.example.fe_quanlyvattu.model;

public class Kho {
    private String maphieu;
    private String tenkho;
    private String nguoitao;
    private String ngaytao;
    private int so_vt;

    public String getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(String maphieu) {
        this.maphieu = maphieu;
    }

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    public String getNguoitao() {
        return nguoitao;
    }

    public void setNguoitao(String nguoitao) {
        this.nguoitao = nguoitao;
    }

    public String getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(String ngaytao) {
        this.ngaytao = ngaytao;
    }

    public int getSo_vt() {
        return so_vt;
    }

    public void setSo_vt(int so_vt) {
        this.so_vt = so_vt;
    }

    public Kho(String maphieu, String tenkho, String nguoitao, String ngaytao, int so_vt) {
        this.maphieu = maphieu;
        this.tenkho = tenkho;
        this.nguoitao = nguoitao;
        this.ngaytao = ngaytao;
        this.so_vt = so_vt;
    }
}
