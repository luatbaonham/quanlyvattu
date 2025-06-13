package com.example.fe_quanlyvattu.activity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.DonViTinhAdapter;
import com.example.fe_quanlyvattu.adpter.PhongAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phong.Room;
import com.example.fe_quanlyvattu.data.model.phongban.Department;
import com.example.fe_quanlyvattu.data.repository.PhongBanRepository;
import com.example.fe_quanlyvattu.data.repository.PhongRepository;

import java.util.ArrayList;
import java.util.List;

public class PhongActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhongAdapter adapter;
    private List<Room> phongList = new ArrayList<>();
    private String phongBanId;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ct_phongban);

        recyclerView = findViewById(R.id.recyclerViewPhong);
        adapter = new PhongAdapter(this, phongList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        phongBanId = getIntent().getStringExtra("phongban_id");
        String phongBanName = getIntent().getStringExtra("phongban_name");

        setTitle("Phòng thuộc: " + phongBanName);

        loadPhongTheoPhongBan(phongBanId);
    }

    private void loadPhongTheoPhongBan(String phongbanId) {
        PhongRepository phongRepository = new PhongRepository(context);

        phongRepository.getAllPhong(new ApiCallback<List<Room>>() {
            @Override
            public void onSuccess(List<Room> data) {
                phongList.clear();
                phongList.addAll(data);
                adapter.updateList(data);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

