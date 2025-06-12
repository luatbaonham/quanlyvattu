package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.loaivattu.NhomVt;
import com.example.fe_quanlyvattu.data.model.loaivattu.NhomVtResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhomVTRepository {
    private ApiService apiService;
    private Context context;

    public NhomVTRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllNhomVT(ApiCallback<List<NhomVt>> callback) {
        apiService.getAllNomVT().enqueue(new Callback<NhomVtResponse>() {
            @Override
            public void onResponse(Call<NhomVtResponse> call, Response<NhomVtResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách phiếu mượn: " + response.message());
                }
            }
            @Override
            public void onFailure(Call<NhomVtResponse> call, Throwable t) {
                Log.e("NhomVTRepository", "Lỗi lấy danh sách phiếu mượn", t);
                callback.onError(t.getMessage());
            }
        });
    }
}
