package com.example.fe_quanlyvattu.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.ViewPagerAdapter;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.profile.GProfile;
import com.example.fe_quanlyvattu.data.model.vattu.kieu.Kieu;
import com.example.fe_quanlyvattu.data.repository.KieuRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.fe_quanlyvattu.data.repository.ProfileRepository;
import com.example.fe_quanlyvattu.activity.ActivityCreateProfile;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;
    private SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        viewPager.setUserInputEnabled(false);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.nav_quan_ly_phieu:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.nav_quan_ly_tai_khoan:
                    viewPager.setCurrentItem(4);
                    return true;
                case R.id.nav_quan_ly_phieu_thanh_ly:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_quan_ly_vat_tu:
                    viewPager.setCurrentItem(1);


            }
            return false;
        });
        viewPager.setCurrentItem(2, false);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected( int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });
        // Khởi tạo repository
        ProfileRepository profileRepository = new ProfileRepository(this);

// Gọi API kiểm tra hồ sơ
        sessionManager = new SessionManager(MainActivity.this);

        // Khởi tạo SessionManager và ProfileRepository

        profileRepository.checkProfileExisted(new ApiCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean hasProfile) {
                if (!hasProfile) {
                    // Nếu chưa có hồ sơ, chuyển sang màn tạo hồ sơ
                    Intent intent = new Intent(MainActivity.this, ActivityCreateProfile.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Nếu đã có hồ sơ, lấy thông tin hồ sơ
                    profileRepository.getProfile(new ApiCallback<GProfile>() {
                        @Override
                        public void onSuccess(GProfile gProfile) {
                            Log.d("GPROFILE_LOG", gProfile.toString());

                            String avatarUrl = gProfile.getAvatar_url();
                            if (avatarUrl != null) {
                                sessionManager.saveAvatarUrl(avatarUrl);
                            }

                            sessionManager.saveFirstName(gProfile.getFirst_name());
                            sessionManager.saveLastName(gProfile.getLast_name());

                            // 👉 Lưu phone & address
                            sessionManager.savePhone(gProfile.getPhone_number());
                            sessionManager.saveAddress(gProfile.getAddress());

                            if (gProfile.getAccount() != null) {
                                sessionManager.saveEmail(gProfile.getAccount().getEmail());
                                sessionManager.saveRole(gProfile.getAccount().getRoleName());
                            }
                        }



                        @Override
                        public void onError(String errorMessage) {
                            Toast.makeText(MainActivity.this, "Không thể lấy hồ sơ: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, "Lỗi kiểm tra hồ sơ: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


    }
}