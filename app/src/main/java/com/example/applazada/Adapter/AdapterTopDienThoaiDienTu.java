package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class AdapterTopDienThoaiDienTu extends RecyclerView.Adapter<AdapterTopDienThoaiDienTu.ViewHolder> {
    Context context;
    List<SanPham> sanPhamList;

    public AdapterTopDienThoaiDienTu(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        ImageView imgHinhSanPham;
        TextView txtTenSP, txtGiaTien, txtGiamGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_TopDienTu);
            imgHinhSanPham = itemView.findViewById(R.id.imgHinhDienThoaiMayTinhBang);
            txtTenSP = itemView.findViewById(R.id.txtTieuDeTopDienThoaiDienTu);
            txtGiaTien = itemView.findViewById(R.id.txtGiaDienTu);
            txtGiamGia = itemView.findViewById(R.id.txtGiamGiaDienTu);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_topdienthoaivamaytinhbang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.txtTenSP.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaTien.setText(gia + " VND");
        Picasso.with(context).load(sanPham.getANHLON()).resize(140, 140).into(holder.imgHinhSanPham, new Callback() {
            @Override
            public void onSuccess() {
                holder.progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}