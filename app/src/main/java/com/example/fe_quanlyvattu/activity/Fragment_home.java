package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.BaoCaoAdapter;
import com.example.fe_quanlyvattu.adpter.DeXuatAdapter;
import com.example.fe_quanlyvattu.model.DeXuat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_home extends Fragment {

    private RecyclerView recyclerDeXuat, recyclerBaoCao;
    private BaoCaoAdapter baoCaoAdapter;
    private DeXuatAdapter deXuatAdapter;
    private List<DeXuat> deXuatList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerDeXuat = view.findViewById(R.id.recycler_de_xuat);
        recyclerBaoCao = view.findViewById(R.id.recycler_bao_cao);

        recyclerDeXuat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBaoCao.setLayoutManager(new LinearLayoutManager(getContext()));

        deXuatList = new ArrayList<>();
        deXuatList.add(new DeXuat("ĐX-2025-001", "Nguyễn Văn A", "25/04/2025", "CNTT",1 ,"CHỜ DUYỆT"));
        deXuatList.add(new DeXuat("ĐX-2025-002", "Trần Thị B", "24/04/2025", "Kế Toán",2, "ĐÃ DUYỆT"));
        deXuatList.add(new DeXuat("ĐX-2025-003", "Lê Văn C", "23/04/2025", "Quản Trị",3, "TỪ CHỐI"));
        List<String> baoCaoList = Arrays.asList("Báo cáo A", "Báo cáo B", "Báo cáo C");

        deXuatAdapter = new DeXuatAdapter(deXuatList);
        baoCaoAdapter = new BaoCaoAdapter(baoCaoList);

        recyclerDeXuat.setAdapter(deXuatAdapter);
        recyclerBaoCao.setAdapter(baoCaoAdapter);

        return view;
    }
}