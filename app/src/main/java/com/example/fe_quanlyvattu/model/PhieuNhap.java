package com.example.fe_quanlyvattu.model;

public class PhieuNhap{
    private String maPhieuNhap;
    private String tenVatTu;
    private String nguoiTao;
    private String ngayDat;
    private int soLuong;
    private String nhaCungCap;

    public PhieuNhap(String maPhieuNhap, String tenVatTu, String nguoiTao, String ngayDat, int soLuong, String nhaCungCap) {
        this.maPhieuNhap = maPhieuNhap;
        this.tenVatTu = tenVatTu;
        this.nguoiTao = nguoiTao;
        this.ngayDat = ngayDat;
        this.soLuong = soLuong;
        this.nhaCungCap = nhaCungCap;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getTenVatTu() {
        return tenVatTu;
    }

    public void setTenVatTu(String tenVatTu) {
        this.tenVatTu = tenVatTu;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
// constructor, getters, setters...

}
