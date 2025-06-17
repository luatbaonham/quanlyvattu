package com.example.fe_quanlyvattu.activity;

import static com.example.fe_quanlyvattu.R.layout.activity_loai_vt;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.LoaiVtAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.NhomVt;
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

        rvLoaiVt = findViewById(R.id.recyclerViewLoaivt);
        rvLoaiVt.setLayoutManager(new LinearLayoutManager(this));

        adapter = new LoaiVtAdapter(loaiVtList, this);
        rvLoaiVt.setAdapter(adapter);

        getLoaiVt(); // Load dữ liệu từ server
    }

    private void getLoaiVt() {
        NhomVTRepository repository = new NhomVTRepository(this);
        Log.d("API_CALL", "Bắt đầu gọi API lấy danh sách loại VT");

        repository.getAllNhomVT(new ApiCallback<List<NhomVt>>() {
            @Override
            public void onSuccess(List<NhomVt> response) {
                Log.d("API_SUCCESS", "Nhận được " + response.size() + " items");

                // Log 3 item đầu tiên để kiểm tra
                for (int i = 0; i < Math.min(response.size(), 3); i++) {
                    NhomVt item = response.get(i);
                    Log.d("ITEM_DATA",
                            "Name: " + item.getName() +
                                    " | Counts: " + (item.getEquipmentStatusCounts() != null ?
                                    item.getEquipmentStatusCounts() : "null") +
                                    " | Unit: " + (item.getUnitOfMeasure() != null ?
                                    item.getUnitOfMeasure().getName() : "null"));
                }


                loaiVtList.clear();
                loaiVtList.addAll(response);
                adapter.setLoaiVtList(response);
                adapter.notifyDataSetChanged(); // ✅ Cập nhật UI
            }

            @Override
            public void onError(String errorMessage) {
                Log.e("API_ERROR", "Lỗi: " + errorMessage);
            }
        });
    }
}
