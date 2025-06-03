package com.example.fe_quanlyvattu.data.model.phieunhap;

public class RequestedUser {

    private String usercode;
    private String username;
    private String email;
    private boolean isActive;
    public RequestedUser(String usercode, String username, String email, boolean isActive) {
        this.usercode = usercode;
        this.username = username;
        this.email = email;
        this.isActive = isActive;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
