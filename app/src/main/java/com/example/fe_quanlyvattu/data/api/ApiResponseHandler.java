package com.example.fe_quanlyvattu.data.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponseHandler {

    public static <T> void handleResponse(Call<T> call, ApiCallback<T> callback) {
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onError("Lỗi: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    callback.onError("Lỗi kết nối mạng");
                } else {
                    callback.onError("Lỗi không xác định: " + t.getMessage());
                }
            }
        });
    }
}