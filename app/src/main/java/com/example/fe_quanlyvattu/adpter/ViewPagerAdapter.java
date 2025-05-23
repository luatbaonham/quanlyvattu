package com.example.fe_quanlyvattu.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fe_quanlyvattu.fragment.Fragment_duyet_capphat;
import com.example.fe_quanlyvattu.fragment.Fragment_home;
import com.example.fe_quanlyvattu.fragment.Fragment_phieunhap_thanhly;
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
                return new Fragment_duyet_capphat();
            case 1:
                return new Fragment_vat_tu();
            case 2:
                return new Fragment_home();
            case 3:
                return new Fragment_phieunhap_thanhly();
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