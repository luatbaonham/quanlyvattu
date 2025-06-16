package com.example.fe_quanlyvattu.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.BaoCaoAdapter;
import com.example.fe_quanlyvattu.auth.SessionManager;
import com.example.fe_quanlyvattu.data.model.home_baocao.ReportItem;
import com.example.fe_quanlyvattu.data.repository.BaoCaoRepository;
import com.example.fe_quanlyvattu.data.api.ApiCallback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_home extends Fragment {

    private SwitchCompat switchMode;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView txtViewUsername;
    private RecyclerView recyclerViewBaoCao;
    private BaoCaoAdapter baoCaoAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 1. Ánh xạ Switch dark mode
        switchMode = view.findViewById(R.id.switchMode);
        sharedPreferences = requireActivity().getSharedPreferences("night_mode_prefs", Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean("nightMode", false);

        switchMode.setChecked(nightMode);
        AppCompatDelegate.setDefaultNightMode(
                nightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );

        switchMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor = sharedPreferences.edit();
            editor.putBoolean("nightMode", isChecked);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
            );
            requireActivity().recreate(); // Bắt buộc để cập nhật theme
        });

        // 2. Chào buổi sáng / chiều / tối
        TextView tvGreeting = view.findViewById(R.id.tvgreeting);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        if (hour >= 5 && hour < 12) {
            greeting = "Buổi sáng vui vẻ ☀️";
        } else if (hour >= 12 && hour < 18) {
            greeting = "Buổi chiều vui vẻ 🌤️";
        } else {
            greeting = "Buổi tối vui vẻ 🌙";
        }

        tvGreeting.setText(greeting);
        tvGreeting.setAlpha(0f);
        tvGreeting.animate().alpha(1f).setDuration(1000);

        // 3. Hiển thị tên người dùng
        txtViewUsername = view.findViewById(R.id.txtusername);
        SessionManager sessionManager = new SessionManager(getContext());
        String userName = sessionManager.getUsername();
        txtViewUsername.setText(userName);

        // 4. RecyclerView báo cáo
        recyclerViewBaoCao = view.findViewById(R.id.recyclerviewbaocao);
        recyclerViewBaoCao.setLayoutManager(new LinearLayoutManager(getContext()));
        baoCaoAdapter = new BaoCaoAdapter(new ArrayList<>());
        recyclerViewBaoCao.setAdapter(baoCaoAdapter);

        // 5. Gọi API để lấy dữ liệu báo cáo
        Context context = requireContext(); // đảm bảo không null
        BaoCaoRepository repository = new BaoCaoRepository(context);
        repository.getBaoCao(new ApiCallback<List<ReportItem>>() {
            @Override
            public void onSuccess(List<ReportItem> result) {
                baoCaoAdapter = new BaoCaoAdapter(result);
                recyclerViewBaoCao.setAdapter(baoCaoAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                if (getContext() != null) {
                    Toast.makeText(getContext(), "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
