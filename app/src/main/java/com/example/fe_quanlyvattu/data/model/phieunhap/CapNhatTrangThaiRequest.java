package com.example.fe_quanlyvattu.data.model.phieunhap;

public class CapNhatTrangThaiRequest {
    private int importReceiptId;
    private String status;
    private String reason;
    public CapNhatTrangThaiRequest(int importReceiptId, String status, String reason) {
        this.importReceiptId = importReceiptId;
        this.status = status;
        this.reason = reason;
    }


    public int getImportReceiptId() {
        return importReceiptId;
    }

    public void setImportReceiptId(int importReceiptId) {
        this.importReceiptId = importReceiptId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
