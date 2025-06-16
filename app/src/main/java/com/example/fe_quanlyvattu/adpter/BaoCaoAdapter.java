package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.home_baocao.PhieuGroup;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.home_baocao.ReportItem;

import java.util.List;

public class BaoCaoAdapter extends RecyclerView.Adapter<BaoCaoAdapter.BaoCaoViewHolder> {

    private List<ReportItem> reportList;

    public BaoCaoAdapter(List<ReportItem> reportList) {
        this.reportList = reportList;
    }


    @NonNull
    @Override
    public BaoCaoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_baocao, parent, false);
        return new BaoCaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaoCaoViewHolder holder, int position) {
        ReportItem item = reportList.get(position);

        // 1. Loại phiếu
        String loaiPhieuHienThi;
        switch (item.getLoaiPhieu()) {
            case "import":
                loaiPhieuHienThi = "📥 Phiếu Nhập";
                break;
            case "borrow":
                loaiPhieuHienThi = "📋 Phiếu Mượn";
                break;
            case "liquidation":
                loaiPhieuHienThi = "🗑️ Phiếu Thanh Lý";
                break;
            case "transfer":
                loaiPhieuHienThi = "🔄 Phiếu Điều Chuyển";
                break;
            default:
                loaiPhieuHienThi = "❓ Không rõ";
        }

        // 2. Trạng thái
        String trangThai = item.getStatus().toLowerCase();
        String trangThaiHienThi;

        switch (trangThai) {
            case "received":
                trangThaiHienThi = "📦 Đã nhận";
                break;
            case "rejected":
                trangThaiHienThi = "❌ Bị từ chối";
                break;
            case "approved":
                trangThaiHienThi = "✅ Đã duyệt";
                break;
            case "returned":
                trangThaiHienThi = "↩️ Đã trả";
                break;
            case "requested":
                trangThaiHienThi = "📝 Đã yêu cầu";
                break;
            default:
                trangThaiHienThi = "❓ Không rõ";
        }

        // 3. Số lượng
        String soLuongHienThi = "🔢 Số lượng: " + item.getCount();

        // Gán dữ liệu vào ViewHolder
        holder.tvLoaiPhieu.setText(loaiPhieuHienThi);
        holder.tvTrangThai.setText("Trạng thái: " + trangThaiHienThi);
        holder.tvSoLuong.setText(soLuongHienThi);
    }


    @Override
    public int getItemCount() {
        return reportList != null ? reportList.size() : 0;
    }

    public static class BaoCaoViewHolder extends RecyclerView.ViewHolder {
        TextView tvLoaiPhieu, tvTrangThai, tvSoLuong;

        public BaoCaoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLoaiPhieu = itemView.findViewById(R.id.tvLoaiPhieu);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThai);
            tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        }
    }
}


