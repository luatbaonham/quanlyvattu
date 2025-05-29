package com.example.fe_quanlyvattu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieuthanhly.PhieuThanhLy;

import java.util.List;

public class PhieuThanhLyAdapter extends RecyclerView.Adapter<PhieuThanhLyAdapter.ViewHolder> {
    private List<PhieuThanhLy> danhSach;
    private Context context;

    public PhieuThanhLyAdapter(List<PhieuThanhLy> locPhieuThanhLy) {
        this.danhSach = locPhieuThanhLy;
    }


    @NonNull
    @Override
    public PhieuThanhLyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu_thanh_ly, parent, false);
        return new PhieuThanhLyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuThanhLyAdapter.ViewHolder holder, int position) {
        PhieuThanhLy phieu = danhSach.get(position);
        holder.tvMaPhieuThanhLy.setText(phieu.getMaPhieu());
        holder.tvNgayThanhLy.setText(phieu.getNgayThanhLy());
        holder.tvLyDo.setText(phieu.getLyDo());
        holder.tvTrangThai.setText(phieu.getTrangThai());
        holder.tvGiaThanhLy.setText(String.valueOf(phieu.getGiaThanhLy()));
        holder.tvGhiChu.setText(phieu.getGhiChu());
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhieuThanhLy, tvNgayThanhLy, tvLyDo, tvTrangThai, tvGiaThanhLy, tvGhiChu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieuThanhLy = itemView.findViewById(R.id.tvMaPhieuTL);
            tvNgayThanhLy = itemView.findViewById(R.id.tvNgayThanhLy);
            tvLyDo = itemView.findViewById(R.id.tvLyDo);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvGiaThanhLy = itemView.findViewById(R.id.tvGiaThanhLy);
            tvGhiChu = itemView.findViewById(R.id.tvGhiChu);
        }

    }
}
