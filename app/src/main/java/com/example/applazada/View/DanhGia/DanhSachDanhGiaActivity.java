package com.example.applazada.View.DanhGia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.applazada.Adapter.AdapterDanhGia;
import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.Model.ObjectClass.ILoadMore;
import com.example.applazada.Model.ObjectClass.LoadMoreScroll;
import com.example.applazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia, ILoadMore {
    RecyclerView recyclerViewDanhSachDanhGia;
    ProgressBar progressBar;
    int masp = 0;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> tatcaDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_danh_gia);
        recyclerViewDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = findViewById(R.id.progress_bar);
        masp = getIntent().getIntExtra("masp", 0);
        tatcaDanhGia = new ArrayList<>();
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp, 0, progressBar);


    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList) {

        tatcaDanhGia.addAll(danhGiaList);

        final AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, tatcaDanhGia, 0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerViewDanhSachDanhGia.setAdapter(adapterDanhGia);
        recyclerViewDanhSachDanhGia.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapterDanhGia.notifyDataSetChanged();



    }

    @Override
    public void LoadMore(int tongitem) {
        presenterLogicDanhGia.LayDanhSachDanhGiaCuaSanPham(masp, tongitem, progressBar);
    }
}
