package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.repository.PhieuMuonRepository;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanActivity extends AppCompatActivity {

    private String roomId; // roomId dạng String, ví dụ: "PGVCNTT"
    private PhieuMuonRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        // Lấy roomId từ Intent
        roomId = getIntent().getStringExtra("ROOM_ID");
        if (roomId == null || roomId.isEmpty()) {
            Toast.makeText(this, "Thiếu thông tin phòng", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        repository = new PhieuMuonRepository(this);
        startQRScanner();
    }

    private void startQRScanner() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setPrompt("Quét mã QR trên vật tư");
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null && result.getContents() != null) {
            String serialNumber = result.getContents(); // lấy mã serial từ QR

            // Gọi API cập nhật room_id cho thiết bị
            repository.capNhatRoomChoEquipment(new ApiCallback<Object>() {
                @Override
                public void onSuccess(Object result) {
                    Toast.makeText(ScanActivity.this, "Cập nhật phòng thành công", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(ScanActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, serialNumber, roomId);

        } else {
            Toast.makeText(this, "Không đọc được mã QR", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
