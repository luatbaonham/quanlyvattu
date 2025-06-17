package com.example.fe_quanlyvattu.data.model.profile;



public class UProfile {
    private int id;
    private String first_name;
    private String last_name;
    private String avatar_url;
    private String phone_number;
    private String address;
    private String created_time;
    private String updated_time;
    private String user_code;

    // Getters (có thể thêm setters nếu bạn cần cập nhật)
    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public String getUser_code() {
        return user_code;
    }
}

