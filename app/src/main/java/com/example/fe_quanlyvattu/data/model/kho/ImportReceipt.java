package com.example.fe_quanlyvattu.data.model.kho;

public class ImportReceipt {
    private int id;
    private String userCode;
    private String approvedBy;
    private String receivedAt;
    private String note;

    public ImportReceipt(int id, String userCode, String approvedBy, String receivedAt, String note) {
        this.id = id;
        this.userCode = userCode;
        this.approvedBy = approvedBy;
        this.receivedAt = receivedAt;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
