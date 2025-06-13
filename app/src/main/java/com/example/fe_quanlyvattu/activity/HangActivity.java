package com.example.fe_quanlyvattu.activity;

import static com.example.fe_quanlyvattu.R.layout.activity_hangvt;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;
import com.example.fe_quanlyvattu.adpter.HangVtAdapter;
import com.example.fe_quanlyvattu.data.api.ApiCallback;
import com.example.fe_quanlyvattu.data.model.vattu.hang.HangVt;
import com.example.fe_quanlyvattu.data.repository.HangRepository;

import java.util.ArrayList;
import java.util.List;

public class HangActivity extends AppCompatActivity {
    private RecyclerView rvHangVt;
    private HangVtAdapter adapter;
    private List<HangVt> hangVtList = new ArrayList<>(); // ✅ Khởi tạo để tránh null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_hangvt);

        rvHangVt = findViewById(R.id.recyclerViewHangvt);
        rvHangVt.setLayoutManager(new LinearLayoutManager(this));

        adapter = new HangVtAdapter(hangVtList);
        rvHangVt.setAdapter(adapter);

        getHangVt(); // Load dữ liệu từ server
    }

    private void getHangVt() {
        HangRepository repository = new HangRepository(this);

        repository.getAllHang(new ApiCallback<List<HangVt>>() {
            @Override
            public void onSuccess(List<HangVt> response) {
                hangVtList.clear();
                hangVtList.addAll(response);
                adapter.setHangVtList(hangVtList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(HangActivity.this, "Lỗi: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
