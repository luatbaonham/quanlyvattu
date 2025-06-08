package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.PhieuMuonAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phieumuon.BorrowReceipt;
import com.example.fe_quanlyvattu.data.repository.PhieuMuonRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityPhieuMuon extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText edtTimKiem;
    private Button btnTimKiem;
    private List<BorrowReceipt> borrowReceiptList = new ArrayList<>();
    private PhieuMuonAdapter adapter;
    private PhieuMuonRepository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_muon); // Tạo layout này sau

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerViewPhieuMuon);
        edtTimKiem = findViewById(R.id.edtTimKiemPhieuMuon);
        btnTimKiem = findViewById(R.id.btntk);

        // Setup RecyclerView
        adapter = new PhieuMuonAdapter(new ArrayList<BorrowReceipt>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new PhieuMuonRepository(this); // Khởi tạo repository
        getDanhSachPhieuMuon(); // Gọi dữ liệu từ API

        btnTimKiem.setOnClickListener(v -> {
            String keyword = edtTimKiem.getText().toString();
            List<BorrowReceipt> filtered = new ArrayList<>();
            for (BorrowReceipt pm : borrowReceiptList) {
                if (pm.getUserCode().toLowerCase().contains(keyword.toLowerCase())) {
                    filtered.add(pm);
                }
            }
            adapter.updateList(filtered);
        });

        FloatingActionButton fab = findViewById(R.id.fabTaoPhieuMuon);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityPhieuMuon.this, TaoPhieuNhapActivity.class);
            startActivity(intent);
        });
    }

    private void getDanhSachPhieuMuon() {
        repository.getAllPhieuMuon(new ApiCallback<List<BorrowReceipt>>() {
            @Override
            public void onSuccess(List<BorrowReceipt> data) {
                borrowReceiptList = data;
                adapter.updateList(data);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ActivityPhieuMuon.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

