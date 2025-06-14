package com.example.fe_quanlyvattu.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phong.Room;

import java.util.ArrayList;
import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.ViewHolder> {

    private final Context context;
    private List<Room> phongList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Room room);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PhongAdapter(Context context, List<Room> phongList) {
        this.context = context;
        this.phongList = phongList != null ? phongList : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phong, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = phongList.get(position);
        holder.tvIdPhong.setText("ID: " + room.getId());
        holder.tvTenPhong.setText("Tên phòng: " + room.getName());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(room);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phongList.size();
    }

    public void updateList(List<Room> newList) {
        phongList.clear();
        if (newList != null) {
            phongList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPhong, tvTenPhong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPhong = itemView.findViewById(R.id.tvIdPhong);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong);
        }
    }
}
