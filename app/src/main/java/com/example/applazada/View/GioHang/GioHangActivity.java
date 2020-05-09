package com.example.applazada.View.GioHang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.applazada.Adapter.AdapterGioHang;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.GioHang.PresenterLogicGioHang;
import com.example.applazada.R;

import java.util.List;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {
    RecyclerView recyclerView;
    AdapterGioHang adapterGioHang;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;


//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this, "kkkk", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        recyclerView = findViewById(R.id.recyclerGioHang);
        toolbar = findViewById(R.id.toolbarGioHang);
        toolbar.setTitle("Giỏ Hàng");
        setSupportActionBar(toolbar);

        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<SanPham> sanPhamList) {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapterGioHang = new AdapterGioHang(GioHangActivity.this, sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }
}
