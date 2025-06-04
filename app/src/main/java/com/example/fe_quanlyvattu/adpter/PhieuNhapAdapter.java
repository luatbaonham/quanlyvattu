package com.example.fe_quanlyvattu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;

import java.util.List;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.ViewHolder> {
    private List<PhieuNhap> danhSach;

    public PhieuNhapAdapter(List<PhieuNhap> danhSach) {
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieunhap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuNhap phieu = danhSach.get(position);
        holder.tvMaPhieuNhap.setText(String.valueOf(phieu.getId()));
        holder.tvNguoiTao.setText(phieu.getRequestedUser().getUsername());
        holder.tvNgayDat.setText(phieu.getDateOfOrder());
        holder.tvNhaCungCap.setText(phieu.getSupplier().getName());

        // Có thể xử lý thêm nếu bạn có danh sách vật tư hoặc số lượng
        // holder.tvTenVatTu.setText(...);
        // holder.tvSoLuong.setText(...);
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Chọn phiếu: " + phieu.getId(), Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    public void updateList(List<PhieuNhap> newList) {
        this.danhSach.clear();
        this.danhSach.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhieuNhap, tvTenVatTu, tvNguoiTao, tvNgayDat, tvSoLuong, tvNhaCungCap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieuNhap = itemView.findViewById(R.id.tvMaPhieu);
            tvTenVatTu = itemView.findViewById(R.id.tvTenVt);
            tvNguoiTao = itemView.findViewById(R.id.tvNguoitao);
            tvNgayDat = itemView.findViewById(R.id.tvNgaydat);
            tvSoLuong = itemView.findViewById(R.id.tvSoluong);
            tvNhaCungCap = itemView.findViewById(R.id.tvNhaCC);
        }
    }
}
