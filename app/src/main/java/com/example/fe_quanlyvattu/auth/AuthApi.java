package com.example.fe_quanlyvattu.auth;

import com.example.fe_quanlyvattu.auth.model.RefreshTokenRequest;
import com.example.fe_quanlyvattu.auth.model.TokenResponse;
import com.example.fe_quanlyvattu.data.model.auth.LoginRequest;
import com.example.fe_quanlyvattu.data.model.auth.LogoutRequest;
import com.example.fe_quanlyvattu.data.model.common.ApiResponse;
import com.example.fe_quanlyvattu.data.model.common.Metadata;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("auth/refresh-token")
    Call<TokenResponse> refreshToken(@Body RefreshTokenRequest body);
    @POST("login")
    Call<ApiResponse<Metadata>> login(@Body LoginRequest request);

    @POST("logout")
    Call<ApiResponse<Integer>> logout(@Body LogoutRequest logoutRequest);
}

