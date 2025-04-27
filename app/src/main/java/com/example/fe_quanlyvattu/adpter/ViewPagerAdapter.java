package com.example.fe_quanlyvattu.adpter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fe_quanlyvattu.activity.Fragment_de_xuat;
import com.example.fe_quanlyvattu.activity.Fragment_home;
import com.example.fe_quanlyvattu.activity.Fragment_kho;
import com.example.fe_quanlyvattu.activity.Fragment_tai_khoan;
import com.example.fe_quanlyvattu.activity.Fragment_vat_tu;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle) {
        super(fm, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Fragment_de_xuat();
            case 1:
                return new Fragment_vat_tu();
            case 2:
                return new Fragment_home();
            case 3:
                return new Fragment_kho();
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