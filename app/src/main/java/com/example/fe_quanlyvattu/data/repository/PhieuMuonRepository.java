package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phieumuon.PhieuMuonResponse;
import com.example.fe_quanlyvattu.data.model.phieumuon.BorrowReceipt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhieuMuonRepository {
    private ApiService apiService;
    private Context context;

    public PhieuMuonRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getAllPhieuMuon(ApiCallback<List<BorrowReceipt>> callback) {
        apiService.getAllPhieuMuon().enqueue(new Callback<PhieuMuonResponse>() {
            @Override
            public void onResponse(Call<PhieuMuonResponse> call, Response<PhieuMuonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata().getMetadata());
                } else {
                    callback.onError("Lỗi lấy danh sách phiếu mượn: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PhieuMuonResponse> call, Throwable t) {
                Log.e("PhieuMuonRepository", "Lỗi lấy danh sách phiếu mượn", t);
                callback.onError(t.getMessage());
            }
        });
    }

}
