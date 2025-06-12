package com.example.fe_quanlyvattu.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.BaoCaoAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fragment_home extends Fragment {

    private SwitchCompat switchMode;
    private boolean nightMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private RecyclerView recyclerDeXuat, recyclerBaoCao;
    private BaoCaoAdapter baoCaoAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        Khởi tạo Switch từ view
        switchMode = view.findViewById(R.id.switchMode);
        sharedPreferences = requireActivity().getSharedPreferences("night_mode_prefs", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("nightMode", false);

        // Gán trạng thái cho switch và áp dụng dark/light mode
        switchMode.setChecked(nightMode);
        AppCompatDelegate.setDefaultNightMode(
                nightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor = sharedPreferences.edit();
            editor.putBoolean("nightMode", isChecked);
            editor.apply();

            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );

            // ⚠️ Recreate lại Activity để chế độ dark/light được apply
            requireActivity().recreate();
        });


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