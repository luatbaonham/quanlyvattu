package com.example.fe_quanlyvattu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.PhieuThanhLyAdapter;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.model.PhieuThanhLy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_duyet_capphat extends Fragment {
    private RecyclerView recyclerView;
    private PhieuThanhLyAdapter adapter;
    private List<PhieuThanhLy> phieuThanhLyList = new ArrayList<>();
    private List<PhieuThanhLy> locPhieuThanhLy = new ArrayList<>();
    private EditText edtTimKiem;
    private Button btnLoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_duyet_capphat, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCapPhat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        edtTimKiem = view.findViewById(R.id.edtTimKiem);
        btnLoc = view.findViewById(R.id.btnLoc);

        adapter = new PhieuThanhLyAdapter(locPhieuThanhLy);
        recyclerView.setAdapter(adapter);

        fetchPhieuThanhLyList();

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocPhieuThanhLy();
            }
        });

        return view;
    }

    private void fetchPhieuThanhLyList() {
        ApiService apiService = RetrofitClient
                .getInstance(requireContext())
                .create(ApiService.class);

        apiService.getPhieuThanhLyList().enqueue(new Callback<List<PhieuThanhLy>>() {
            @Override
            public void onResponse(Call<List<PhieuThanhLy>> call, Response<List<PhieuThanhLy>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    phieuThanhLyList.clear();
                    phieuThanhLyList.addAll(response.body());

                    // Gán toàn bộ vào list lọc để hiển thị ban đầu
                    locPhieuThanhLy.clear();
                    locPhieuThanhLy.addAll(phieuThanhLyList);

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Không có dữ liệu phiếu nhập", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PhieuThanhLy>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi khi tải phiếu nhập: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setLocPhieuThanhLy() {
        String keyword = edtTimKiem.getText().toString().trim().toLowerCase();
        locPhieuThanhLy.clear();

        if (keyword.isEmpty()) {
            locPhieuThanhLy.addAll(phieuThanhLyList); // Hiển thị lại tất cả nếu không tìm
        } else {
            for (PhieuThanhLy ptl : phieuThanhLyList) {
                if (ptl.getMaPhieu() != null && ptl.getMaPhieu().toLowerCase().contains(keyword)) {
                    locPhieuThanhLy.add(ptl);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

}