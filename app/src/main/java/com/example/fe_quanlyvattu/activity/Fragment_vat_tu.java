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
import com.example.fe_quanlyvattu.adpter.VatTuAdapter;
import com.example.fe_quanlyvattu.model.DeXuat;
import com.example.fe_quanlyvattu.model.VatTu;

import java.util.ArrayList;
import java.util.List;

import kotlin.jvm.internal.Ref;

public class Fragment_vat_tu extends Fragment {

    private RecyclerView recyclerView;
    private VatTuAdapter adapter;
    private List<VatTu> vatTuList;
    private List<VatTu> TKVatTu;
    private EditText edtTk;
    private Button btnloai, btnkho, btndvtinh,btntrangthai, btnTk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vat_tu, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewVatTu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        edtTk= view.findViewById(R.id.edtTimKiem);
        btnloai = view.findViewById(R.id.btn_loai);
        btnkho = view.findViewById(R.id.btn_kho);
        btndvtinh = view.findViewById(R.id.btn_don_vi);
        btntrangthai = view.findViewById(R.id.btn_trangthai);
        btnTk = view.findViewById(R.id.btn_tk);

        vatTuList = new ArrayList<>();
        vatTuList.add(new VatTu("Vat tu 1", "VT001", "máy khoan", "dụng cuj sửa chữa",14));
        vatTuList.add(new VatTu("Vat tu 2", "VT002", "máy may", "dụng cuj sửa chữa",2));
        vatTuList.add(new VatTu("Vat tu 3", "VT003", "máy tiện", "dụng cuj sửa chữa",3));

        TKVatTu = new ArrayList<>(vatTuList);
        adapter = new VatTuAdapter(TKVatTu);
        recyclerView.setAdapter(adapter);

        btnTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtTk.getText().toString().trim().toLowerCase();

                TKVatTu.clear(); // Xoá danh sách lọc trước khi thêm

                if (keyword.isEmpty()) {
                    TKVatTu.addAll(vatTuList); // Nếu không nhập gì thì hiện tất cả
                } else {
                    for (VatTu vatTu : vatTuList) {
                        String ma = vatTu.getMaVt().toLowerCase();

                        if (ma.contains(keyword)) {
                            TKVatTu.add(vatTu); // Thêm nếu mã chứa từ khóa
                        }
                    }
                }

                adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
            }
        });

        return view;
        }

}
