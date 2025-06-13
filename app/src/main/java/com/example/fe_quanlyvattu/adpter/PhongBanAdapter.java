package com.example.fe_quanlyvattu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.data.model.phongban.Department;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.Phieu;

import java.util.List;

public class PhongBanAdapter extends RecyclerView.Adapter<PhongBanAdapter.ViewHolder> {

    private Context context;
    private List<Department> list;
    private PhongBanAdapter.OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(Department department);
    }
    public void setOnItemClickListener(PhongBanAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public PhongBanAdapter(Context context, List<Department> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PhongBanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phongban, parent, false);
        return new PhongBanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongBanAdapter.ViewHolder holder, int position) {
        Department pb = list.get(position);
        holder.tvName.setText(pb.getName());
        holder.tvNotes.setText(pb.getNotes());
        holder.tvStatus.setText(pb.getStatus());
        holder.tvCreated.setText(pb.getCreatedAt());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(pb);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvNotes, tvStatus, tvCreated, tvUpdated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTenPhongBan);
            tvNotes = itemView.findViewById(R.id.tvGhiChu);
            tvStatus = itemView.findViewById(R.id.tvTrangThai);
            tvCreated = itemView.findViewById(R.id.tvNgayTao);
        }
    }

    public void updateList(List<Department> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
}

