package com.example.applazada.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Model.GioHang.ModelGioHang;
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
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<SanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenGioHang, txtGiaGioHang, txtGiamGiaGioHang, txtSoLuongSP;
        ImageView imgHinhGioHang, imgXoaSanPham;
        ImageButton imgTangSoLuongSP, imgGiamSoLuongSP;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtGiamGiaGioHang = itemView.findViewById(R.id.txtGiamGiaGioHang);
            imgHinhGioHang = itemView.findViewById(R.id.imgHinhHinhGioHang);
            imgXoaSanPham = itemView.findViewById(R.id.imgXoaSanPham);
            progressBar = itemView.findViewById(R.id.progress_bar_GioHang);
            txtSoLuongSP = itemView.findViewById(R.id.txtSoLuongSanPham);
            imgTangSoLuongSP = itemView.findViewById(R.id.imgTangSoLuongSP);
            imgGiamSoLuongSP = itemView.findViewById(R.id.imgGiamSoLuongSP);
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SanPham sanPham = sanPhamList.get(position);
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
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int) view.getTag());
                sanPhamList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSP.setText(String.valueOf(sanPham.getSOLUONG()));

        holder.imgTangSoLuongSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int soluong = Integer.parseInt(holder.txtSoLuongSP.getText().toString());
                int soluongtonkho = sanPham.getSOLUONGTONKHO();

                if (soluong < soluongtonkho) {
                    soluong++;
                } else {
                    Toast.makeText(context, "Số lượng bạn mua quá số lượng có trong cửa hàng", Toast.LENGTH_SHORT).show();
                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSP.setText(String.valueOf(soluong));

            }
        });
        holder.imgGiamSoLuongSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = Integer.parseInt(holder.txtSoLuongSP.getText().toString());
                if (soluong > 1) {
                    soluong--;

                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(), soluong);
                holder.txtSoLuongSP.setText(String.valueOf(soluong));


            }
        });


    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }


}
