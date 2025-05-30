package com.example.fe_quanlyvattu.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;

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
                case R.id.nav_quan_ly_phieu_nhap:
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
    }
}