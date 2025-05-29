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
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.ApiService;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;
import com.example.fe_quanlyvattu.data.repository.PhieuNhapRepository;

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

        recyclerView = view.findViewById(R.id.recyclerViewPhieuNhapTL);
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
        PhieuNhapRepository repository = new PhieuNhapRepository(requireContext());

        repository.getAllPhieuNhap(new ApiCallback<List<PhieuNhap>>() {
            @Override
            public void onSuccess(List<PhieuNhap> data) {
                phieuNhapList.clear();
                phieuNhapList.addAll(data);

                locPhieuNhap.clear();
                locPhieuNhap.addAll(phieuNhapList);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
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
                // Chuyển id (kiểu int) sang String rồi mới so sánh
                String idStr = String.valueOf(pn.getId());
                if (idStr.contains(keyword)) {
                    locPhieuNhap.add(pn);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

}
