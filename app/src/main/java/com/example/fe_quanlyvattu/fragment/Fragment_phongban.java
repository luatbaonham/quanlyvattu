package com.example.fe_quanlyvattu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.PhongActivity;
import com.example.fe_quanlyvattu.adpter.PhongBanAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phongban.Department;
import com.example.fe_quanlyvattu.data.repository.PhongBanRepository;

import java.util.ArrayList;
import java.util.List;

public class Fragment_phongban extends Fragment {

    private RecyclerView recyclerView;
    private PhongBanAdapter adapter;
    private List<Department> phongBanList = new ArrayList<>();
    private Context context;

    @Override
    public void onAttach(@NonNull Context ctx) {
        super.onAttach(ctx);
        this.context = ctx; // Gán context an toàn
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phongban, container, false);

        // Khởi tạo RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewquanlyphongban);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Tạo adapter và gán cho RecyclerView
        adapter = new PhongBanAdapter(context, phongBanList);
        recyclerView.setAdapter(adapter);

        // Xử lý sự kiện khi click vào item
        adapter.setOnItemClickListener(department -> {
            Intent intent = new Intent(context, PhongActivity.class);
            intent.putExtra("departmentName", department.getName());
            intent.putExtra("departmentID", department.getId());
            startActivity(intent);
        });

        // Gọi API để tải dữ liệu phòng ban
        loadData();

        return view;
    }

    private void loadData() {
        PhongBanRepository phongBanRepository = new PhongBanRepository(context);

        phongBanRepository.getAllPhongBan(new ApiCallback<List<Department>>() {
            @Override
            public void onSuccess(List<Department> data) {
                phongBanList.clear();
                phongBanList.addAll(data);
                adapter.updateList(data);
                Toast.makeText(context, "Nhận thành công danh sách Phòng ban", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(context, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
