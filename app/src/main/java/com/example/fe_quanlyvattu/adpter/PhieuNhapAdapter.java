package com.example.fe_quanlyvattu.adpter;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.util.Base64;
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
import com.example.fe_quanlyvattu.utils.QRUtil;
import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
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
        this.repository = new PhieuNhapRepository(context);
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

        if ("approved".equalsIgnoreCase(phieu.getStatus())) {
            holder.btnGenerateCode.setVisibility(View.VISIBLE);
        } else {
            holder.btnGenerateCode.setVisibility(View.GONE);
        }

        holder.btnGenerateCode.setOnClickListener(v -> {
            int totalDevices = 0;
            for (Item item : phieu.getItems()) {
                totalDevices += item.getQuantity();
            }

            if (totalDevices == 0) {
                Toast.makeText(context, "Không có thiết bị nào để tạo", Toast.LENGTH_SHORT).show();
                return;
            }

            final int[] successCount = {0};
            final int[] errorCount = {0};

            holder.btnGenerateCode.setEnabled(false);
            Toast.makeText(context, "Đang tạo " + totalDevices + " thiết bị...", Toast.LENGTH_SHORT).show();

            for (Item item : phieu.getItems()) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    String serialNumber = UUID.randomUUID().toString();
                    Bitmap qrCode = QRUtil.generateQRCode(serialNumber, 512);
                    String qrBase64 = bitmapToBase64(qrCode);

                    Map<String, Object> requestBody = new HashMap<>();
                    requestBody.put("groupEquipmentCode", item.getCode());
                    requestBody.put("status", "available");
                    requestBody.put("serialNumber", serialNumber);
                    //requestBody.put("qrCodeImage", qrBase64);

                    int finalTotalDevices = totalDevices;
                    repository.taoEquipmentMoi(new ApiCallback<Object>() {
                        @Override
                        public void onSuccess(Object response) {
                            successCount[0]++;
                            checkCompletion(finalTotalDevices, successCount[0], errorCount[0], holder);
                        }

                        @Override
                        public void onError(String errorMessage) {
                            errorCount[0]++;
                            checkCompletion(finalTotalDevices, successCount[0], errorCount[0], holder);
                        }
                    }, requestBody);
                }
            }
        });
    }

    private void checkCompletion(int totalDevices, int successCount, int errorCount, ViewHolder holder) {
        if (successCount + errorCount == totalDevices) {
            new Handler(Looper.getMainLooper()).post(() -> {
                holder.btnGenerateCode.setEnabled(true);
                String message = "Tạo thành công " + successCount + "/" + totalDevices + " thiết bị";
                if (errorCount > 0) {
                    message += " (" + errorCount + " lỗi)";
                }
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            });
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
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
        MaterialButton btnGenerateCode;
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
            btnGenerateCode = itemView.findViewById(R.id.btnGenerateCode);
        }
    }

    private void showItemDialog(Context context, List<Item> items) {
        Dialog dialog = new Dialog(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_item_list, null);

        RecyclerView recyclerView = dialogView.findViewById(R.id.rvItems);
        ItemDialogAdapter adapter = new ItemDialogAdapter(items);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        dialog.setContentView(dialogView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.SlideDownDialogTheme;

        Button btnClose = dialogView.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}