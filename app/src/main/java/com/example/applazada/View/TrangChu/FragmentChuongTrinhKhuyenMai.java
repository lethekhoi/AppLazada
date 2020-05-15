package com.example.applazada.View.TrangChu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Adapter.AdapterDanhSachKhuyenMai;
import com.example.applazada.Model.ObjectClass.KhuyenMai;
import com.example.applazada.Presenter.KhuyenMai.PresenterLogicKhuyenMai;
import com.example.applazada.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FragmentChuongTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {
    View view;
    LinearLayout lnHinhKhuyenMai;
    RecyclerView recyclerViewDanhSachKhuyenMai;
    PresenterLogicKhuyenMai presenterLogicKhuyenMai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_chuongtrinhkhuyenmai, container, false);
        lnHinhKhuyenMai = view.findViewById(R.id.lnHinhKhuyenMai);
        recyclerViewDanhSachKhuyenMai = view.findViewById(R.id.recyclerDanhSachKhuyenMai);
        presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        return view;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {

        int demHinh = khuyenMaiList.size();
        if (demHinh > 5) {
            demHinh = 5;
        } else {
            demHinh = khuyenMaiList.size();
        }
        lnHinhKhuyenMai.removeAllViews();
        for (int i = 0; i < demHinh; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 225);
            layoutParams.setMargins(10, 10, 10, 10);
            imageView.setLayoutParams(layoutParams);
            Picasso.with(getContext()).load(khuyenMaiList.get(i).getHINHKHUYENMAI()).into(imageView);

            lnHinhKhuyenMai.addView(imageView);
        }


        AdapterDanhSachKhuyenMai adapterDanhSachKhuyenMai = new AdapterDanhSachKhuyenMai(getContext(), khuyenMaiList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerViewDanhSachKhuyenMai.setLayoutManager(layoutManager);
        recyclerViewDanhSachKhuyenMai.setAdapter(adapterDanhSachKhuyenMai);
        adapterDanhSachKhuyenMai.notifyDataSetChanged();
    }
}
