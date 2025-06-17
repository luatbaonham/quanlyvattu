package com.example.fe_quanlyvattu.data.repository;

import android.content.Context;
import android.util.Log;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.profile.CheckProfileResponse;
import com.example.fe_quanlyvattu.data.model.profile.CreateProfileRequest;
import com.example.fe_quanlyvattu.data.model.profile.CreateProfileResponse;
import com.example.fe_quanlyvattu.data.model.profile.GProfile;
import com.example.fe_quanlyvattu.data.model.profile.GetProfileResponse;
import com.example.fe_quanlyvattu.data.model.profile.Profile;
import com.example.fe_quanlyvattu.data.model.profile.UProfile;
import com.example.fe_quanlyvattu.data.model.profile.UpdateProfileRequest;
import com.example.fe_quanlyvattu.data.model.profile.UpdateProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    private ApiService apiService;

    public ProfileRepository(Context context){
        this.apiService = RetrofitClient.getInstance(context).create(ApiService.class);
    }

    public void checkProfileExisted(ApiCallback<Boolean> callback){
        apiService.checkProfileExisted().enqueue(new Callback<CheckProfileResponse>() {
            @Override
            public void onResponse(Call<CheckProfileResponse> call, Response<CheckProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMetadata() != null) {
                    callback.onSuccess(response.body().getMetadata().getHasProfile());
                } else {
                    callback.onError("Lỗi kiểm tra hồ sơ: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CheckProfileResponse> call, Throwable t) {
                Log.e("ProfileRepository", "Lỗi kiểm tra hồ sơ", t);
                callback.onError(t.getMessage());
            }
        });
    }

    public void createProfile(CreateProfileRequest request, ApiCallback<Profile> callback) {
        apiService.createProfile(request).enqueue(new Callback<CreateProfileResponse>() {
            @Override
            public void onResponse(Call<CreateProfileResponse> call, Response<CreateProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMetadata() != null) {
                    callback.onSuccess(response.body().getMetadata());
                } else {
                    callback.onError("Lỗi tạo hồ sơ: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<CreateProfileResponse> call, Throwable t) {
                Log.e("ProfileRepository", "Lỗi tạo hồ sơ", t);
                callback.onError(t.getMessage());
            }
        });
    }

    public void getProfile(ApiCallback<GProfile> callback) {
        apiService.getProfile().enqueue(new Callback<GetProfileResponse>() {
            @Override
            public void onResponse(Call<GetProfileResponse> call, Response<GetProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata());
                } else {
                    callback.onError("Lỗi lấy hồ sơ: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetProfileResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void updateProfile(UpdateProfileRequest updatedData, ApiCallback<UProfile> callback) {
        apiService.updateProfile(updatedData).enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getMetadata());
                } else {
                    callback.onError("Lỗi cập nhật hồ sơ: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }
}
