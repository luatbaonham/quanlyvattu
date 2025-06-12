package com.example.fe_quanlyvattu.data.api;

import com.example.fe_quanlyvattu.data.model.phieumuon.CreatePhieuMuonRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PhieuMuonApi {
    @POST("borrow-receipt")
    Call<Void> taoPhieuMuon(@Body CreatePhieuMuonRequest request);
}
