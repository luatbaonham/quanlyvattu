package com.example.fe_quanlyvattu.data.model.phieuthanhly;

public class PhieuThanhLy{
    private String maPhieuThanhLy;
    private String ngayThanhLy;
    private String lyDo;
    private String trangThai;
    private int giaThanhLy;
    private String ghiChu;

    public PhieuThanhLy(String maPhieuThanhLy, String ngayThanhLy, String lyDo, String trangThai, int giaThanhLy, String ghiChu) {
        this.maPhieuThanhLy = maPhieuThanhLy;
        this.ngayThanhLy = ngayThanhLy;
        this.lyDo = lyDo;
        this.trangThai = trangThai;
        this.giaThanhLy = giaThanhLy;
        this.ghiChu = ghiChu;
    }

    public String getMaPhieu() {
        return maPhieuThanhLy;
    }

    public void setMaPhieu(String maPhieuThanhLy) {
        this.maPhieuThanhLy = maPhieuThanhLy;
    }

    public String getNgayThanhLy() {
        return ngayThanhLy;
    }

    public void setNgayThanhLy(String ngayThanhLy) {
        this.ngayThanhLy = ngayThanhLy;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getGiaThanhLy() {
        return giaThanhLy;
    }

    public void setGiaThanhLy(int giaThanhLy) {
        this.giaThanhLy = giaThanhLy;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}

