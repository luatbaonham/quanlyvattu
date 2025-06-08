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
import com.example.fe_quanlyvattu.adpter.PhieuNhapAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;
import com.example.fe_quanlyvattu.data.repository.PhieuNhapRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivityPhieuNhap extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edtTimKiem;
    private Button btnTimKiem;
    private List<PhieuNhap> phieuNhapList = new ArrayList<>();
    private PhieuNhapAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phieu_nhap);

        // Ánh xạ view
        recyclerView = findViewById(R.id.recyclerViewPhieuNhapTL);
        edtTimKiem = findViewById(R.id.edtTimKiem);
        btnTimKiem = findViewById(R.id.btntk);

        // Setup RecyclerView
        adapter = new PhieuNhapAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getDanhSachPhieuNhap();

        btnTimKiem.setOnClickListener(v -> {
            String keyword = edtTimKiem.getText().toString();
            List<PhieuNhap> filtered = new ArrayList<>();
            for (PhieuNhap pn : phieuNhapList) {
                if (pn.getSupplier().getName().toLowerCase().contains(keyword.toLowerCase())) {
                    filtered.add(pn);
                }
            }
            adapter.updateList(filtered);
        });

        FloatingActionButton fab = findViewById(R.id.fabTaoPhieuNhap);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityPhieuNhap.this, TaoPhieuNhapActivity.class);
            startActivity(intent);
        });

    }

    private void getDanhSachPhieuNhap() {
        PhieuNhapRepository repository = new PhieuNhapRepository(ActivityPhieuNhap.this);

        repository.getAllPhieuNhap(new ApiCallback<List<PhieuNhap>>() {
            @Override
            public void onSuccess(List<PhieuNhap> data) {
                phieuNhapList.clear();
                phieuNhapList.addAll(data);
                adapter.updateList(data);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(ActivityPhieuNhap.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
