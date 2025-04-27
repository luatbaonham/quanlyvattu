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
import com.example.fe_quanlyvattu.adpter.KhoAdapter;
import com.example.fe_quanlyvattu.adpter.VatTuAdapter;
import com.example.fe_quanlyvattu.model.Kho;

import java.util.ArrayList;
import java.util.List;

public class Fragment_kho extends Fragment {
    private RecyclerView recyclerView;
    private KhoAdapter adapter;
    private List<Kho> ticketList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vat_tu, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewVatTu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dữ liệu mẫu
        ticketList = new ArrayList<>();
        ticketList.add(new Kho("PN-2025-0023", "Kho A", "Nguyễn Văn A", "14/4/2025", 5));
        ticketList.add(new Kho("PX-2025-0022", "Kho A", "Nguyễn Văn A", "14/4/2025", 90));

        adapter = new KhoAdapter(ticketList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
