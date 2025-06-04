package com.example.fe_quanlyvattu.data.api;

import com.example.fe_quanlyvattu.data.model.phieunhap.CreatePhieuNhapRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PhieuNhapApi {
    @POST("import-receipt")
    Call<Void> taoPhieuNhap(@Body CreatePhieuNhapRequest request);
}
