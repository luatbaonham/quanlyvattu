package com.example.fe_quanlyvattu.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.api.PhieuNhapApi;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phieunhap.CreatePhieuNhapRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoPhieuNhapActivity extends AppCompatActivity {

    EditText edtSupplierId, edtItemCode, edtItemPrice, edtItemQuantity, edtDateOrder, edtDateReceived, edtNote;
    Button btnTaoPhieuNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phieu_nhap);

        // Ánh xạ
        edtSupplierId = findViewById(R.id.edtSupplierId);
        edtItemCode = findViewById(R.id.edtItemCode);
        edtItemPrice = findViewById(R.id.edtItemPrice);
        edtItemQuantity = findViewById(R.id.edtItemQuantity);
        edtDateOrder = findViewById(R.id.edtDateOrder);
        edtDateReceived = findViewById(R.id.edtDateReceived);
        edtNote = findViewById(R.id.edtNote);
        btnTaoPhieuNhap = findViewById(R.id.btnTaoPhieuNhap);

        // Chọn ngày
        edtDateOrder.setOnClickListener(v -> showDatePickerDialog(edtDateOrder));
        edtDateReceived.setOnClickListener(v -> showDatePickerDialog(edtDateReceived));

        // Gửi dữ liệu
        btnTaoPhieuNhap.setOnClickListener(v -> guiPhieuNhap());
    }

    private void showDatePickerDialog(EditText targetEditText) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                    targetEditText.setText(date);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void guiPhieuNhap() {
        try {
            CreatePhieuNhapRequest request = buildRequest();

            PhieuNhapApi api = RetrofitClient.getInstance(this).create(PhieuNhapApi.class);
            Call<Void> call = api.taoPhieuNhap(request);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(TaoPhieuNhapActivity.this, "Tạo phiếu thành công!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        String errorMessage = "Lỗi " + response.code();
                        try {
                            if (response.errorBody() != null) {
                                errorMessage += ": " + response.errorBody().string();
                            }
                        } catch (IOException ignored) {}
                        Toast.makeText(TaoPhieuNhapActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(TaoPhieuNhapActivity.this, "Thất bại: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_LONG).show();
        }
    }


    private CreatePhieuNhapRequest buildRequest() throws NumberFormatException {
        int supplierId = Integer.parseInt(edtSupplierId.getText().toString());
        String code = edtItemCode.getText().toString();
        double price = Double.parseDouble(edtItemPrice.getText().toString());
        int quantity = Integer.parseInt(edtItemQuantity.getText().toString());
        String dateOfOrder = edtDateOrder.getText().toString();
        String dateOfReceived = edtDateReceived.getText().toString();
        String note = edtNote.getText().toString();

        CreatePhieuNhapRequest.Supplier supplier = new CreatePhieuNhapRequest.Supplier(supplierId);
        CreatePhieuNhapRequest.Item item = new CreatePhieuNhapRequest.Item(code, price, quantity);
        List<CreatePhieuNhapRequest.Item> items = Collections.singletonList(item);

        return new CreatePhieuNhapRequest(supplier, items, dateOfReceived, dateOfOrder, note);
    }

}
