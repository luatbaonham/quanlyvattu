package com.example.fe_quanlyvattu.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fe_quanlyvattu.R;

import java.util.List;

public class BaoCaoAdapter extends RecyclerView.Adapter<BaoCaoAdapter.ViewHolder> {
    private List<String> dataList;

    public BaoCaoAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNoiDung;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNoiDung = itemView.findViewById(R.id.txt_bao_cao_noi_dung);
        }
    }

    @Override
    public BaoCaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bao_cao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaoCaoAdapter.ViewHolder holder, int position) {
        holder.txtNoiDung.setText(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

