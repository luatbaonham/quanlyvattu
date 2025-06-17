package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.NhomVt;
import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.NhomVtResponse;
import com.google.gson.Gson;

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
        apiService.getAllNhomVT().enqueue(new Callback<NhomVtResponse>() {
            @Override
            public void onResponse(Call<NhomVtResponse> call, Response<NhomVtResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<NhomVt> list = response.body().getMetadata().getMetadata();
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                    Log.d("API_RESPONSE", "Raw response: " + new Gson().toJson(response.body()));
                    for (NhomVt item : list) {
                        Log.d("API_ITEM", "Name: " + item.getName() +
                                " | Counts: " + (item.getEquipmentStatusCounts() != null ?
                                item.getEquipmentStatusCounts() : "null"));
                    }
                    callback.onSuccess(list);

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
