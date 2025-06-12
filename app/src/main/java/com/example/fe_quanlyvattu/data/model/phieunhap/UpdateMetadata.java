package com.example.fe_quanlyvattu.data.model.phieunhap;

public class UpdateMetadata {
    private String id;
    private String note;
    private String status;
    private String approveBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApproveBy() {
        return approveBy;
    }

    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    public UpdateMetadata(String id, String note, String status, String approveBy) {
        this.id = id;
        this.note = note;
        this.status = status;
        this.approveBy = approveBy;
    }
}
