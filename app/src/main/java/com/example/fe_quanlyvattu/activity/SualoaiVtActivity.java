//package com.example.fe_quanlyvattu.activity;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.fe_quanlyvattu.R;
//import com.example.fe_quanlyvattu.data.model.vattu.loaivattu.LoaiVt;
//
//public class SualoaiVtActivity extends AppCompatActivity {
//    private EditText edtTenLoai, edtMoTa, edtSoluong, edtLancapnhat;
//    private Button btnHuy, btnLuu;
//    int position = -1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sua_loaivt);
//
//        edtTenLoai = findViewById(R.id.edtTen);
//        edtMoTa = findViewById(R.id.edtMoTa);
//        edtSoluong = findViewById(R.id.edtSoLuong);
//        edtLancapnhat = findViewById(R.id.edtLanCapNhat);
//        btnHuy = findViewById(R.id.btnHuy);
//        btnLuu = findViewById(R.id.btnLuu);
//
//
//        // Nhận dữ liệu từ adapter
//        Intent intent = getIntent();
//        if (intent != null) {
//            edtTenLoai.setText(intent.getStringExtra("tenloai"));
//            edtMoTa.setText(intent.getStringExtra("mota"));
//            edtSoluong.setText(String.valueOf(intent.getIntExtra("soluong", 0)));
//            edtLancapnhat.setText(intent.getStringExtra("lancapnhat"));
//            position = intent.getIntExtra("position", -1);
//        }
//
//        btnLuu.setOnClickListener(v -> {
//            String ten = edtTenLoai.getText().toString();
//            String mota = edtMoTa.getText().toString();
//            int soluong = Integer.parseInt(edtSoluong.getText().toString());
//            String lancapnhat = edtLancapnhat.getText().toString();
//
//            LoaiVt loaiVt = new LoaiVt(ten, mota, soluong, lancapnhat);
//
//            Intent resultIntent = new Intent();
//            resultIntent.putExtra("updatedLoaiVt", loaiVt);
//            resultIntent.putExtra("position", position);
//            setResult(Activity.RESULT_OK, resultIntent);
//            Toast.makeText(this, "Đã lưu!", Toast.LENGTH_SHORT).show();
//            finish();
//        });
//
//
//        btnHuy.setOnClickListener(v -> finish());
//    }
//}
