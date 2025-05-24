package com.example.fe_quanlyvattu.data.model.auth;

import com.google.gson.annotations.SerializedName;

public class LogoutRequest {
    @SerializedName("userCode")  // ✅ Thêm dòng này
    private String userCode;

    public LogoutRequest(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
