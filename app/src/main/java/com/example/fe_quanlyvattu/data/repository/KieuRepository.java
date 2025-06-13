package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.kieu.Kieu;
import com.example.fe_quanlyvattu.data.model.kieu.KieuResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KieuRepository {
    private ApiService apiService;

    public KieuRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllKieu(ApiCallback<List<Kieu>> callback) {
        apiService.getAllKieu().enqueue(new Callback<KieuResponse>() {
            @Override
            public void onResponse(Call<KieuResponse> call, Response<KieuResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách kiểu: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<KieuResponse> call, Throwable t) {
                Log.e("KieuRepository", "Lỗi kết nối API kiểu", t);
                callback.onError(t.getMessage());
            }
        });
    }
}
