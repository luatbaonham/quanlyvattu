package com.example.fe_quanlyvattu.data.api;

import android.content.Context;

import com.example.fe_quanlyvattu.auth.AuthInterceptor;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.auth.TokenAuthenticator;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://192.168.1.6:3055/v1/api/"; // Nếu chạy Android emulator

    public static Retrofit getInstance(Context context) {
        if (retrofit == null) {
            SessionManager sessionManager = new SessionManager(context);

            // Retrofit không auth để dùng trong Authenticator
            Retrofit withoutAuthRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .build();

            // Authenticator để tự refresh token khi gặp 401
            TokenAuthenticator tokenAuthenticator = new TokenAuthenticator(sessionManager, withoutAuthRetrofit);
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(sessionManager))   // Gắn access token vào Header
                    .authenticator(tokenAuthenticator)                    // Tự động làm mới token khi bị 401
                    .addInterceptor(loggingInterceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }
}
