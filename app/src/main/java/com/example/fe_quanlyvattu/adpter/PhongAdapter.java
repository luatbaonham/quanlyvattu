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
import com.example.fe_quanlyvattu.data.model.phongban.Department;

import java.util.ArrayList;
import java.util.List;

public class PhongAdapter extends RecyclerView.Adapter<PhongAdapter.PhongViewHolder> {

    private Context context;
    private List<Room> phongList;

    public PhongAdapter(Context context, List<Room> phongList) {
        this.context = context;
        this.phongList = phongList != null ? phongList : new ArrayList<>();
    }

    @NonNull
    @Override
    public PhongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phong, parent, false);
        return new PhongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongViewHolder holder, int position) {
        Room phong = phongList.get(position);
        holder.tvIdPhong.setText("ID: " + phong.getId());
        holder.tvTenPhong.setText("Tên phòng: " + phong.getName());
    }

    @Override
    public int getItemCount() {
        return phongList != null ? phongList.size() : 0;
    }

    public void setPhongList(List<Room> newList) {
        this.phongList = newList != null ? newList : new ArrayList<>();
        notifyDataSetChanged();
    }

    static class PhongViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdPhong, tvTenPhong, tvMoTa;

        public PhongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdPhong = itemView.findViewById(R.id.tvIdPhong);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong);
        }
    }

    public void updateList(List<Room> newList) {
        phongList.clear();
        phongList.addAll(newList);
        notifyDataSetChanged();
    }
}
