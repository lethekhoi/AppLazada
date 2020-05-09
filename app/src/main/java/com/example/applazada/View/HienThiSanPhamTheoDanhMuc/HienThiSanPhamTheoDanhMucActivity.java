package com.example.applazada.View.HienThiSanPhamTheoDanhMuc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.applazada.Model.ObjectClass.ILoadMore;
import com.example.applazada.Model.ObjectClass.LoadMoreScroll;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.applazada.Presenter.HienThiSanPhamTheoDanhMuc.PresenterLogicHienThiSanPhamTheoDanhMuc;
import com.example.applazada.R;
import com.example.applazada.View.GioHang.GioHangActivity;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import java.util.List;

public class HienThiSanPhamTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiSanPhamTheoDanhMuc, View.OnClickListener, ILoadMore {
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;
    RecyclerView.LayoutManager layoutManager;
    boolean danggrid = true;
    boolean kiemtra;
    boolean onPause = false;
    int masp;
    TextView txtGioHang;
    ProgressBar progressBar;
    Toolbar toolbar;
    AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu;
    PresenterLogicHienThiSanPhamTheoDanhMuc presenterLogicHienThiSanPhamTheoDanhMuc;
    List<SanPham> sanPhamList1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_san_pham_theo_danh_muc);

        recyclerView = findViewById(R.id.recyclerHienThiSanPhamTheoDanhMuc);
        btnThayDoiTrangThaiRecycler = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbarHienThiSP);
        progressBar = findViewById(R.id.progress_bar);

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
        MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGioHang = new Intent(HienThiSanPhamTheoDanhMucActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

    @Override
    public void HienThiDanhSachSanPham(List<SanPham> sanPhamList) {
        sanPhamList1 = sanPhamList;

        if (danggrid) {
            layoutManager = new GridLayoutManager(HienThiSanPhamTheoDanhMucActivity.this, 2);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_topdienthoaivamaytinhbang, sanPhamList1);

        } else {
            layoutManager = new LinearLayoutManager(this);
            adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(HienThiSanPhamTheoDanhMucActivity.this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhamList1);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTopDienThoaiDienTu);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
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

    @Override
    public void LoadMore(int tongitem) {
        List<SanPham> listsanPhamsLoadMore = presenterLogicHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoaiLoadMore(masp, kiemtra, tongitem, progressBar);
        sanPhamList1.addAll(listsanPhamsLoadMore);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapterTopDienThoaiDienTu.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        if (onPause) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }
}
