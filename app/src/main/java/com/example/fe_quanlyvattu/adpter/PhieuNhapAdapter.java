package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.phieunhap.CapNhatTrangThaiRequest;
import com.example.fe_quanlyvattu.data.model.phieunhap.Item;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhap;
import com.example.fe_quanlyvattu.data.model.phieunhap.PhieuNhapUpdateResponse;
import com.example.fe_quanlyvattu.data.repository.PhieuNhapRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PhieuNhapAdapter extends RecyclerView.Adapter<PhieuNhapAdapter.ViewHolder> {

    private List<PhieuNhap> danhSach;
    private final Context context;
    private final PhieuNhapRepository repository;

    public PhieuNhapAdapter(Context context, List<PhieuNhap> danhSach) {
        this.context = context;
        this.danhSach = danhSach;
        this.repository = new PhieuNhapRepository(context); // Thêm dòng này nếu bạn chưa có DI
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieunhap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String[] trangThaiArray = {"rejected", "completed", "approved", "requested"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, trangThaiArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinnerTrangThai.setAdapter(adapter);

        PhieuNhap phieu = danhSach.get(position);
        holder.tvMaPhieuNhap.setText(String.valueOf(phieu.getId()));
        holder.tvNguoiTao.setText(phieu.getRequestedUser().getUsername());
        holder.tvNhaCungCap.setText(phieu.getSupplier().getName());

        String formattedDate = phieu.getDateOfOrder();
        try {
            SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat sdfOutput = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            Date date = sdfInput.parse(formattedDate);
            if (date != null) {
                formattedDate = sdfOutput.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.tvNgayDat.setText(formattedDate);

        // Ẩn Spinner mặc định
        holder.spinnerTrangThai.setVisibility(View.GONE);
        holder.tvTrangthai.setText(phieu.getStatus());

        holder.itemView.setOnClickListener(v -> {
            showItemDialog(context, phieu.getItems());
        });

        holder.btnSua.setOnClickListener(v -> {
            holder.tvTrangthai.setVisibility(View.GONE);
            holder.spinnerTrangThai.setVisibility(View.VISIBLE);
            holder.spinnerTrangThai.setSelection(Arrays.asList(trangThaiArray).indexOf(phieu.getStatus()));

            holder.spinnerTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                    String newStatus = trangThaiArray[pos];

                    if (!newStatus.equalsIgnoreCase(phieu.getStatus())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Nhập lý do cập nhật trạng thái");

                        final EditText input = new EditText(context);
                        input.setInputType(InputType.TYPE_CLASS_TEXT);
                        builder.setView(input);

                        builder.setPositiveButton("OK", (dialog, which) -> {
                            String reason = input.getText().toString();
                            CapNhatTrangThaiRequest request = new CapNhatTrangThaiRequest(phieu.getId(), newStatus, reason);

                            repository.capNhatTrangThai(new ApiCallback<PhieuNhapUpdateResponse>() {
                                @Override
                                public void onSuccess(PhieuNhapUpdateResponse response) {
                                    Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                                    phieu.setStatus(newStatus);
                                    notifyItemChanged(position);
                                }

                                @Override
                                public void onError(String errorMessage) {
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            }, request, phieu.getId());
                        });

                        builder.setNegativeButton("Hủy", (dialog, which) -> {
                            dialog.cancel();
                            holder.spinnerTrangThai.setSelection(Arrays.asList(trangThaiArray).indexOf(phieu.getStatus()));
                        });

                        builder.show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
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
        TextView tvMaPhieuNhap, tvNguoiTao, tvNgayDat, tvNhaCungCap, tvTrangthai;
        Button btnSua;
        Spinner spinnerTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPhieuNhap = itemView.findViewById(R.id.tvMaPhieu);
            tvNguoiTao = itemView.findViewById(R.id.tvNguoitao);
            tvNgayDat = itemView.findViewById(R.id.tvNgaydat);
            tvNhaCungCap = itemView.findViewById(R.id.tvNhaCC);
            tvTrangthai = itemView.findViewById(R.id.tvTrangthai);
            spinnerTrangThai = itemView.findViewById(R.id.spinnerTrangThai);
            btnSua = itemView.findViewById(R.id.btnEdit);
        }
    }

    private void showItemDialog(Context context, List<Item> items) {
        Dialog dialog = new Dialog(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_item_list, null);

        RecyclerView recyclerView = dialogView.findViewById(R.id.rvItems);
        ItemDialogAdapter adapter = new ItemDialogAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        // Gắn layout vào dialog
        dialog.setContentView(dialogView);

        // Nền trong suốt để bo góc đẹp
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Gắn animation style
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlideDownDialogTheme;

        // Nếu muốn thêm nút Đóng:
        Button btnClose = dialogView.findViewById(R.id.btnClose); // Bạn cần có nút này trong layout
        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

}
