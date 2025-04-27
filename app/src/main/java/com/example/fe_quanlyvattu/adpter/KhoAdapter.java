package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.Kho;

import java.util.List;

public class KhoAdapter extends RecyclerView.Adapter<KhoAdapter.ViewHolder> {
    private List<Kho> ticketList;

    public KhoAdapter(List<Kho> ticketList) {
        this.ticketList = ticketList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kho, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Kho ticket = ticketList.get(position);
        holder.tv_maphieu.setText("📋 " + ticket.getMaphieu());
        holder.tv_tenkho.setText("🏷️ " + ticket.getTenkho());
        holder.tv_nguoitao.setText("👤 Người tạo: " + ticket.getNguoitao());
        holder.tv_ngaytao.setText("📆 Ngày: " + ticket.getNgaytao());
        holder.tv_sovt.setText("📦 " + ticket.getSo_vt());

    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_maphieu, tv_tenkho, tv_nguoitao, tv_ngaytao, tv_sovt;
        Button btntk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maphieu = itemView.findViewById(R.id.tv_maphieu);
            tv_tenkho = itemView.findViewById(R.id.tv_tenkho);
            tv_nguoitao = itemView.findViewById(R.id.tv_nguoitao);
            tv_ngaytao = itemView.findViewById(R.id.tv_ngaytao);
            tv_sovt = itemView.findViewById(R.id.tv_so_vt);
        }
    }
}