package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLonDienTu extends RecyclerView.Adapter<AdapterThuongHieuLonDienTu.ViewHolder> {
    Context context;
    List<ThuongHieu> thuongHieuList;

    public AdapterThuongHieuLonDienTu(Context context, List<ThuongHieu> thuongHieuList) {
        this.context = context;
        this.thuongHieuList = thuongHieuList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgLogoThuongHieuLon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgLogoThuongHieuLon = itemView.findViewById(R.id.imgLogoTopThuongHieuLon);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_topthuonghieulon_dientu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ThuongHieu thuongHieu = thuongHieuList.get(position);
        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(120, 60).centerInside().into(holder.imgLogoThuongHieuLon);
    }

    @Override
    public int getItemCount() {
        return thuongHieuList.size();
    }


}
