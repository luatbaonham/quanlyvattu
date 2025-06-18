package com.example.fe_quanlyvattu.utils;

public class StatusUtil {
    public static String getStatusDisplayName(String status) {
        if (status == null) return "Không xác định";

        switch (status.toLowerCase()) {
            case "pending":
                return "Chờ xử lý";
            case "approved":
                return "Đã duyệt";
            case "rejected":
                return "Từ chối";
            case "completed":
                return "Hoàn thành";
            case "in_use":
                return "Đang sử dụng";
            default:
                return status;
        }
    }
}