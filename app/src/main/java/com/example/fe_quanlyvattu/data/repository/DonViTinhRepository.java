package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.donvitinh.DonViTinh;
import com.example.fe_quanlyvattu.data.model.donvitinh.DonViTinhResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonViTinhRepository {
    private ApiService apiService;

    public DonViTinhRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllDonViTinh(ApiCallback<List<DonViTinh>> callback) {
        apiService.getAllDonViTinh().enqueue(new Callback<DonViTinhResponse>() {
            @Override
            public void onResponse(Call<DonViTinhResponse> call, Response<DonViTinhResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy đơn vị tính: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<DonViTinhResponse> call, Throwable t) {
                Log.e("DonViTinhRepository", "Lỗi kết nối API đơn vị tính", t);
                callback.onError(t.getMessage());
            }
        });
    }
}
