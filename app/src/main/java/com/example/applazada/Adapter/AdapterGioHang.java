package com.example.applazada.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {

    Context context;
    List<SanPham> sanPhamList;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenGioHang, txtGiaGioHang, txtGiamGiaGioHang;
        ImageView imgHinhGioHang, imgXoaSanPham;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtGiamGiaGioHang = itemView.findViewById(R.id.txtGiamGiaGioHang);
            imgHinhGioHang = itemView.findViewById(R.id.imgHinhHinhGioHang);
            imgXoaSanPham = itemView.findViewById(R.id.imgXoaSanPham);
            progressBar = itemView.findViewById(R.id.progress_bar_GioHang);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);
        holder.txtTenGioHang.setText(sanPham.getTENSP());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        holder.txtGiaGioHang.setText(gia + " VND");
        byte[] hinhsanpham = sanPham.getHinhgiohang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhsanpham, 0, hinhsanpham.length);
        holder.imgHinhGioHang.setImageBitmap(bitmapHinhGioHang);
        holder.progressBar.setVisibility(View.GONE);
        holder.imgXoaSanPham.setTag(sanPham.getMASP());
        holder.imgXoaSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
