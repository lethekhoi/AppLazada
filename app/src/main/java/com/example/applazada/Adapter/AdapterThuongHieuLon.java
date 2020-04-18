package com.example.applazada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterThuongHieuLon extends RecyclerView.Adapter<AdapterThuongHieuLon.ViewHolder> {

    Context context;
    List<ThuongHieu> thuongHieus;

    public AdapterThuongHieuLon(Context context, List<ThuongHieu> thuongHieus) {
        this.context = context;
        this.thuongHieus = thuongHieus;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTieuDeThuongHieu;
        ImageView imgHinhThuongHieu;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTieuDeThuongHieu = itemView.findViewById(R.id.txtTieuDeThuongHieuLonDienTu);
            imgHinhThuongHieu = itemView.findViewById(R.id.imgHinhThuongHieuLonDienTu);
            progressBar = itemView.findViewById(R.id.progress_bar_download);
            linearLayout = itemView.findViewById(R.id.lnThuongHieuLon);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_layout_recycler_thuonghieulon, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ThuongHieu thuongHieu = thuongHieus.get(position);
        holder.txtTieuDeThuongHieu.setText(thuongHieu.getTENTHUONGHIEU());

        Picasso.with(context).load(thuongHieu.getHINHTHUONGHIEU()).resize(150, 150).into(holder.imgHinhThuongHieu, new Callback() {
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
        return thuongHieus.size();
    }


}
