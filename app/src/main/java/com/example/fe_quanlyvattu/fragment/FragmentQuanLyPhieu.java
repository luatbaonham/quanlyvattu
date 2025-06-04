package com.example.fe_quanlyvattu.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.ActivityPhieuChuyen;
import com.example.fe_quanlyvattu.activity.ActivityPhieuMuon;
import com.example.fe_quanlyvattu.activity.ActivityPhieuNhap;
import com.example.fe_quanlyvattu.activity.ActivityPhieuThanhLy;
import com.example.fe_quanlyvattu.adpter.PhieuAdapter;
import com.example.fe_quanlyvattu.model.Phieu;

import java.util.ArrayList;
import java.util.List;

public class FragmentQuanLyPhieu extends Fragment {

    private RecyclerView recyclerView;
    private PhieuAdapter adapter;
    private List<Phieu> danhSachPhieu = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quanly_phieu, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewPhieu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Tạo danh sách mẫu
        danhSachPhieu.add(new Phieu("Phiếu Nhập"));
        danhSachPhieu.add(new Phieu("Phiếu Mượn"));
        danhSachPhieu.add(new Phieu("Phiếu Thanh Lý"));
        danhSachPhieu.add(new Phieu("Phiếu Chuyển"));

        adapter = new PhieuAdapter(danhSachPhieu);
        adapter.setOnItemClickListener(phieu -> {
            Intent intent = null;
            Context context = getContext();

            switch (phieu.getTenPhieu()) {
                case "Phiếu Nhập":
                    intent = new Intent(context, ActivityPhieuNhap.class);
                    break;
                case "Phiếu Mượn":
                    intent = new Intent(context, ActivityPhieuMuon.class);
                    break;
                case "Phiếu Thanh Lý":
                    intent = new Intent(context, ActivityPhieuThanhLy.class);
                    break;
                case "Phiếu Chuyển":
                    intent = new Intent(context, ActivityPhieuChuyen.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}
