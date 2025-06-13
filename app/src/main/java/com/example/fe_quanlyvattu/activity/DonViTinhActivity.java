package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.DonViTinhAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.vattu.donvitinh.DonViTinh;
import com.example.fe_quanlyvattu.data.repository.DonViTinhRepository;

import java.util.ArrayList;
import java.util.List;

public class DonViTinhActivity extends AppCompatActivity {

    private RecyclerView rvDonViTinh;
    private DonViTinhAdapter adapter;
    private List<DonViTinh> donViTinhList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donvt); // gắn layout XML

        rvDonViTinh = findViewById(R.id.recyclerViewDVT);
        rvDonViTinh.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DonViTinhAdapter(donViTinhList);
        rvDonViTinh.setAdapter(adapter);

        getDonViTinhFromApi();
    }

    private void getDonViTinhFromApi() {
        DonViTinhRepository repository = new DonViTinhRepository(this);

        repository.getAllDonViTinh(new ApiCallback<List<DonViTinh>>() {
            @Override
            public void onSuccess(List<DonViTinh> response) {
                donViTinhList.clear();
                donViTinhList.addAll(response);
                adapter.setDonViTinhList(donViTinhList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(DonViTinhActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}