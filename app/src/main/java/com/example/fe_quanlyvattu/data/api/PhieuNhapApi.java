package com.example.fe_quanlyvattu.data.api;


import com.example.fe_quanlyvattu.data.model.phieunhap.CapNhatTrangThaiRequest;
import com.example.fe_quanlyvattu.data.model.phieunhap.CreatePhieuNhapRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PhieuNhapApi {
    @POST("import-receipt")
    Call<Void> taoPhieuNhap(@Body CreatePhieuNhapRequest request);
    @PUT("import-receipt/{id}/status")
    Call<CapNhatTrangThaiRequest> updateImportReceiptStatus(
                            @Path("id") int importReceiptId,
                            @Body CapNhatTrangThaiRequest request);
}
