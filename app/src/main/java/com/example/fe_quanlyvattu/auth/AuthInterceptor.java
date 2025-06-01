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
        Request original = chain.request();

        String accessToken = sessionManager.getAccessToken();  // nếu cần Authorization Bearer
        String refreshToken = sessionManager.getRefreshToken();  // x-rf-token
        String userCode = sessionManager.getUserCode();         // x-user-code

        Request.Builder requestBuilder = original.newBuilder()
                .header("accept", "application/json");

        if (refreshToken != null && !refreshToken.isEmpty()) {
            requestBuilder.header("x-rf-token", refreshToken);
        }

        if (userCode != null && !userCode.isEmpty()) {
            requestBuilder.header("x-user-code", userCode);
        }

        // Nếu backend yêu cầu Authorization, bỏ comment dòng dưới
        // if (accessToken != null && !accessToken.isEmpty()) {
        //     requestBuilder.header("Authorization", "Bearer " + accessToken);
        // }

        Request request = requestBuilder.build();

        return chain.proceed(request);
    }
}
