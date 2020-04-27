package com.example.applazada.View.HienThiSanPhamTheoDanhMuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.applazada.R;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;
    RecyclerView.LayoutManager layoutManager;
    boolean danggrid = true;
    boolean kiemtra;
    int masp;
    Toolbar toolbar;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    PresenterLogicHienThiSanPhamTheoDanhMuc presenterLogicHienThiSanPhamTheoDanhMuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_san_pham_theo_danh_muc);

        recyclerView = findViewById(R.id.recyclerHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbarHienThiSP);

        Intent intent = getIntent();
        masp = intent.getIntExtra("MALOAI", 0);
        kiemtra = intent.getBooleanExtra("KIEMTRA", false);
        String tensanpham = intent.getStringExtra("TENLOAI");

        presenterLogicHienThiSanPhamTheoDanhMuc = new PresenterLogicHienThiSanPhamTheoDanhMuc(this);
        presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
        btnThayDoiTrangThaiRecycler.setOnClickListener(this);

        toolbar.setTitle(tensanpham);
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {


        if (danggrid) {
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList);

        } else {
            layoutManager = new LinearLayoutManager(this);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhamList);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        adapterTopDienThoaiDienTu.notifyDataSetChanged();

    }

    @Override
    public void LoiHienThiDanhSachSanPham() {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, kiemtra);
                break;

        }


    }
}
