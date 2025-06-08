package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieumuon.BorrowReceipt;
import com.example.fe_quanlyvattu.data.model.phieumuon.RequestItem;

import java.util.List;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.ViewHolder> {

    private List<BorrowReceipt> danhSach;

    public PhieuMuonAdapter(List<BorrowReceipt> danhSach) {
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BorrowReceipt phieu = danhSach.get(position);

        holder.tvMaPhieu.setText("Mã: " + phieu.getId());
        holder.tvNguoiMuon.setText("Người mượn: " + phieu.getRequestedBy().getUsername());
        holder.tvNgayTao.setText("Ngày tạo: " + (phieu.getCreatedTime() != null ? phieu.getCreatedTime().substring(0, 10) : "N/A"));
        holder.tvPhong.setText("Phòng: " + phieu.getRoom().getRoomName());

        int tongSoLuong = 0;
        for (RequestItem item : phieu.getRequestItems()) {
            tongSoLuong += item.getQuantity();
        }
        holder.tvSoLuong.setText("Tổng SL: " + tongSoLuong);

        holder.itemView.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "Chọn phiếu mượn: " + phieu.getId(), Toast.LENGTH_SHORT).show()
        );
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
        TextView tvMaPhieu, tvNguoiMuon, tvNgayTao, tvPhong, tvSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieu = itemView.findViewById(R.id.tvMaPhieu);
            tvNguoiMuon = itemView.findViewById(R.id.tvNguoiMuon);
            tvNgayTao = itemView.findViewById(R.id.tvNgayTao);
            tvPhong = itemView.findViewById(R.id.tvPhong);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}
