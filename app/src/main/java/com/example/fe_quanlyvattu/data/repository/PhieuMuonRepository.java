package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.equipment.UpdateRoomRequest;
import com.example.fe_quanlyvattu.data.model.phieumuon.CapNhatTrangThaiPhieuMuonRequest;
import com.example.fe_quanlyvattu.data.model.phieumuon.PhieuMuonResponse;
import com.example.fe_quanlyvattu.data.model.phieumuon.BorrowReceipt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void capNhatTrangThai(ApiCallback<Object> callback, CapNhatTrangThaiPhieuMuonRequest request, int id) {
        apiService.updateBorrowReceiptStatus(id, request).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Cập nhật thất bại: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("PhieuMuonRepository", "Lỗi cập nhật trạng thái", t);
                callback.onError("Lỗi: " + t.getMessage());
            }
        });
    }
    public void capNhatRoomChoEquipment(ApiCallback<Object> callback, String serialNumber, String roomId) {
        UpdateRoomRequest request = new UpdateRoomRequest(roomId);
        apiService.updateEquipmentRoom(serialNumber, request).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Cập nhật phòng thất bại: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                callback.onError("Lỗi kết nối: " + t.getMessage());
            }
        });
    }
    public void thucHienHanhDong(int phieuMuonId, String action, String reason, ApiCallback<Object> callback) {
        Map<String, String> body = new HashMap<>();
        body.put("action", action);
        body.put("reason", reason);

        apiService.performActionOnBorrowReceipt(phieuMuonId, body).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onError("Lỗi: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }



}
