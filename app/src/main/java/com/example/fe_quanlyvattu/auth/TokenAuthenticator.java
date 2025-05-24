package com.example.fe_quanlyvattu.auth;

import androidx.annotation.Nullable;

import com.example.fe_quanlyvattu.auth.model.RefreshTokenRequest;
import com.example.fe_quanlyvattu.auth.model.TokenResponse;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Retrofit;

public class TokenAuthenticator implements Authenticator {
    private final SessionManager sessionManager;
    private final AuthApi authApi;

    public TokenAuthenticator(SessionManager sessionManager, Retrofit retrofit) {
        this.sessionManager = sessionManager;
        this.authApi = retrofit.create(AuthApi.class);
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        if (responseCount(response) >= 2) {
            return null;
        }

        String refreshToken = sessionManager.getRefreshToken();
        if (refreshToken == null) return null;

        // Gọi API refresh
        Call<TokenResponse> call = authApi.refreshToken(new RefreshTokenRequest(refreshToken));
        retrofit2.Response<TokenResponse> refreshResponse = call.execute();

        if (refreshResponse.isSuccessful() && refreshResponse.body() != null) {
            String newAccessToken = refreshResponse.body().getMetadata().getTokens().getAccessToken();

            // Lưu lại token
            sessionManager.saveTokens(newAccessToken, refreshToken);

            // Retry request với token mới
            return response.request().newBuilder()
                    .header("Authorization", "Bearer " + newAccessToken)
                    .build();
        }

        return null;
    }

    private int responseCount(Response response) {
        int count = 1;
        while ((response = response.priorResponse()) != null) {
            count++;
        }
        return count;
    }
}
