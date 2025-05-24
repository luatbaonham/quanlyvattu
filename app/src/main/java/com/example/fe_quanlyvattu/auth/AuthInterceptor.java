package com.example.fe_quanlyvattu.auth;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final SessionManager sessionManager;

    public AuthInterceptor(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request(); // Lấy request gốc mà app gửi đi

        String token = sessionManager.getAccessToken(); // Lấy access token từ local (EncryptedSharedPreferences)

        if (token != null) {
            // Nếu có token, tạo request mới giống request cũ nhưng gắn thêm header Authorization
            Request request = original.newBuilder()
                    .header("Authorization", "Bearer " + token)
                    .build();

            return chain.proceed(request); // Gửi request mới (đã có token)
        }

        return chain.proceed(original); // Nếu không có token thì gửi request như cũ
    }

}
