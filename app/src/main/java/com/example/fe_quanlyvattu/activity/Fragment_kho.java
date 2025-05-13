package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.KhoAdapter;
import com.example.fe_quanlyvattu.adpter.VatTuAdapter;
import com.example.fe_quanlyvattu.model.Kho;
import com.example.fe_quanlyvattu.model.VatTu;

import java.util.ArrayList;
import java.util.List;

public class Fragment_kho extends Fragment {
    private RecyclerView recyclerView;
    private KhoAdapter adapter;
    private List<Kho> ticketList;
    private List<Kho> searchkho;
    private EditText edtTk;
    private Button btntk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nhap_xuat_kho, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewKho);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btntk = view.findViewById(R.id.btntk);
        edtTk = view.findViewById(R.id.edtTimKiem);

        // Dữ liệu mẫu
        ticketList = new ArrayList<>();
        ticketList.add(new Kho("PN-001", "Kho A", "Nguyễn Văn A", "14/4/2025", 5));
        ticketList.add(new Kho("PX-002", "Kho A", "Nguyễn Văn A", "14/4/2025", 90));

        searchkho = new ArrayList<>(ticketList);
        adapter = new KhoAdapter(ticketList);
        recyclerView.setAdapter(adapter);

        btntk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTk.getText().toString().trim().toLowerCase();

                searchkho.clear(); // Xoá danh sách lọc trước khi thêm

                if (keyword.isEmpty()) {
                    searchkho.addAll(ticketList); // Nếu không nhập gì thì hiện tất cả
                } else {
                    for (Kho kho : ticketList) {
                        String ma = kho.getMaphieu().toLowerCase();

                        if (ma.contains(keyword)) {
                            searchkho.add(kho); // Thêm nếu mã chứa từ khóa
                        }
                    }
                }

                adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
            }
        });

        return view;
    }
}
