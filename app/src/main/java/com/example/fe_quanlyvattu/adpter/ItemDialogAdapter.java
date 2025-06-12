package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieunhap.Item;

import java.util.List;

public class ItemDialogAdapter extends RecyclerView.Adapter<ItemDialogAdapter.ItemViewHolder> {

    private List<Item> itemList;

    public ItemDialogAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chitiet_vattu_phieunhap, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.tvItemCode.setText(item.getCode());
        holder.tvItemName.setText(item.getName());
        holder.tvItemQuantity.setText(String.valueOf(item.getQuantity()));
        holder.tvItemPrice.setText(String.valueOf(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemCode, tvItemName, tvItemQuantity, tvItemPrice;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemCode = itemView.findViewById(R.id.tvItemCode);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemQuantity = itemView.findViewById(R.id.tvItemQuantity);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
        }
    }
}
