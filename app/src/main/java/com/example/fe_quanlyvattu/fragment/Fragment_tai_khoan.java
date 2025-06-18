package com.example.fe_quanlyvattu.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.ActivityCapNhatHoSo;
import com.example.fe_quanlyvattu.activity.activity_dang_nhap;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.model.common.ApiResponse;
import com.example.fe_quanlyvattu.data.model.profile.UpdateProfileRequest;
import com.example.fe_quanlyvattu.data.model.profile.UProfile;
import com.example.fe_quanlyvattu.data.repository.AuthRepository;
import com.example.fe_quanlyvattu.data.repository.ProfileRepository;
import com.example.fe_quanlyvattu.utils.AwsUploadUtil;
import com.example.fe_quanlyvattu.data.api.ApiCallback;

public class Fragment_tai_khoan extends Fragment {

    private ImageView imgAvatar;
    private TextView tvName, tvEmail, tvRole;
    private TextView btnHoSo, btnDangXuat;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvRole = view.findViewById(R.id.tvRole);

        btnHoSo = view.findViewById(R.id.btnHoSo);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);

        btnHoSo.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ActivityCapNhatHoSo.class);
            startActivity(intent);
        });



        SessionManager sessionManager = new SessionManager(getContext());
        String imageUrl = sessionManager.getAvatarUrl();
        tvName.setText(sessionManager.getFullName());
        tvEmail.setText(sessionManager.getEmail());
        tvRole.setText("Vai trò: " + sessionManager.getRole());

        Glide.with(this)
                .load(imageUrl != null && !imageUrl.isEmpty() ? imageUrl : R.drawable.ic_user)
                .placeholder(R.drawable.ic_user)
                .error(R.drawable.ic_error)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        Log.e("GlideError", "Load failed", e);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        Log.d("GlideSuccess", "Image loaded successfully");
                        return false;
                    }
                })
                .into(imgAvatar);

        imgAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Chọn ảnh đại diện"), PICK_IMAGE_REQUEST);
        });

        btnDangXuat.setOnClickListener(v -> {
            String userCode = sessionManager.getUserCode();
            AuthRepository authRepository = new AuthRepository(getContext());

            authRepository.logout(userCode, new AuthRepository.LoginCallback<ApiResponse<Integer>>() {
                @Override
                public void onSuccess(ApiResponse<Integer> response) {
                    Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                    sessionManager.clear();
                    startActivity(new Intent(getContext(), activity_dang_nhap.class));
                    getActivity().finish();
                }

                @Override
                public void onError(String message) {
                    Toast.makeText(getContext(), "Đăng xuất thất bại: " + message, Toast.LENGTH_SHORT).show();
                }
            });
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();

            new AlertDialog.Builder(getContext())
                    .setTitle("Xác nhận cập nhật ảnh")
                    .setMessage("Bạn có muốn cập nhật ảnh đại diện mới không?")
                    .setPositiveButton("Cập nhật", (dialog, which) -> {
                        uploadAvatarToAWSAndUpdateProfile(selectedImageUri);
                    })
                    .setNegativeButton("Huỷ", (dialog, which) -> dialog.dismiss())
                    .show();
        }
    }

    private void uploadAvatarToAWSAndUpdateProfile(Uri uri) {
        AwsUploadUtil.upload(getContext(), uri, new ApiCallback<String>() {
            @Override
            public void onSuccess(String uploadedUrl) {
                updateAvatarInProfile(uploadedUrl);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(getContext(), "Lỗi upload ảnh: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAvatarInProfile(String avatarUrl) {
        ProfileRepository profileRepository = new ProfileRepository(getContext());
        UpdateProfileRequest request = new UpdateProfileRequest();
        request.setAvatar_url(avatarUrl);

        profileRepository.updateProfile(request, new ApiCallback<UProfile>() {
            @Override
            public void onSuccess(UProfile result) {
                Toast.makeText(getContext(), "Cập nhật ảnh thành công", Toast.LENGTH_SHORT).show();

                // Update ảnh trong UI
                Glide.with(Fragment_tai_khoan.this)
                        .load(avatarUrl)
                        .placeholder(R.drawable.ic_user)
                        .into(imgAvatar);

                // Lưu vào session
                new SessionManager(getContext()).saveAvatarUrl(avatarUrl);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), "Lỗi cập nhật avatar: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
