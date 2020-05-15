package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.KhuyenMai;
import com.example.applazada.R;

import java.util.List;

public class AdapterDanhSachKhuyenMai extends RecyclerView.Adapter<AdapterDanhSachKhuyenMai.ViewHolder> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterDanhSachKhuyenMai(Context context, List<KhuyenMai> khuyenMaiList) {
        this.context = context;
        this.khuyenMaiList = khuyenMaiList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewItemKhuyenmai;
        TextView txtTieuDeKhuyenMai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItemKhuyenmai = itemView.findViewById(R.id.recyclerItemKhuyenMai);
            txtTieuDeKhuyenMai = itemView.findViewById(R.id.txtTieuDeKhuyenMai);

        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_itemkhuyenmai, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        KhuyenMai khuyenMai = khuyenMaiList.get(position);
        holder.txtTieuDeKhuyenMai.setText(khuyenMai.getTENLOAISP());
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context, R.layout.custom_layout_topdienthoaivamaytinhbang,
                khuyenMai.getDanhSachSanPhamKhuyenMai());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        holder.recyclerViewItemKhuyenmai.setLayoutManager(layoutManager);
        holder.recyclerViewItemKhuyenmai.setAdapter(adapterTopDienThoaiDienTu);
    }

    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }


}
