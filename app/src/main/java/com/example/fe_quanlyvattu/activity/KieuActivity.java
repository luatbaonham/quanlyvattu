package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.KieuAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.vattu.kieu.Kieu;
import com.example.fe_quanlyvattu.data.repository.KieuRepository;

import java.util.ArrayList;
import java.util.List;

public class KieuActivity extends AppCompatActivity {
    private RecyclerView rvKieu;
    private KieuAdapter adapter;
    private List<Kieu> kieuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kieu_vt); // Tạo layout XML tương ứng

        rvKieu = findViewById(R.id.recyclerViewKieu);
        rvKieu.setLayoutManager(new LinearLayoutManager(this));

        adapter = new KieuAdapter(kieuList);
        rvKieu.setAdapter(adapter);

        getKieuList(); // Gọi API
    }

    private void getKieuList() {
        KieuRepository repository = new KieuRepository(this);

        repository.getAllKieu(new ApiCallback<List<Kieu>>() {
            @Override
            public void onSuccess(List<Kieu> response) {
                kieuList.clear();
                kieuList.addAll(response);
                adapter.setKieuList(kieuList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(KieuActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
