package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.NhomVt;

import java.util.ArrayList;
import java.util.List;

public class LoaiVtAdapter extends RecyclerView.Adapter<LoaiVtAdapter.LoaiVtViewHolder> {
    private List<NhomVt> loaiVtList;
    private Context context;

    public LoaiVtAdapter(List<NhomVt> loaiVtList, Context context) {
        this.loaiVtList = loaiVtList != null ? loaiVtList : new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public LoaiVtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_vat_tu, parent, false);
        return new LoaiVtViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LoaiVtViewHolder holder, int position) {
        NhomVt loaiVt = loaiVtList.get(position);
        holder.ten.setText(loaiVt.getName());
        holder.mota.setText(loaiVt.getDescription());
        holder.soluong.setText(
                loaiVt.getEquipmentStatusCounts().getAvailable() +
                        loaiVt.getEquipmentStatusCounts().getLiquidation() + " " +loaiVt.getUnitOfMeasure().getName()
        );
        holder.hang.setText(
                loaiVt.getManufacturer().getName()
        );
        holder.lancapnhat.setText(loaiVt.getUpdatedAt());
        //holder.lancapnhat.setText();


//        holder.btnSua.setOnClickListener(v -> {
//            Intent intent = new Intent(context, SualoaiVtActivity.class);
//            intent.putExtra("tenloai", loaiVt.getName());
//            intent.putExtra("mota", loaiVt.getDescription());
//            intent.putExtra("position", position);
//
//            if (context instanceof Activity) {
//                ((Activity) context).startActivityForResult(intent, 1001);
//            }
//        });

        holder.btnXoa.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Xác nhận xóa")
                    .setMessage("Bạn có chắc chắn xóa loại vật tư này?\nNhững vật tư thuộc loại này sẽ chuyển về loại mặc định.")
                    .setNegativeButton("Hủy", null)
                    .setPositiveButton("Xác nhận", (dialog, which) -> {
                        loaiVtList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, loaiVtList.size());
                    })
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return loaiVtList.size();
    }

    public class LoaiVtViewHolder extends RecyclerView.ViewHolder {
        TextView ten, mota, soluong, lancapnhat, hang;
        Button btnSua, btnXoa;

        public LoaiVtViewHolder(@NonNull View itemView) {
            super(itemView);
            hang = itemView.findViewById(R.id.hang);
            ten = itemView.findViewById(R.id.tenloai);
            mota = itemView.findViewById(R.id.mota);
            soluong = itemView.findViewById(R.id.soluong);
            lancapnhat = itemView.findViewById(R.id.ngaycapnhat);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }

    public void updateItem(int position, NhomVt newLoaiVt) {
        loaiVtList.set(position, newLoaiVt);
        notifyItemChanged(position);
    }

    public void setLoaiVtList(List<NhomVt> loaiVtList) {
        this.loaiVtList = loaiVtList != null ? loaiVtList : new ArrayList<>();
        notifyDataSetChanged(); // ✅ Cập nhật lại toàn bộ danh sách
    }
}
