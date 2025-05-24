package com.example.fe_quanlyvattu.fragment;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_home extends Fragment {

    private RecyclerView recyclerDeXuat, recyclerBaoCao;
    private BaoCaoAdapter baoCaoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerDeXuat = view.findViewById(R.id.recycler_de_xuat);
        recyclerBaoCao = view.findViewById(R.id.recycler_bao_cao);

        recyclerDeXuat.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerBaoCao.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> baoCaoList = Arrays.asList("Báo cáo A", "Báo cáo B", "Báo cáo C");

        baoCaoAdapter = new BaoCaoAdapter(baoCaoList);

        recyclerBaoCao.setAdapter(baoCaoAdapter);

        return view;
    }
}