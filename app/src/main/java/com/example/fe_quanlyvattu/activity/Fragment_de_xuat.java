package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.DeXuatAdapter;
import com.example.fe_quanlyvattu.model.DeXuat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_de_xuat extends Fragment {

    private RecyclerView recyclerView;
    private DeXuatAdapter adapter;
    private List<DeXuat> deXuatList;
    private List<DeXuat> locDeXuat;
    private EditText edtTimKiem;
    private Button btnLoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_de_xuat, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewDeXuat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        edtTimKiem= view.findViewById(R.id.edtTimKiem);
        btnLoc = view.findViewById(R.id.btnLoc);
        // Khởi tạo danh sách gốc
        deXuatList = new ArrayList<>();
        deXuatList.add(new DeXuat("ĐX-2025-001", "Nguyễn Văn A", "25/04/2025", "CNTT",1 ,"CHỜ DUYỆT"));
        deXuatList.add(new DeXuat("ĐX-2025-002", "Trần Thị B", "24/04/2025", "Kế Toán",2, "ĐÃ DUYỆT"));
        deXuatList.add(new DeXuat("ĐX-2025-003", "Lê Văn C", "23/04/2025", "Quản Trị",3, "TỪ CHỐI"));
        // Khởi tạo danh sách lọc với bản sao ban đầu
        locDeXuat = new ArrayList<>(deXuatList);
        // Adapter dùng danh sách lọc
        adapter = new DeXuatAdapter(locDeXuat);
        recyclerView.setAdapter(adapter);
        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTimKiem.getText().toString().trim().toLowerCase();

                locDeXuat.clear(); // Xoá danh sách lọc trước khi thêm

                if (keyword.isEmpty()) {
                    locDeXuat.addAll(deXuatList); // Nếu không nhập gì thì hiện tất cả
                } else {
                    for (DeXuat dx : deXuatList) {
                        String ma = dx.getMaDeXuat().toLowerCase();
                        String trangThai = dx.getTrangThai().toLowerCase();

                        if (ma.contains(keyword) || trangThai.contains(keyword)) {
                            locDeXuat.add(dx); // Thêm nếu mã hoặc trạng thái chứa từ khóa
                        }
                    }
                }

                adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
            }
        });

        return view;
    }
}