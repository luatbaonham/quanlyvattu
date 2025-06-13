//package com.example.fe_quanlyvattu.adpter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.fe_quanlyvattu.R;
//import com.example.fe_quanlyvattu.data.model.vattu.VatTu;
//
//import java.util.List;
//
//public class VatTuAdapter extends RecyclerView.Adapter<VatTuAdapter.ViewHolder> {
//    private List<VatTu> vatTuList;
//
//    public VatTuAdapter(List<VatTu> vatTuList) {
//        this.vatTuList = vatTuList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vat_tu, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        VatTu vatTu = vatTuList.get(position);
//        holder.tvTitle.setText(vatTu.getTitle());
//        holder.tvMavt.setText("Mã vật tư: " + vatTu.getMaVt());
//        holder.tvTenvt.setText("Tên vật tư: " + vatTu.getTenVt());
//        holder.tvloai.setText("Loại: " + vatTu.getLoai());
//        holder.tvslton.setText("Số lượng tồn: " + vatTu.getSlton());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return vatTuList.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle,tvMavt, tvTenvt, tvloai, tvslton;
//        Button btnDetails;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvTitle= itemView.findViewById(R.id.tv_tieude);
//            tvMavt = itemView.findViewById(R.id.tv_mavattu);
//            tvTenvt = itemView.findViewById(R.id.tv_tenvattu);
//            tvloai= itemView.findViewById(R.id.tv_loai);
//            tvslton = itemView.findViewById(R.id.tv_slton);
//        }
//    }
//}