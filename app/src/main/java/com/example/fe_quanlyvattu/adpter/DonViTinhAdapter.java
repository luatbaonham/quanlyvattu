package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.vattu.donvitinh.DonViTinh;

import java.util.ArrayList;
import java.util.List;

public class DonViTinhAdapter extends RecyclerView.Adapter<DonViTinhAdapter.DonViTinhViewHolder> {

    private List<DonViTinh> donViTinhList;

    public DonViTinhAdapter(List<DonViTinh> donViTinhList) {
        this.donViTinhList = donViTinhList;
    }



    @NonNull
    @Override
    public DonViTinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_donvitinh, parent, false);
        return new DonViTinhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonViTinhViewHolder holder, int position) {
        DonViTinh donVi = donViTinhList.get(position);
        holder.tvMaDv.setText(String.valueOf(donVi.getId()));
        holder.tvTenDv.setText(donVi.getName());
        holder.tvMoTa.setText(donVi.getDescription());
    }

    @Override
    public int getItemCount() {
        return donViTinhList != null ? donViTinhList.size() : 0;
    }

    public static class DonViTinhViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaDv, tvTenDv, tvMoTa;

        public DonViTinhViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaDv = itemView.findViewById(R.id.tvIdDonVi);
            tvTenDv = itemView.findViewById(R.id.tvTenDonVi);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
        }
    }
    public void setDonViTinhList(List<DonViTinh> donViTinhList) {
        this.donViTinhList = donViTinhList != null ? donViTinhList : new ArrayList<>();
        notifyDataSetChanged();
    }
}