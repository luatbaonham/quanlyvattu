package com.example.fe_quanlyvattu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.LoaiVtAdapter;
import com.example.fe_quanlyvattu.model.LoaiVt;

import java.util.ArrayList;
import java.util.List;

public class LoaiVtActivity extends AppCompatActivity {
    RecyclerView rvLoaiVt;
    LoaiVtAdapter adapter;
    List<LoaiVt> loaiVtList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_vt);

        rvLoaiVt = findViewById(R.id.recyclerViewLoaiVatTu);
        rvLoaiVt.setLayoutManager(new LinearLayoutManager(this));

        loaiVtList = getLoaiVt();

        adapter = new LoaiVtAdapter(loaiVtList, this);
        rvLoaiVt.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == Activity.RESULT_OK && data != null) {
            int position = data.getIntExtra("position", -1);
            LoaiVt updated = (LoaiVt) data.getSerializableExtra("updatedLoaiVt");
            if (position != -1 && updated != null) {
                adapter.updateItem(position, updated);
            }
        }
    }
    private List<LoaiVt> getLoaiVt() {
        List<LoaiVt> list = new ArrayList<>();
        list.add(new LoaiVt("Thiết bị", "Vật tư dạng thiết bị", 12, "2025-04-01"));
        list.add(new LoaiVt("Dụng cụ", "Các loại dụng cụ nhỏ", 25, "2025-04-02"));
        list.add(new LoaiVt("Hóa chất", "Dùng trong phòng thí nghiệm", 8, "2025-04-03"));
        return list;
    }
}
