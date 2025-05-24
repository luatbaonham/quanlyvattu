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
import com.example.fe_quanlyvattu.adpter.PhieuNhapAdapter;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_phieunhap_thanhly extends Fragment {

    private RecyclerView recyclerView;
    private PhieuNhapAdapter adapter;
    private List<PhieuNhap> phieuNhapList = new ArrayList<>();
    private List<PhieuNhap> locPhieuNhap = new ArrayList<>();
    private EditText edtTimKiem;
    private Button btnLoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieunhap_thanhly, container, false);

        recyclerView.findViewById(R.id.recyclerViewPhieuNhapTL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        edtTimKiem = view.findViewById(R.id.edtTimKiem);
        btnLoc = view.findViewById(R.id.btntk);

        adapter = new PhieuNhapAdapter(locPhieuNhap);
        recyclerView.setAdapter(adapter);

        fetchPhieuNhapList();

        btnLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locPhieuNhap();
            }
        });

        return view;
    }

    private void fetchPhieuNhapList() {
        ApiService apiService = RetrofitClient
                .getInstance(requireContext())
                .create(ApiService.class);

        apiService.getPhieuNhapList().enqueue(new Callback<List<PhieuNhap>>() {
            @Override
            public void onResponse(Call<List<PhieuNhap>> call, Response<List<PhieuNhap>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    phieuNhapList.clear();
                    phieuNhapList.addAll(response.body());

                    // Gán toàn bộ vào list lọc để hiển thị ban đầu
                    locPhieuNhap.clear();
                    locPhieuNhap.addAll(phieuNhapList);

                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Không có dữ liệu phiếu nhập", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PhieuNhap>> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi khi tải phiếu nhập: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void locPhieuNhap() {
        String keyword = edtTimKiem.getText().toString().trim().toLowerCase();
        locPhieuNhap.clear();

        if (keyword.isEmpty()) {
            locPhieuNhap.addAll(phieuNhapList); // Hiển thị lại tất cả nếu không tìm
        } else {
            for (PhieuNhap pn : phieuNhapList) {
                if (pn.getMaPhieuNhap() != null && pn.getMaPhieuNhap().toLowerCase().contains(keyword)) {
                    locPhieuNhap.add(pn);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }
}
