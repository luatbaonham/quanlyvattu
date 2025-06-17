package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
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
import java.util.Map;

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

        // Debug log quan trọng
        Log.d("ADAPTER_DEBUG", "Item: " + loaiVt.getName());
        Log.d("ADAPTER_COUNTS", "Raw counts: " + loaiVt.getEquipmentStatusCounts());
        Log.d("ADAPTER_COUNTS", "Parsed counts: " +
                (loaiVt.getEquipmentStatusCounts() != null ?
                        loaiVt.getEquipmentStatusCounts() : "null"));

        // Hiển thị thông tin cơ bản
        holder.ten.setText(loaiVt.getName());
        holder.mota.setText(loaiVt.getDescription() != null ? loaiVt.getDescription() : "");

        // Hiển thị hãng sản xuất
        holder.hang.setText(loaiVt.getManufacturer() != null ?
                loaiVt.getManufacturer().getName() : "Không xác định");

        // Xử lý số lượng
        String quantityText = buildQuantityText(loaiVt);
        holder.soluong.setText(quantityText);
        holder.lancapnhat.setText(formatDate(loaiVt.getUpdatedAt()));
    }

    private String buildQuantityText(NhomVt loaiVt) {
        if (loaiVt.getEquipmentStatusCounts() == null) {
            return "Không có thiết bị nào";
        }

        Map<String, Integer> statusMap = loaiVt.getEquipmentStatusCounts();
        if (statusMap == null || statusMap.isEmpty()) {
            return "Không có thiết bị nào";
        }

        int total = 0;
        StringBuilder details = new StringBuilder();

        for (Map.Entry<String, Integer> entry : statusMap.entrySet()) {
            if (entry.getValue() != null && entry.getValue() > 0) {
                total += entry.getValue();
                if (details.length() > 0) details.append(", ");
                details.append(convertStatusKeyToText(entry.getKey()))
                        .append(": ")
                        .append(entry.getValue());
            }
        }

        if (total == 0) {
            return "Không có thiết bị nào";
        }

        String unit = (loaiVt.getUnitOfMeasure() != null &&
                loaiVt.getUnitOfMeasure().getName() != null) ?
                loaiVt.getUnitOfMeasure().getName() : "";

        return "Tổng: " + total + " (" + details.toString() + ")" + (unit.isEmpty() ? "" : " " + unit);
    }

    private String formatDate(String rawDate) {
        if (rawDate == null || rawDate.isEmpty()) return "";
        try {
            // Lấy phần ngày tháng (bỏ phần thời gian và timezone)
            return rawDate.split("T")[0];
        } catch (Exception e) {
            return rawDate;
        }
    }

    private void showDeleteConfirmation(int position) {
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
        notifyDataSetChanged();
    }

    private String convertStatusKeyToText(String key) {
        switch (key) {
            case "available": return "Sẵn sàng";
            case "in_use": return "Đang dùng";
            case "pending_transfer": return "Chờ chuyển";
            case "liquidation": return "Thanh lý";
            default: return key;
        }
    }
}