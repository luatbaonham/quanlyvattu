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
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phongban.Department;
import com.example.fe_quanlyvattu.data.model.vattu.QuanLyVatTuOption;
import com.example.fe_quanlyvattu.data.repository.PhongBanRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_phongban extends Fragment {

    private RecyclerView recyclerView;
    private PhongBanAdapter adapter;
    private List<Department> phongBanList = new ArrayList<>();
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phongban, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewquanlyphongban);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        phongBanList = new ArrayList<>();
        adapter = new PhongBanAdapter(getContext(), phongBanList);
        recyclerView.setAdapter(adapter);

        adapter = new PhongBanAdapter(context, phongBanList);
        adapter.setOnItemClickListener(department -> {
            Intent intent = new Intent(context, PhongActivity.class);
            intent.putExtra("departmentName", department.getName());
            intent.putExtra("departmentID", department.getId());
            startActivity(intent);
        });

        loadData();
        return view;
    }

    private void loadData() {
        // Không truyền apiService nữa, chỉ cần truyền context
        PhongBanRepository phongBanRepository = new PhongBanRepository(getContext());

        phongBanRepository.getAllPhongBan(new ApiCallback<List<Department>>() {
            @Override
            public void onSuccess(List<Department> data) {
                phongBanList.clear();
                phongBanList.addAll(data);
                adapter.updateList(data);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

