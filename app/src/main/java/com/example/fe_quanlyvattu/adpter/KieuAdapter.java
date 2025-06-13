package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.vattu.kieu.Kieu;

import java.util.List;

public class KieuAdapter extends RecyclerView.Adapter<KieuAdapter.KieuViewHolder> {

    private List<Kieu> kieuList;

    public KieuAdapter(List<Kieu> kieuList) {
        this.kieuList = kieuList;
    }

    public void setKieuList(List<Kieu> kieuList) {
        this.kieuList = kieuList;
    }

    @NonNull
    @Override
    public KieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kieuvt, parent, false);
        return new KieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KieuViewHolder holder, int position) {
        Kieu kieu = kieuList.get(position);
        holder.tvIdKieu.setText(String.valueOf(kieu.getId()));
        holder.tvTenKieu.setText(kieu.getName());
        holder.tvMoTa.setText(kieu.getDescription());
    }

    @Override
    public int getItemCount() {
        return kieuList != null ? kieuList.size() : 0;
    }

    public static class KieuViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdKieu, tvTenKieu, tvMoTa;

        public KieuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdKieu = itemView.findViewById(R.id.tvIdKieu);
            tvTenKieu = itemView.findViewById(R.id.tvTenKieu);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
        }
    }
}
