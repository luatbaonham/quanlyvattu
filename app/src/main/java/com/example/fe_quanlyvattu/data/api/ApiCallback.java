package com.example.fe_quanlyvattu.data.api;

public interface ApiCallback<T> {
    void onSuccess(T response);
    void onError(String errorMessage);
}