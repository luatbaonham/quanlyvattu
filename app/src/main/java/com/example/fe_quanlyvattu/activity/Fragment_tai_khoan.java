package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fe_quanlyvattu.R;

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

        btnTaiKhoan = view.findViewById(R.id.btnTaiKhoan);
        btnHoSo = view.findViewById(R.id.btnHoSo);
        btnDoiMatKhau = view.findViewById(R.id.btnDoiMatKhau);
        btnQuanLyBanQuyen = view.findViewById(R.id.btnQuanLyBanQuyen);
        btnDangXuat = view.findViewById(R.id.btnDangXuat);

        imgAvatar.setImageResource(R.drawable.real);
        tvName.setText("Nguyễn Văn A");
        tvEmail.setText("admin@daihocx.edu.vn");
        tvRole.setText("Vai trò: Admin");
        btnHoSo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ví dụ chuyển đến màn hình hồ sơ (nếu có)
                // startActivity(new Intent(getContext(), HoSoActivity.class));
            }
        });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ví dụ chuyển đến màn hình đổi mật khẩu
                // startActivity(new Intent(getContext(), DoiMatKhauActivity.class));
            }
        });

        btnQuanLyBanQuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ví dụ mở màn hình bản quyền
                // startActivity(new Intent(getContext(), BanQuyenActivity.class));
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                  Xử lý đăng xuất (ví dụ: xóa session, chuyển về LoginActivity)
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                startActivity(intent);
//                getActivity().finish();
            }
        });

        return view;
    }

}
