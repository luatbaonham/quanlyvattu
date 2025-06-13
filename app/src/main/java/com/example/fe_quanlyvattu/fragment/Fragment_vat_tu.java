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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.LoaiVtActivity;
import com.example.fe_quanlyvattu.adpter.QuanLyVatTuOptionAdapter;
//import com.example.fe_quanlyvattu.adpter.VatTuAdapter;
import com.example.fe_quanlyvattu.model.QuanLyVatTuOption;

import java.util.ArrayList;
import java.util.List;

public class Fragment_vat_tu extends Fragment {

    private RecyclerView recyclerView;
    private QuanLyVatTuOptionAdapter adapter;
    //private List<VatTu> vatTuList;
    //private List<VatTu> TKVatTu;
    //private EditText edtTk;
    //private Button btnloai, btnkho, btndvtinh,btntrangthai, btnTk;
    private List<QuanLyVatTuOption> danhSachOption = new ArrayList<>();
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_vat_tu, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewQuanLyVatTuOption);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        danhSachOption.add(new QuanLyVatTuOption("Loại"));
        danhSachOption.add(new QuanLyVatTuOption("Kiểu"));
        danhSachOption.add(new QuanLyVatTuOption("Đơn vị tính"));
        danhSachOption.add(new QuanLyVatTuOption("Hãng"));

        adapter = new QuanLyVatTuOptionAdapter(danhSachOption);
        adapter.setOnClickListener(option -> {
            Intent intent = null;
            context = getContext();
            switch (option.getName()) {
                case "Loại":
                    intent = new Intent(context, LoaiVtActivity.class);
                    break;
                case "Đơn vị tính":
                    intent = new Intent(context, DonViTinhActivity.class);
                    break;
                case "Hãng":
                    intent = new Intent(Context, HangActivity.class);
                    break;
                case "Kiểu":
                    intent = new Intent(Context, KieuActivity.class);
                    break;
            }

            if (intent!=null) {
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        //edtTk= view.findViewById(R.id.edtTimKiem);
        //btnloai = view.findViewById(R.id.btn_loai);
        //btnkho = view.findViewById(R.id.btn_kho);
        //btndvtinh = view.findViewById(R.id.btn_don_vi);
        //btntrangthai = view.findViewById(R.id.btn_trangthai);
        //btnTk = view.findViewById(R.id.btn_tk);

        //vatTuList = new ArrayList<>();
        //vatTuList.add(new VatTu("Vat tu 1", "VT001", "máy khoan", "dụng cuj sửa chữa",14));
        //vatTuList.add(new VatTu("Vat tu 2", "VT002", "máy may", "dụng cuj sửa chữa",2));
        //vatTuList.add(new VatTu("Vat tu 3", "VT003", "máy tiện", "dụng cuj sửa chữa",3));

        //TKVatTu = new ArrayList<>(vatTuList);
        //adapter = new VatTuAdapter(TKVatTu);
        //recyclerView.setAdapter(adapter);

        //btnTk.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        String keyword = edtTk.getText().toString().trim().toLowerCase();

        //        TKVatTu.clear(); // Xoá danh sách lọc trước khi thêm

        //        if (keyword.isEmpty()) {
        //            TKVatTu.addAll(vatTuList); // Nếu không nhập gì thì hiện tất cả
        //        } else {
        //            for (VatTu vatTu : vatTuList) {
        //                String ma = vatTu.getMaVt().toLowerCase();

        //                if (ma.contains(keyword)) {
        //                    TKVatTu.add(vatTu); // Thêm nếu mã chứa từ khóa
        //                }
        //            }
        //        }

        //        adapter.notifyDataSetChanged(); // Cập nhật lại RecyclerView
        //    }
        //});
        //btnloai.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Intent intent = new Intent(getActivity(), LoaiVtActivity.class);
        //        startActivity(intent);
        //    }
        //});

        return view;
        }

}
