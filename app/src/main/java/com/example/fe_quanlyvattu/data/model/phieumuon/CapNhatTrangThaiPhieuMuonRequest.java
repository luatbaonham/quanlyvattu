package com.example.fe_quanlyvattu.data.model.phieumuon;

public class CapNhatTrangThaiPhieuMuonRequest {
    private String reason;
    private String action;

    public CapNhatTrangThaiPhieuMuonRequest(String reason, String action) {
        this.reason = reason;
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
