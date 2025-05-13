package com.example.fe_quanlyvattu.adpter;

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
import com.example.fe_quanlyvattu.activity.SualoaiVtActivity;
import com.example.fe_quanlyvattu.model.Kho;
import com.example.fe_quanlyvattu.model.LoaiVt;

import java.util.List;

public class LoaiVtAdapter extends RecyclerView.Adapter<LoaiVtAdapter.LoaiVtViewHolder> {
    private List<LoaiVt> loaiVtList;
    private Context context;
    public LoaiVtAdapter(List<LoaiVt> loaiVtList, Context context) {
        this.loaiVtList = loaiVtList;
        this.context = context;
    }

    @NonNull
    @Override
    public LoaiVtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loai_vat_tu, parent, false);
        return new LoaiVtViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiVtAdapter.LoaiVtViewHolder holder, int position) {
        LoaiVt loaiVt = loaiVtList.get(position);
        holder.ten.setText(loaiVt.getTenloai());
        holder.mota.setText(loaiVt.getMota());
        holder.soluong.setText(String.valueOf(loaiVt.getSoluong()));// chuyển sang int
        holder.lancapnhat.setText(loaiVt.getLancapnhat());

        // Sửa
        holder.btnSua.setOnClickListener(v -> {
            Intent intent = new Intent(context, SualoaiVtActivity.class);
            intent.putExtra("tenloai", loaiVt.getTenloai());
            intent.putExtra("mota", loaiVt.getMota());
            intent.putExtra("soluong", loaiVt.getSoluong());
            intent.putExtra("lancapnhat", loaiVt.getLancapnhat());
            intent.putExtra("position", position);

            // context phải là Activity mới gọi startActivityForResult được
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, 1001);
            }
        });
        // Xóa
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
        TextView ten, mota, soluong, lancapnhat;
        Button btnSua, btnXoa;
        public LoaiVtViewHolder(@NonNull View itemView) {
            super(itemView);
            ten = itemView.findViewById(R.id.tenloai);
            mota = itemView.findViewById(R.id.mota);
            soluong = itemView.findViewById(R.id.soluong);
            lancapnhat = itemView.findViewById(R.id.ngaycapnhat);
            btnSua = itemView.findViewById(R.id.btnSua);
            btnXoa = itemView.findViewById(R.id.btnXoa);
        }
    }
    // Hàm để cập nhật lại item sau khi sửa
    public void updateItem(int position, LoaiVt newLoaiVt) {
        loaiVtList.set(position, newLoaiVt);
        notifyItemChanged(position);
    }
}
