package com.example.fe_quanlyvattu.data.api;

import com.example.fe_quanlyvattu.data.model.auth.LoginRequest;
import com.example.fe_quanlyvattu.data.model.auth.LoginResponse;
import com.example.fe_quanlyvattu.model.PhieuNhap;
import com.example.fe_quanlyvattu.model.PhieuThanhLy;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("equipment-manufacturer")
    Call<JsonObject> getManufacturers(
            @Query("page") int page,
            @Query("limit") int limit,
            @Header("x-user-code") String userCode,
            @Header("x-rf-token") String token
    );


    @GET("import-receipt") // đường dẫn backend
    Call<List<PhieuNhap>> getPhieuNhapList();

    @GET("djdj") // đường dẫn backend
    Call<List<PhieuThanhLy>> getPhieuThanhLyList();
}
