package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.vattu.hang.HangVt;
import com.example.fe_quanlyvattu.data.model.vattu.hang.HangResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HangRepository {
    private ApiService apiService;

    public HangRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllHang(ApiCallback<List<HangVt>> callback) {
        apiService.getAllHang().enqueue(new Callback<HangResponse>() {
            @Override
            public void onResponse(Call<HangResponse> call, Response<HangResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách hãng: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<HangResponse> call, Throwable t) {
                Log.e("HangRepository", "Lỗi kết nối API hãng", t);
                callback.onError(t.getMessage());
            }
        });
    }
}
