package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.R;

import java.util.List;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolder> {

    Context context;
    List<DanhGia> danhGiaList;
    int limit;

    public AdapterDanhGia(Context context, List<DanhGia> danhGiaList, int limit) {
        this.context = context;
        this.danhGiaList = danhGiaList;
        this.limit = limit;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDeDanhGia, txtNoiDungDanhGia, txtDuocDanhGiaBoi;
        RatingBar rbDanhGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDeDanhGia = itemView.findViewById(R.id.txtTieuDeDanhGia);
            txtNoiDungDanhGia = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtDuocDanhGiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vieư = layoutInflater.inflate(R.layout.custom_layout_recycler_danhgia_chitiet, parent, false);
        return new ViewHolder(vieư);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);
        holder.txtTieuDeDanhGia.setText(danhGia.getTIEUDE());
        holder.txtNoiDungDanhGia.setText(danhGia.getNOIDUNG());
        holder.txtDuocDanhGiaBoi.setText("Được đánh giá bởi " + danhGia.getTENTHIETBI() + " ngày " + danhGia.getNGAYDANHGIA());
        holder.rbDanhGia.setRating(danhGia.getSOSAO());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if (danhGiaList.size() < limit) {
            dong = danhGiaList.size();
        } else {
            if (limit != 0) {
                dong = limit;
            } else {
                dong = danhGiaList.size();
            }
        }
        return dong;
    }
}



