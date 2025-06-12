package com.example.fe_quanlyvattu.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fe_quanlyvattu.R;

import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.api.PhieuMuonApi;
import com.example.fe_quanlyvattu.data.api.RetrofitClient;
import com.example.fe_quanlyvattu.data.model.phieumuon.CreatePhieuMuonRequest;
import com.example.fe_quanlyvattu.data.model.phieumuon.PhieuMuonResponse;
import com.example.fe_quanlyvattu.data.model.phong.Room;
import com.example.fe_quanlyvattu.data.model.phong.RoomItem;
import com.example.fe_quanlyvattu.data.repository.PhongRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaoPhieuMuonActivity extends AppCompatActivity {
    private AutoCompleteTextView edtRoomId, edtGroupEquipmentCode, edtGroupQuantity;
    private EditText edtBorrowDate, edtNote;
    private Button btnTaoPhieuMuon;
    private PhongRepository repository;
    private List<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_phieu_muon);
        //ánh xạ
        edtRoomId = findViewById(R.id.edtRoomId);
        edtGroupEquipmentCode = findViewById(R.id.edtGroupEquipmentCode);
        edtGroupQuantity = findViewById(R.id.edtGroupQuantity);
        edtBorrowDate = findViewById(R.id.edtBorrowDate);
        edtNote = findViewById(R.id.edtNote);
        btnTaoPhieuMuon = findViewById(R.id.btnTaoPhieuMuon);

        // Fetch data
        repository = new PhongRepository(this);
        getDanhSachPhong();
        // Setup Adapter
        //roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("P101", "P102", "P103"));
        //edtRoomId.setAdapter(roomList);

        ArrayAdapter<String> codeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("MAYTINH-MAC-2025", "LOA", "MIC"));
        edtGroupEquipmentCode.setAdapter(codeAdapter);

        ArrayAdapter<String> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, Arrays.asList("1", "2", "3", "4", "5"));
        edtGroupQuantity.setAdapter(quantityAdapter);

        // Chọn ngày
        edtBorrowDate.setOnClickListener(v -> showDatePickerDialog(edtBorrowDate));

        // Gửi dữ liệu
        btnTaoPhieuMuon.setOnClickListener(v -> guiPhieuMuon());
        edtRoomId.setOnClickListener(v -> edtRoomId.showDropDown());
        edtGroupEquipmentCode.setOnClickListener(v -> edtGroupEquipmentCode.showDropDown());
        edtGroupQuantity.setOnClickListener(v -> edtGroupQuantity.showDropDown());

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

    private void guiPhieuMuon() {
        try {
            CreatePhieuMuonRequest request = buildRequest();

            PhieuMuonApi api = RetrofitClient.getInstance(this).create(PhieuMuonApi.class);
            Call<Void> call = api.taoPhieuMuon(request);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(TaoPhieuMuonActivity.this, "Tạo phiếu mượn thành công!", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        String errorMessage = "Lỗi " + response.code();
                        try {
                            if (response.errorBody() != null) {
                                errorMessage += ": " + response.errorBody().string();
                            }
                        } catch (IOException ignored) {}
                        Toast.makeText(TaoPhieuMuonActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(TaoPhieuMuonActivity.this, "Thất bại: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_LONG).show();
        }
    }

    private CreatePhieuMuonRequest buildRequest() throws NumberFormatException {
        String roomId = getSelectedRoomId(); // Sử dụng hàm lấy đúng id
        if (roomId == null) {
            throw new NumberFormatException("Phòng không hợp lệ");
        }

        String borrowDate = edtBorrowDate.getText().toString();
        String note = edtNote.getText().toString();
        String code = edtGroupEquipmentCode.getText().toString();
        int quantity = Integer.parseInt(edtGroupQuantity.getText().toString());

        CreatePhieuMuonRequest.Group group = new CreatePhieuMuonRequest.Group(code, quantity);
        List<CreatePhieuMuonRequest.Group> groups = Collections.singletonList(group);

        return new CreatePhieuMuonRequest(borrowDate, note, roomId, groups);
    }



    private void getDanhSachPhong(){
        repository.getAllPhong(new ApiCallback<List<Room>>() {
            @Override
            public void onSuccess(List<Room> data) {
                roomList = data;

                ArrayAdapter<Room> adapter = new ArrayAdapter<>(
                        TaoPhieuMuonActivity.this,
                        android.R.layout.simple_dropdown_item_1line,
                        roomList
                );
                edtRoomId.setAdapter(adapter);
            }
            @Override
            public void onError(String errorMessage) {
                Toast.makeText(TaoPhieuMuonActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
    });
    }
    private String getSelectedRoomId() {
        String selectedText = edtRoomId.getText().toString();
        for (Room room : roomList) {
            if (room.toString().equals(selectedText)) {
                return room.getId();
            }
        }
        return null; // hoặc thông báo lỗi nếu muốn
    }


}
