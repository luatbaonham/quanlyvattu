package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.home_baocao.BaoCaoResponse;
import com.example.fe_quanlyvattu.data.model.home_baocao.ReportItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaoCaoRepository {
    private ApiService apiService;

    public BaoCaoRepository(Context context) {
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void getBaoCao(ApiCallback<List<ReportItem>> callback) {
        apiService.getAllBaoCao().enqueue(new Callback<BaoCaoResponse>() {
            @Override
            public void onResponse(Call<BaoCaoResponse> call, Response<BaoCaoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ReportItem> result = new ArrayList<>();
                    Map<String, List<BaoCaoResponse.StatusCount>> data = response.body().getMetadata();
                    for (Map.Entry<String, List<BaoCaoResponse.StatusCount>> entry : data.entrySet()) {
                        String type = entry.getKey(); // import, borrow, etc.
                        for (BaoCaoResponse.StatusCount item : entry.getValue()) {
                            result.add(new ReportItem(type, item.getStatus(), item.getCount()));
                        }
                    }
                    callback.onSuccess(result);
                } else {
                    callback.onError("Lỗi dữ liệu báo cáo: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<BaoCaoResponse> call, Throwable t) {
                Log.e("BaoCaoRepo", "Lỗi kết nối API báo cáo", t);
                callback.onError(t.getMessage());
            }
        });
    }
}

