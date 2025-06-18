package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.ScanActivity;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phieumuon.BorrowReceipt;
import com.example.fe_quanlyvattu.data.model.phieumuon.RequestItem;
import com.example.fe_quanlyvattu.data.repository.PhieuMuonRepository;
import com.example.fe_quanlyvattu.utils.StatusUtil;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {

    private final List<BorrowReceipt> danhSach;
    private final Context context;
    private final PhieuMuonRepository repository;
    private final OnStatusUpdateListener statusUpdateListener;

    public interface OnStatusUpdateListener {
        void onStatusUpdated();
    }

    public PhieuMuonAdapter(Context context, List<BorrowReceipt> danhSach, OnStatusUpdateListener listener) {
        this.context = context;
        this.danhSach = danhSach;
        this.repository = new PhieuMuonRepository(context); // ✅ dùng đúng repository
        this.statusUpdateListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        BorrowReceipt phieu = danhSach.get(position);

        holder.tvMaPhieu.setText("Mã: " + phieu.getId());
        holder.tvNguoiMuon.setText("Người mượn: " + phieu.getRequestedBy().getUsername());
        holder.tvNgayTao.setText("Ngày tạo: " + (phieu.getCreatedTime() != null ? phieu.getCreatedTime().substring(0, 10) : "N/A"));
        holder.tvPhong.setText("Phòng: " + phieu.getRoom().getRoomName());
        holder.tvTrangThai.setText("Trạng thái: " + StatusUtil.getStatusDisplayName(phieu.getStatus()));

        int tongSoLuong = 0;
        for (RequestItem item : phieu.getRequestItems()) {
            tongSoLuong += item.getQuantity();
        }
        holder.tvSoLuong.setText("Tổng SL: " + tongSoLuong);

        // Hiện nút scan nếu trạng thái là pending
        if ("pending".equalsIgnoreCase(phieu.getStatus())) {
            holder.btnScan.setVisibility(View.VISIBLE);
        } else {
            holder.btnScan.setVisibility(View.GONE);
        }

        // Xử lý quét QR
        holder.btnScan.setOnClickListener(v -> {
            if (phieu.getRequestItems().isEmpty()) {
                Toast.makeText(context, "Phiếu mượn không có vật tư", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mở ScanActivity và truyền room_id
            Intent intent = new Intent(context, ScanActivity.class);
            intent.putExtra("ROOM_ID", phieu.getRoom().getRoomId()); // ✅ truyền roomId
            context.startActivity(intent);
        });

        // Xử lý cập nhật trạng thái
        holder.btnUpdateStatus.setOnClickListener(v -> {
            String[] trangThaiArray = {"pending", "approved", "rejected", "completed"};
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Cập nhật trạng thái");

            builder.setItems(trangThaiArray, (dialog, which) -> {
                String newStatus = trangThaiArray[which];

                // Dialog nhập lý do
                AlertDialog.Builder reasonBuilder = new AlertDialog.Builder(context);
                reasonBuilder.setTitle("Nhập lý do");

                final EditText input = new EditText(context);
                input.setHint("Nhập lý do...");
                input.setPadding(50, 30, 50, 30);
                reasonBuilder.setView(input);

                reasonBuilder.setPositiveButton("Xác nhận", (d, w) -> {
                    String reason = input.getText().toString().trim();

                    String action;
                    switch (newStatus) {
                        case "approved": action = "approve"; break;
                        case "rejected": action = "reject"; break;
                        case "completed": action = "mark-returned"; break;
                        default:
                            Toast.makeText(context, "Trạng thái không hợp lệ", Toast.LENGTH_SHORT).show();
                            return;
                    }

                    repository.thucHienHanhDong(phieu.getId(), action, reason, new ApiCallback<Object>() {
                        @Override
                        public void onSuccess(Object response) {
                            Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            phieu.setStatus(newStatus);
                            notifyItemChanged(position);
                            if (statusUpdateListener != null) {
                                statusUpdateListener.onStatusUpdated();
                            }
                        }

                        @Override
                        public void onError(String errorMessage) {
                            Toast.makeText(context, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                });

                reasonBuilder.setNegativeButton("Hủy", null);
                reasonBuilder.show();
            });

            builder.show();
        });
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    public void updateList(List<BorrowReceipt> newList) {
        this.danhSach.clear();
        this.danhSach.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhieu, tvNguoiMuon, tvNgayTao, tvPhong, tvSoLuong, tvTrangThai;
        MaterialButton btnScan;
        Button btnUpdateStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvNguoiMuon = itemView.findViewById(R.id.tvNguoiMuon);
            tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
            tvPhong = itemView.findViewById(R.id.tvPhong);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            btnScan = itemView.findViewById(R.id.btnScan);
            btnUpdateStatus = itemView.findViewById(R.id.btnUpdateStatus);
        }
    }
}
