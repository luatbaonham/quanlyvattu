package com.example.fe_quanlyvattu.data.model.profile;

public class GProfile {
    private int id;
    private String first_name;
    private String last_name;
    private String avatar_url;
    private String phone_number;
    private String address;
    private String created_time;
    private String updated_time;
    private String user_code;
    private Account account;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }

    public String getLast_name() { return last_name; }
    public void setLast_name(String last_name) { this.last_name = last_name; }

    public String getAvatar_url() { return avatar_url; }
    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }

    public String getPhone_number() { return phone_number; }
    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCreated_time() { return created_time; }
    public void setCreated_time(String created_time) { this.created_time = created_time; }

    public String getUpdated_time() { return updated_time; }
    public void setUpdated_time(String updated_time) { this.updated_time = updated_time; }

    public String getUser_code() { return user_code; }
    public void setUser_code(String user_code) { this.user_code = user_code; }

    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
    @Override
    public String toString() {
        return "GProfile{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", address='" + address + '\'' +
                ", created_time='" + created_time + '\'' +
                ", updated_time='" + updated_time + '\'' +
                ", user_code='" + user_code + '\'' +
                ", account=" + account +
                '}';
    }

}
