package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.model.QuanLyVatTuOption;

import java.util.ArrayList;
import java.util.List;

public class QuanLyVatTuOptionAdapter extends RecyclerView.Adapter<QuanLyVatTuOptionAdapter.QuanLyVatTuOptionViewHolder> {
    private List<QuanLyVatTuOption> danhSachOption;
    private List<QuanLyVatTuOption> originalList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(QuanLyVatTuOption option);
    }

    public void setOnClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public QuanLyVatTuOptionAdapter(List<QuanLyVatTuOption> danhSachOption) {
        this.danhSachOption = new ArrayList<>(danhSachOption);
        this.originalList = new ArrayList<>(danhSachOption);  // sao lưu danh sách ban đầu
    }

    @NonNull
    @Override
    public QuanLyVatTuOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quan_ly_vat_tu_option, parent, false);
        return new QuanLyVatTuOptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyVatTuOptionViewHolder holder, int position) {
        QuanLyVatTuOption option = danhSachOption.get(position);
        holder.textViewTenOption.setText(option.getName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(option);
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachOption.size();
    }

    public void filter(String keyword) {
        List<QuanLyVatTuOption> filteredList = new ArrayList<>();
        for (QuanLyVatTuOption option : originalList) {
            if (option.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(option);
            }
        }
        updateList(filteredList);
    }

    public void updateList(List<QuanLyVatTuOption> newList) {
        danhSachOption.clear();
        danhSachOption.addAll(newList);
        notifyDataSetChanged();
    }

    static class QuanLyVatTuOptionViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTenOption;

        public QuanLyVatTuOptionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTenOption = itemView.findViewById(R.id.textViewTenOption);
        }
    }
}
