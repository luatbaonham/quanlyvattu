package com.example.fe_quanlyvattu.model;

public class DeXuat {
    private String maDeXuat;
    private String nguoiGui;
    private String ngayGui;
    private String khoa;
    private int soLuongVatTu;
    private String loai;
    private String trangThai; // "CHỜ DUYỆT", "ĐÃ DUYỆT", "TỪ CHỐI"

    // Constructor
    public DeXuat(String maDeXuat, String nguoiGui, String ngayGui,
                  String khoa, int soLuongVatTu, String trangThai) {
        this.maDeXuat = maDeXuat;
        this.nguoiGui = nguoiGui;
        this.ngayGui = ngayGui;
        this.khoa = khoa;
        this.soLuongVatTu = soLuongVatTu;
        this.trangThai = trangThai;
    }

    // Getter + Setter đầy đủ
    public String getMaDeXuat() { return maDeXuat; }
    public void setMaDeXuat(String maDeXuat) { this.maDeXuat = maDeXuat; }

    public String getNguoiGui() { return nguoiGui; }
    public void setNguoiGui(String nguoiGui) { this.nguoiGui = nguoiGui; }

    public String getNgayGui() { return ngayGui; }
    public void setNgayGui(String ngayGui) { this.ngayGui = ngayGui; }

    public String getKhoa() { return khoa; }
    public void setKhoa(String khoa) { this.khoa = khoa; }

    public int getSoLuongVatTu() { return soLuongVatTu; }
    public void setSoLuongVatTu(int soLuongVatTu) { this.soLuongVatTu = soLuongVatTu; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}


