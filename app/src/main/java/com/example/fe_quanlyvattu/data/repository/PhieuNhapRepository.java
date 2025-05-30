package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.auth.AuthApi;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapResponse;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PhieuNhapRepository {
    private ApiService apiService;
    private Context context;

    public PhieuNhapRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }


    public void getAllPhieuNhap(ApiCallback<List<PhieuNhap>> callback) {
        apiService.getAllPhieuNhap().enqueue(new Callback<PhieuNhapResponse>() {
            @Override
            public void onResponse(Call<PhieuNhapResponse> call, Response<PhieuNhapResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách phiếu nhập");
                }
            }

            @Override
            public void onFailure(Call<PhieuNhapResponse> call, Throwable t) {
                Log.e("PhieuNhapRepository", "Lỗi lấy danh sách phiếu nhập", t);
            }
        });
    }
}
