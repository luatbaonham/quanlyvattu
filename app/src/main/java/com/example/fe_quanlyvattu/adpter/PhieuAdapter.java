package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.Phieu;

import java.util.ArrayList;
import java.util.List;

public class PhieuAdapter extends RecyclerView.Adapter<PhieuAdapter.PhieuViewHolder> {

    private List<Phieu> danhSachPhieu;      // danh sách hiển thị
    private List<Phieu> originalList;       // danh sách gốc
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Phieu phieu);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PhieuAdapter(List<Phieu> danhSachPhieu) {
        this.danhSachPhieu = new ArrayList<>(danhSachPhieu);
        this.originalList = new ArrayList<>(danhSachPhieu);  // sao lưu danh sách ban đầu
    }

    @NonNull
    @Override
    public PhieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieu, parent, false);
        return new PhieuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuViewHolder holder, int position) {
        Phieu phieu = danhSachPhieu.get(position);
        holder.textViewTenPhieu.setText(phieu.getTenPhieu());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(phieu);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachPhieu.size();
    }

    public void filter(String keyword) {
        List<Phieu> filteredList = new ArrayList<>();
        for (Phieu phieu : originalList) {
            if (phieu.getTenPhieu().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(phieu);
            }
        }
        updateList(filteredList);
    }

    public void updateList(List<Phieu> newList) {
        danhSachPhieu.clear();
        danhSachPhieu.addAll(newList);
        notifyDataSetChanged();
    }

    static class PhieuViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenPhieu;

        public PhieuViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTenPhieu = itemView.findViewById(R.id.textViewTenPhieu);
        }
    }
}

