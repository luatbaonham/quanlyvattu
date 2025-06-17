package com.example.fe_quanlyvattu.data.model.profile;

public class Account {
    private String username;
    private String email;
    private String phone_number;
    private int role_id;

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public int getRole_id() { return role_id; }
    public void setRole_id(int role_id) { this.role_id = role_id; }
}
