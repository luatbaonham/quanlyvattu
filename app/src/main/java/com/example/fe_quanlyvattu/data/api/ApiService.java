package com.example.fe_quanlyvattu.data.api;

import com.example.fe_quanlyvattu.data.model.auth.LoginRequest;
import com.example.fe_quanlyvattu.data.model.auth.LoginResponse;
import com.example.fe_quanlyvattu.data.model.phieumuon.PhieuMuonResponse;
import com.example.fe_quanlyvattu.data.model.phieunhap.CreatePhieuNhapRequest;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapResponse;
import com.example.fe_quanlyvattu.data.model.phong.PhongResponse;
import com.google.gson.JsonObject;

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
    Call<PhieuNhapResponse> getAllPhieuNhap();
    @POST("import-receipt") // Đường dẫn đúng với backend bạn
    Call<Void> taoPhieuNhap(@Body CreatePhieuNhapRequest request);
    @GET("borrow-receipt")
    Call<PhieuMuonResponse> getAllPhieuMuon();
    @POST("borrow-receipt")
    Call<Void> taoPhieuMuon(@Body CreatePhieuNhapRequest request);
    @GET("room")
    Call<PhongResponse> getAllPhong();

}
