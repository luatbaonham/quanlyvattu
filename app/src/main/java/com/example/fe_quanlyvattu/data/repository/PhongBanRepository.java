package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phongban.Department;
import com.example.fe_quanlyvattu.data.model.phongban.PhongBanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhongBanRepository {
    private ApiService apiService;

    public PhongBanRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllPhongBan(ApiCallback<List<Department>> callback) {
        apiService.getAllPhongBan().enqueue(new Callback<PhongBanResponse>() {
            @Override
            public void onResponse(Call<PhongBanResponse> call, Response<PhongBanResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách phòng ban: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PhongBanResponse> call, Throwable t) {
                Log.e("PhongBanRepository", "Lỗi kết nối API phòng ban", t);
                callback.onError(t.getMessage());
            }
        });
    }
}
