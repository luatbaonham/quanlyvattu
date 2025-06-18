package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.profile.UProfile;
import com.example.fe_quanlyvattu.data.model.profile.UpdateProfileRequest;
import com.example.fe_quanlyvattu.data.repository.ProfileRepository;

public class ActivityCapNhatHoSo extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView imgAvatar;
    private EditText etFirstName, etLastName, etPhoneNumber, etAddress;
    private Button btnSave;

    private Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capnhat_hoso);

        // Khởi tạo view
        imgAvatar = findViewById(R.id.imgAvatar);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etAddress = findViewById(R.id.etAddress);
        btnSave = findViewById(R.id.btnSave);

        // Set dữ liệu từ JSON giả định
        SessionManager sessionManager = new SessionManager(ActivityCapNhatHoSo.this);
        etFirstName.setText(sessionManager.getFirstName());
        etLastName.setText(sessionManager.getLastName());
        etPhoneNumber.setText(sessionManager.getPhone());
        etAddress.setText(sessionManager.getAddress());

        Glide.with(this)
                .load("https://thinoproawsbucket.s3.ap-southeast-1.amazonaws.com/thi.png")
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_user)
                .into(imgAvatar);

        // Sự kiện chọn ảnh
        imgAvatar.setOnClickListener(view -> openImagePicker());

        // Sự kiện lưu
        btnSave.setOnClickListener(view -> {
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String address = etAddress.getText().toString().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            // Giả lập URL ảnh nếu chưa chọn ảnh mới
            String avatarUrl = "https://thinoproawsbucket.s3.ap-southeast-1.amazonaws.com/thi.png";

            // Nếu người dùng đã chọn ảnh mới => TODO: upload ảnh và lấy URL từ server/s3
            if (selectedImageUri != null) {
                // Hiện tại, bạn chưa có upload ảnh lên server/S3 nên để trống hoặc giữ URL cũ
                // Bạn có thể gán avatarUrl = "" hoặc gửi selectedImageUri nếu API hỗ trợ multipart
                avatarUrl = ""; // hoặc giữ nguyên URL cũ nếu chưa hỗ trợ upload
            }

            // Gửi thông tin cập nhật lên server
            UpdateProfileRequest request = new UpdateProfileRequest(
                    firstName,
                    lastName,
                    avatarUrl,
                    phone,
                    address
            );

            ProfileRepository repository = new ProfileRepository(this);
            repository.updateProfile(request, new ApiCallback<UProfile>() {
                @Override
                public void onSuccess(UProfile result) {
                    Toast.makeText(ActivityCapNhatHoSo.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    // Có thể finish() để quay lại màn hình trước
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(ActivityCapNhatHoSo.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    // Hàm mở thư viện chọn ảnh
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh đại diện"), PICK_IMAGE_REQUEST);
    }

    // Nhận ảnh khi chọn xong
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imgAvatar.setImageURI(selectedImageUri);
        }
    }
}
