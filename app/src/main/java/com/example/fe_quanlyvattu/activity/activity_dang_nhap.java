package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.model.common.Metadata;
import com.example.fe_quanlyvattu.data.repository.AuthRepository;
import androidx.appcompat.app.AppCompatDelegate;

public class activity_dang_nhap extends AppCompatActivity {
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin;
    private SessionManager sessionManager;
    private AuthRepository authRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        sessionManager = new SessionManager(this);

        // Nếu đã đăng nhập, chuyển thẳng vào MainActivity
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        authRepository = new AuthRepository(getApplicationContext());

        editTextUsername = findViewById(R.id.edtUsername);
        editTextPassword = findViewById(R.id.edtPassword);
        buttonLogin = findViewById(R.id.btnDangNhap);

        buttonLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        authRepository.login(username, password, new AuthRepository.LoginCallback<Metadata>() {
            @Override
            public void onSuccess(Metadata metadata) {
                if (metadata != null && metadata.getTokens() != null) {
                    // Lưu thông tin vào session
                    SessionManager sessionManager = new SessionManager(activity_dang_nhap.this);
                    sessionManager.saveLoginSession(metadata);

                    Toast.makeText(activity_dang_nhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_dang_nhap.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(activity_dang_nhap.this, "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(activity_dang_nhap.this, "Đăng nhập thất bại: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
