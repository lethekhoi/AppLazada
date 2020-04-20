package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.DienTu;
import com.example.applazada.R;

import java.util.List;

public class AdapterDienTu extends RecyclerView.Adapter<AdapterDienTu.ViewHolder> {
    Context context;
    List<DienTu> dienTuList;

    public AdapterDienTu(Context context, List<DienTu> dienTuList) {
        this.context = context;
        this.dienTuList = dienTuList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHinhGiamGia;
        RecyclerView recyclerViewThuongHieuLon, recyclerViewTopSanPham;
        TextView txtTieuDeSanPhamNoiBat, txtTopSanPhamNoiBat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhGiamGia = itemView.findViewById(R.id.imgKhuyenMaiDienTu);
            recyclerViewThuongHieuLon = itemView.findViewById(R.id.recyclerThuongHieuLon);
            recyclerViewTopSanPham = itemView.findViewById(R.id.recyclerTopDienThoaiMayTinhBang);
            txtTieuDeSanPhamNoiBat = itemView.findViewById(R.id.txtTenSanPhamNoiBat);
            txtTopSanPhamNoiBat = itemView.findViewById(R.id.TenTopSanPhamNoiBat);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recyclerview_dientu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DienTu dienTu = dienTuList.get(position);
        //xử lí hiển thị thương hieu lớn
        AdapterThuongHieuLon adapterThuongHieuLon = new AdapterThuongHieuLon(context, dienTu.getThuongHieus());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.HORIZONTAL, false);
        holder.recyclerViewThuongHieuLon.setLayoutManager(layoutManager);
        holder.recyclerViewThuongHieuLon.setAdapter(adapterThuongHieuLon);
        holder.txtTieuDeSanPhamNoiBat.setText(dienTu.getTennoibat().toString());
        adapterThuongHieuLon.notifyDataSetChanged();

        //xử lí hiển thị top sản phẩm
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(context, dienTu.getSanPhams());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.recyclerViewTopSanPham.setLayoutManager(layoutManager1);
        holder.txtTopSanPhamNoiBat.setText(dienTu.getTentopnoibat().toString());
        holder.recyclerViewTopSanPham.setAdapter(adapterTopDienThoaiDienTu);
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dienTuList.size();
    }

}
