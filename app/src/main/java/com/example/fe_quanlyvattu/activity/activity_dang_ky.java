package com.example.fe_quanlyvattu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.common.Metadata;
import com.example.fe_quanlyvattu.data.repository.AuthRepository;

public class activity_dang_ky extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtConfirmPassword;
    private Button btnRegister, btnGoToLogin;
    private AuthRepository authRepository;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tắt chế độ tối
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_dangki); // <-- Đảm bảo tên layout đúng

        // Khởi tạo repository
        authRepository = new AuthRepository(getApplicationContext());

        // Ánh xạ các thành phần UI
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister); // nút "Đăng ký"
        btnGoToLogin = findViewById(R.id.btnGoToLogin); // nút "Quay lại đăng nhập"

        // Sự kiện khi bấm Đăng ký
        btnRegister.setOnClickListener(v -> dangKy());

        // Chuyển sang màn hình đăng nhập
        btnGoToLogin.setOnClickListener(v -> {
            startActivity(new Intent(activity_dang_ky.this, activity_dang_nhap.class));
            finish();
        });
    }

    private void dangKy() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        // Kiểm tra dữ liệu nhập
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        // Gọi API đăng ký
        authRepository.signup(username, password, "staff", new AuthRepository.LoginCallback<Metadata>() {
            @Override
            public void onSuccess(Metadata data) {
                runOnUiThread(() -> {
                    Toast.makeText(activity_dang_ky.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(activity_dang_ky.this, activity_dang_nhap.class));
                    finish();
                });
            }

            @Override
            public void onError(String message) {
                runOnUiThread(() -> {
                    Toast.makeText(activity_dang_ky.this, "Lỗi đăng ký: " + message, Toast.LENGTH_LONG).show();
                });
            }
        });
    }
}
