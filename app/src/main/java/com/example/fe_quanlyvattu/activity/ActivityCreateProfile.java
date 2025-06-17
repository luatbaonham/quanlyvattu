package com.example.fe_quanlyvattu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.profile.CreateProfileRequest;
import com.example.fe_quanlyvattu.data.model.profile.Profile;
import com.example.fe_quanlyvattu.data.repository.ProfileRepository;
import com.example.fe_quanlyvattu.utils.AwsUploadUtil;


import java.io.File;

public class ActivityCreateProfile extends AppCompatActivity {

    private EditText edtFirstName, edtLastName, edtPhone, edtAddress;
    private Button btnSaveProfile;
    private ImageView imgAvatar;
    private ProfileRepository profileRepository;

    private String imageUrl = "https://thinoproawsbucket.s3.ap-southeast-1.amazonaws.com/thi.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_hoso);

        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtPhone = findViewById(R.id.edtPhone);
        edtAddress = findViewById(R.id.edtAddress);
        imgAvatar = findViewById(R.id.imgAvatar);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);

        profileRepository = new ProfileRepository(this);

        // Load avatar mặc định
        Glide.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_user)
                .circleCrop()
                .into(imgAvatar);

        // Chọn ảnh khi click vào avatar
        imgAvatar.setOnClickListener(v -> pickImageLauncher.launch("image/*"));

        btnSaveProfile.setOnClickListener(v -> {
            String firstName = edtFirstName.getText().toString().trim();
            String lastName = edtLastName.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            String address = edtAddress.getText().toString().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            CreateProfileRequest request = new CreateProfileRequest(
                    firstName,
                    lastName,
                    imageUrl,
                    phone,
                    address
            );

            profileRepository.createProfile(request, new ApiCallback<Profile>() {
                @Override
                public void onSuccess(Profile profile) {
                    Toast.makeText(ActivityCreateProfile.this, "Tạo hồ sơ thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(String errorMessage) {
                    Toast.makeText(ActivityCreateProfile.this, "Lỗi tạo hồ sơ: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    // ActivityResult để chọn ảnh
    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    uploadImageToAws(uri);
                }
            });

    // Upload ảnh lên AWS S3
    private void uploadImageToAws(Uri imageUri) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang tải ảnh lên...");
        progressDialog.setCancelable(false); // không cho người dùng hủy
        progressDialog.show();

        AwsUploadUtil.upload(this, imageUri, new ApiCallback<String>() {
            @Override
            public void onSuccess(String url) {
                progressDialog.dismiss(); // Ẩn dialog khi thành công

                imageUrl = url;

                Glide.with(ActivityCreateProfile.this)
                        .load(url)
                        .circleCrop()
                        .into(imgAvatar);

                Toast.makeText(ActivityCreateProfile.this, "Tải ảnh thành công!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String errorMessage) {
                progressDialog.dismiss(); // Ẩn dialog khi lỗi

                Toast.makeText(ActivityCreateProfile.this, "Lỗi tải ảnh: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
