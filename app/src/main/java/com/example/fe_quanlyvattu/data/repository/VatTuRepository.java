package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.kho.Equipment;
import com.example.fe_quanlyvattu.data.model.kho.EquipmentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VatTuRepository {
    private ApiService apiService;

    public VatTuRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getEquipmentsByRoomId(String roomId, ApiCallback<List<Equipment>> callback) {
        apiService.getEquipmentsByRoomId(roomId).enqueue(new Callback<EquipmentResponse>() {
            @Override
            public void onResponse(Call<EquipmentResponse> call, Response<EquipmentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy thiết bị: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<EquipmentResponse> call, Throwable t) {
                Log.e("VatTuRepository", "Lỗi kết nối API thiết bị", t);
                callback.onError(t.getMessage());
            }
        });
    }
}

