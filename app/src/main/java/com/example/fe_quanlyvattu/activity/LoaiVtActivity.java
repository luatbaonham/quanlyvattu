package com.example.fe_quanlyvattu.activity;

import static com.example.fe_quanlyvattu.R.layout.activity_loai_vt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.LoaiVtAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.loaivattu.NhomVt;
import com.example.fe_quanlyvattu.data.repository.NhomVTRepository;

import java.util.ArrayList;
import java.util.List;

public class LoaiVtActivity extends AppCompatActivity {
    private RecyclerView rvLoaiVt;
    private LoaiVtAdapter adapter;
    private List<NhomVt> loaiVtList = new ArrayList<>(); // ✅ Khởi tạo để tránh null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_loai_vt);

        rvLoaiVt = findViewById(R.id.recyclerViewLoaiVatTu);
        rvLoaiVt.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LoaiVtAdapter(loaiVtList, this);
        rvLoaiVt.setAdapter(adapter);

        getLoaiVt(); // Load dữ liệu từ server
    }

    private void getLoaiVt() {
        NhomVTRepository repository = new NhomVTRepository(this);
        repository.getAllNhomVT(new ApiCallback<List<NhomVt>>() {
            @Override
            public void onSuccess(List<NhomVt> response) {
                loaiVtList.clear();
                loaiVtList.addAll(response);
                adapter.setLoaiVtList(response);
                adapter.notifyDataSetChanged(); // ✅ Cập nhật UI
            }

            @Override
            public void onError(String errorMessage) {
                // Có thể thêm thông báo lỗi
            }
        });
    }
}
