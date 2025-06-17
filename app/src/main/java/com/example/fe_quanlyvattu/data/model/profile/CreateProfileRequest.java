package com.example.fe_quanlyvattu.data.model.profile;

public class CreateProfileRequest {
    private String first_name;
    private String last_name;
    private String avatar_url;
    private String phone_number;
    private String address;

    public CreateProfileRequest(String first_name, String last_name, String avatar_url, String phone_number, String address) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar_url = avatar_url;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
