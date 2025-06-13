package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.vattu.donvitinh.DonViTinh;
import com.example.fe_quanlyvattu.data.model.vattu.hang.HangVt;

import java.util.ArrayList;
import java.util.List;

public class HangVtAdapter extends RecyclerView.Adapter<HangVtAdapter.HangViewHolder> {

    private List<HangVt> hangList;

    public HangVtAdapter(List<HangVt> hangList) {
        this.hangList = hangList;
    }

    @NonNull
    @Override
    public HangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hangvt, parent, false);
        return new HangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HangViewHolder holder, int position) {
        HangVt hang = hangList.get(position);
        holder.tvMaHang.setText(String.valueOf(hang.getId()));
        holder.tvTenHang.setText(hang.getName());
        holder.tvLienLac.setText(hang.getContactInfo());
        holder.tvDiaChi.setText(hang.getAddress());
    }

    @Override
    public int getItemCount() {
        return hangList != null ? hangList.size() : 0;
    }

    public void setHangVtList(List<HangVt> hangVtList) {
        this.hangList = hangVtList != null ? hangVtList : new ArrayList<>();
        notifyDataSetChanged();
    }
    public static class HangViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaHang, tvTenHang, tvLienLac, tvDiaChi;

        public HangViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaHang = itemView.findViewById(R.id.tvIdHang);
            tvTenHang = itemView.findViewById(R.id.tvTenHang);
            tvLienLac = itemView.findViewById(R.id.tvLienLac);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
        }
    }
}
