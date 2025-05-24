package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fe_quanlyvattu.auth.AuthApi;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.auth.LoginRequest;
import com.example.fe_quanlyvattu.data.model.auth.LogoutRequest;
import com.example.fe_quanlyvattu.data.model.common.ApiResponse;
import com.example.fe_quanlyvattu.data.model.common.Metadata;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AuthRepository {

    private final AuthApi authApi;
    private final SessionManager sessionManager;

    public AuthRepository(Context context) {
        Retrofit retrofit = RetrofitClient.getInstance(context);
        authApi = retrofit.create(AuthApi.class);
        sessionManager = new SessionManager(context);
    }

    // Generic callback
    public interface LoginCallback<T> {
        void onSuccess(T data); // T ở đây là Metadata
        void onError(String message);
    }


    // Dùng đúng kiểu LoginResponse
    // AuthRepository.java
    public void login(String username, String password, LoginCallback<Metadata> callback) {
        LoginRequest request = new LoginRequest(username, password);
        Call<ApiResponse<Metadata>> call = authApi.login(request);

        call.enqueue(new Callback<ApiResponse<Metadata>>() {
            @Override
            public void onResponse(Call<ApiResponse<Metadata>> call, Response<ApiResponse<Metadata>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Metadata metadata = response.body().getMetadata();
                    if (metadata != null) {
                        callback.onSuccess(metadata);
                    } else {
                        callback.onError("Metadata null");
                    }
                } else {
                    callback.onError("Đăng nhập thất bại: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Metadata>> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }



    public void logout(String userCode, LoginCallback<ApiResponse<Integer>> callback) {
        LogoutRequest request = new LogoutRequest(userCode);
        Log.d("LogoutDebug", "JSON: " + new Gson().toJson(new LogoutRequest(userCode)));
        Call<ApiResponse<Integer>> call = authApi.logout(request);

        call.enqueue(new Callback<ApiResponse<Integer>>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse<Integer>> call, @NonNull Response<ApiResponse<Integer>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Logout thất bại: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Integer>> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }

}

