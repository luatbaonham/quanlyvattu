package com.example.fe_quanlyvattu.data.api;

import com.example.fe_quanlyvattu.data.model.auth.LoginRequest;
import com.example.fe_quanlyvattu.data.model.auth.LoginResponse;
import com.example.fe_quanlyvattu.data.model.kho.EquipmentResponse;
import com.example.fe_quanlyvattu.data.model.vattu.donvitinh.DonViTinhResponse;
import com.example.fe_quanlyvattu.data.model.vattu.hang.HangResponse;
import com.example.fe_quanlyvattu.data.model.vattu.kieu.KieuResponse;
import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.NhomVtResponse;
import com.example.fe_quanlyvattu.data.model.phieumuon.PhieuMuonResponse;
import com.example.fe_quanlyvattu.data.model.phieunhap.CapNhatTrangThaiRequest;
import com.example.fe_quanlyvattu.data.model.phieunhap.CreatePhieuNhapRequest;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapResponse;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapUpdateResponse;
import com.example.fe_quanlyvattu.data.model.phong.PhongResponse;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("equipment-manufacturer")
    Call<JsonObject> getManufacturers(
            @Query("page") int page,
            @Query("limit") int limit,
            @Header("x-user-code") String userCode,
            @Header("x-rf-token") String token
    );


    @GET("import-receipt") // đường dẫn backend
    Call<PhieuNhapResponse> getAllPhieuNhap();
    @POST("import-receipt") // Đường dẫn đúng với backend bạn
    Call<Void> taoPhieuNhap(@Body CreatePhieuNhapRequest request);
    @GET("borrow-receipt")
    Call<PhieuMuonResponse> getAllPhieuMuon();
    @POST("borrow-receipt")
    Call<Void> taoPhieuMuon(@Body CreatePhieuNhapRequest request);
    @GET("room")
    Call<PhongResponse> getAllPhong();
    @GET("group-equipment")
    Call<NhomVtResponse> getAllNomVT();
    @PUT("import-receipt/{id}/status")
    Call<PhieuNhapUpdateResponse> updateImportReceiptStatus(
            @Path("id") int importReceiptId,
            @Body CapNhatTrangThaiRequest request);
    @GET("unit-of-measure")
    Call<DonViTinhResponse> getAllDonViTinh();

    @GET("equipment-type")
    Call<KieuResponse> getAllKieu();

    @GET("equipment-manufacturer")
    Call<HangResponse> getAllHang();
    @GET("v1/api/equipment/room/{roomId}")
    Call<EquipmentResponse> getEquipmentsByRoomId(@Path("roomId") String roomId);

}
