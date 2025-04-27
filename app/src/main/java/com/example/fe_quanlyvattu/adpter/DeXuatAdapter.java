package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.DeXuat;

import java.util.List;

public class DeXuatAdapter extends RecyclerView.Adapter<DeXuatAdapter.ViewHolder> {
    private List<DeXuat> dexuatList;

    public DeXuatAdapter(List<DeXuat> dexuatList) {
        this.dexuatList = dexuatList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView madexuat, nguoigui, ngaygui, khoa, soluongvattu, trangthai;
        ImageView imgTrangThai;
        Button btnDuyet;
        public ViewHolder(View itemView) {
            super(itemView);
            madexuat= itemView.findViewById(R.id.tvMaDeXuat);
            nguoigui = itemView.findViewById(R.id.tvNguoiGui);
            ngaygui = itemView.findViewById(R.id.tvNgayGui);
            khoa = itemView.findViewById(R.id.tvKhoa);
            soluongvattu = itemView.findViewById(R.id.tvVatTu);
            trangthai = itemView.findViewById(R.id.tvTrangThai);
            imgTrangThai = itemView.findViewById(R.id.imgTrangThai);
            btnDuyet = itemView.findViewById(R.id.btnDuyet);
        }
    }

    @Override
    public DeXuatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_de_xuat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeXuatAdapter.ViewHolder holder, int position) {
        DeXuat deXuat = dexuatList.get(position);
        holder.madexuat.setText(deXuat.getMaDeXuat());
        holder.nguoigui.setText("Người gửi: " + deXuat.getNguoiGui());
        holder.ngaygui.setText("Ngày: " + deXuat.getNgayGui());
        holder.khoa.setText("Khoa: " + deXuat.getKhoa());
        holder.soluongvattu.setText(deXuat.getSoLuongVatTu() + " vật tư");
        holder.trangthai.setText(deXuat.getTrangThai());
        if (deXuat.getTrangThai().equals("ĐÃ DUYỆT")) {
            holder.trangthai.setTextColor(0xFF00C853); // xanh lá
            holder.imgTrangThai.setImageResource(R.drawable.ic_done); // icon tick xanh
        } else if (deXuat.getTrangThai().equals("CHỜ DUYỆT")) {
            holder.trangthai.setTextColor(0xFFFF8800); // cam
            holder.imgTrangThai.setImageResource(R.drawable.wait); // icon đồng hồ
        } else if (deXuat.getTrangThai().equals("TỪ CHỐI")) {
            holder.trangthai.setTextColor(0xFFD50000); // đỏ
            holder.imgTrangThai.setImageResource(R.drawable.reject); // icon bị từ chối
        }

        switch (deXuat.getTrangThai()) {
            case "ĐÃ DUYỆT":
                holder.trangthai.setTextColor(0xFF00C853);
                holder.imgTrangThai.setImageResource(R.drawable.ic_done);
                holder.btnDuyet.setText("ĐÃ DUYỆT");
                holder.btnDuyet.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00FF00")));
                holder.btnDuyet.setEnabled(false);
                break;
            case "CHỜ DUYỆT":
            case "TỪ CHỐI":
                holder.trangthai.setTextColor(
                        deXuat.getTrangThai().equals("CHỜ DUYỆT") ? 0xFFFF8800 : 0xFFD50000
                );
                holder.imgTrangThai.setImageResource(
                        deXuat.getTrangThai().equals("CHỜ DUYỆT") ? R.drawable.wait : R.drawable.reject
                );
                holder.btnDuyet.setText("DUYỆT");
                holder.btnDuyet.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F44336")));
                holder.btnDuyet.setEnabled(true);

                // Gắn sự kiện duyệt
                holder.btnDuyet.setOnClickListener(v -> {
                    deXuat.setTrangThai("ĐÃ DUYỆT");
                    notifyItemChanged(holder.getAdapterPosition());
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dexuatList.size();
    }
}

