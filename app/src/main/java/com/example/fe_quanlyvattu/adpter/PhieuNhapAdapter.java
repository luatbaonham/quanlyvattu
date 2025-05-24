package com.example.fe_quanlyvattu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.PhieuNhap;

import java.util.List;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.ViewHolder> {
    private List<PhieuNhap> danhSach;
    private Context context;

    public PhieuNhapAdapter(List<PhieuNhap> locPhieuNhap) {
        this.danhSach = locPhieuNhap;
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
        holder.tvMaPhieuNhap.setText(phieu.getMaPhieuNhap());
        holder.tvTenVatTu.setText(phieu.getTenVatTu());
        holder.tvNguoiTao.setText(phieu.getNguoiTao());
        holder.tvNgayDat.setText(phieu.getNgayDat());
        holder.tvSoLuong.setText(String.valueOf(phieu.getSoLuong()));
        holder.tvNhaCungCap.setText(phieu.getNhaCungCap());
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
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

