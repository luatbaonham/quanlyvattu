//package com.example.fe_quanlyvattu.activity;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.fe_quanlyvattu.R;
//import com.example.fe_quanlyvattu.adpter.PhongBanAdapter;
//import com.example.fe_quanlyvattu.data.model.phongban.Department;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PhongBanActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private PhongBanAdapter adapter;
//    private List<Department> phongBanList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ct_phongban);
//
//        recyclerView = findViewById(R.id.recyclerViewPhongBan);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Dữ liệu mẫu hoặc gọi API
//        phongBanList = new ArrayList<>();
//        phongBanList.add(new PhongBan(1, "Phòng Kỹ Thuật", "Chuyên kỹ thuật", "Đang hoạt động", "2024-01-01", "2025-06-13"));
//
//        adapter = new PhongBanAdapter(this, phongBanList);
//        recyclerView.setAdapter(adapter);
//    }
//}
