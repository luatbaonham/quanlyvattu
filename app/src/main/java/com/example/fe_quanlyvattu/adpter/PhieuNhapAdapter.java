package com.example.fe_quanlyvattu.adpter;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import java.text.ParseException;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import androidx.appcompat.app.AlertDialog;




import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.ViewHolder> {
    private List<PhieuNhap> danhSach;
    Context context;

    public PhieuNhapAdapter(Context context, List<PhieuNhap> danhSach) {
        this.context = context;
        this.danhSach = danhSach;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieunhap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PhieuNhap phieu = danhSach.get(position);
        holder.tvMaPhieuNhap.setText(String.valueOf(phieu.getId()));
        holder.tvNguoiTao.setText(phieu.getRequestedUser().getUsername());
        holder.tvNhaCungCap.setText(phieu.getSupplier().getName());

        String ngayDat = phieu.getDateOfOrder(); // dạng "2025-06-04"
        String formattedDate = ngayDat; // mặc định là ngày gốc nếu parse lỗi
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            Date date = sdfInput.parse(ngayDat); // parse từ String -> Date
            if (date != null) {
                formattedDate = sdfOutput.format(date); // format Date -> String
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvNgayDat.setText(formattedDate);


        // Có thể xử lý thêm nếu bạn có danh sách vật tư hoặc số lượng
        // holder.tvTenVatTu.setText(...);
        // holder.tvSoLuong.setText(...);
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(), "Chọn phiếu: " + phieu.getId(), Toast.LENGTH_SHORT).show();
        });

        holder.btnSua.setOnClickListener(v -> {
            showEditDialog(v.getContext(), phieu, position);
        });

    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    public void updateList(List<PhieuNhap> newList) {
        this.danhSach.clear();
        this.danhSach.addAll(newList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaPhieuNhap, tvTenVatTu, tvNguoiTao, tvNgayDat, tvSoLuong, tvNhaCungCap;
        Button btnSua, btnXoa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieuNhap = itemView.findViewById(R.id.tvMaPhieu);
            tvTenVatTu = itemView.findViewById(R.id.tvTenVt);
            tvNguoiTao = itemView.findViewById(R.id.tvNguoitao);
            tvNgayDat = itemView.findViewById(R.id.tvNgaydat);
            tvSoLuong = itemView.findViewById(R.id.tvSoluong);
            tvNhaCungCap = itemView.findViewById(R.id.tvNhaCC);
            btnSua = itemView.findViewById(R.id.btnEdit);
            btnXoa = itemView.findViewById(R.id.btnDelete);
        }
    }

    private void showEditDialog(Context context, PhieuNhap phieu, int position) {
        // Tạo view custom cho dialog
        View dialogView = LayoutInflater.from(context).inflate(R.layout.suaphieunhap, null);

        EditText edtNgayDat = dialogView.findViewById(R.id.edtNgayDat);
        EditText edtNhaCC = dialogView.findViewById(R.id.edtNhaCungCap);
        EditText edtSoLuong = dialogView.findViewById(R.id.edtSoLuong);
        EditText edtTenVatTu = dialogView.findViewById(R.id.edtTenVatTu);
        EditText edtNguoiTao = dialogView.findViewById(R.id.edtNguoiTao);

        edtNhaCC.setText(phieu.getSupplier().getName());
////        edtSoLuong.setText(String.valueOf(phieu.getSupplier().getQuantity()));
//        edtTenVatTu.setText(phieu.getRequestedUser().getUsername());
        edtNguoiTao.setText(phieu.getRequestedUser().getUsername());

        String ngayDat = phieu.getDateOfOrder();
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = sdfInput.parse(ngayDat);
            if (date != null) {
                edtNgayDat.setText(sdfOutput.format(date));
            } else {
                edtNgayDat.setText(ngayDat); // Nếu parse lỗi thì gán luôn
            }
        } catch (ParseException e) {
            e.printStackTrace();
            edtNgayDat.setText(ngayDat);
        }

        edtNgayDat.setOnClickListener(v -> showDatePickerDialog(edtNgayDat));

        new AlertDialog.Builder(context)
                .setTitle("Sửa phiếu nhập")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {

                    String ngayDatSua = edtNgayDat.getText().toString();
                    try {
                        // Chuyển ngày về dạng "yyyy-MM-dd" để lưu/phù hợp API
                        SimpleDateFormat sdfInput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        Date date = sdfInput.parse(ngayDatSua);
                        if (date != null) {
                            ngayDatSua = sdfOutput.format(date);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        // Nếu lỗi parse thì giữ nguyên chuỗi nhập
                    }
                    
                    // Cập nhật thông tin
                    phieu.getSupplier().setName(edtNhaCC.getText().toString());
                    phieu.getRequestedUser().setUsername(edtNguoiTao.getText().toString());
//                    phieu.getSupplier().get(0).setQuantity(Integer.parseInt(edtSoLuong.getText().toString()));
//                    phieu.getItems().get(0).setName(edtTenVatTu.getText().toString());

                    notifyItemChanged(position); // Cập nhật giao diện
                    Toast.makeText(context, "Đã sửa phiếu: " + phieu.getId(), Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void showDatePickerDialog(EditText editText) {
        Calendar calendar = Calendar.getInstance();
        try {
            // Parse ngày hiện tại nếu có
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            if (!editText.getText().toString().isEmpty()) {
                Date date = sdf.parse(editText.getText().toString());
                if (date != null) calendar.setTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    editText.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

}
