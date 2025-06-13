package com.example.fe_quanlyvattu.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fe_quanlyvattu.fragment.FragmentQuanLyPhieu;
import com.example.fe_quanlyvattu.fragment.Fragment_phongban;
import com.example.fe_quanlyvattu.fragment.Fragment_home;
import com.example.fe_quanlyvattu.fragment.Fragment_tai_khoan;
import com.example.fe_quanlyvattu.fragment.Fragment_vat_tu;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_phongban();
            case 1:
                return new Fragment_vat_tu();
            case 2:
                return new Fragment_home();
            case 3:
                return new FragmentQuanLyPhieu();
            case 4:
                return new Fragment_tai_khoan();

            default:
                return new Fragment_home();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}