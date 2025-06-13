package com.example.fe_quanlyvattu.fragment;

import android.content.Intent;
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

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.activity.activity_dang_nhap;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.model.common.ApiResponse;
import com.example.fe_quanlyvattu.data.repository.AuthRepository;

public class Fragment_tai_khoan extends Fragment {
    private ImageView imgAvatar;
    private TextView tvName, tvEmail, tvRole;
    private TextView btnTaiKhoan, btnHoSo, btnDoiMatKhau, btnQuanLyBanQuyen, btnDangXuat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tai_khoan, container, false);
        imgAvatar = view.findViewById(R.id.imgAvatar);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvRole = view.findViewById(R.id.tvRole);

//        btnTaiKhoan = view.findViewById(R.id.btnTaiKhoan);
//        btnHoSo = view.findViewById(R.id.btnHoSo);
//        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);
//        btnQuanLyBanQuyen = view.findViewById(R.id.btnQuanLyBanQuyen);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);

        tvName.setText("Nguyễn Văn A");
        tvEmail.setText("admin@daihocx.edu.vn");
        tvRole.setText("Vai trò: Admin");

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ✅ Khởi tạo sessionManager tại đây
                SessionManager sessionManager = new SessionManager(getContext());
                String userCode = sessionManager.getUserCode(); // Lấy userCode từ session

                Log.d("LogoutDebug", "userCode: " + userCode);

                AuthRepository authRepository = new AuthRepository(getContext());
                authRepository.logout(userCode, new AuthRepository.LoginCallback<ApiResponse<Integer>>() {
                    @Override
                    public void onSuccess(ApiResponse<Integer> response) {
                        Toast.makeText(getContext(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();

                        // Xoá session & chuyển về màn hình đăng nhập
                        sessionManager.clear();  // hoặc sessionManager.clearSession() nếu bạn có hàm đó
                        Intent intent = new Intent(getContext(), activity_dang_nhap.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(), "Đăng xuất thất bại: " + message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



        return view;
    }

}

