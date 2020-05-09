package com.example.applazada.Presenter.GioHang;

import android.content.Context;

import com.example.applazada.Model.GioHang.ModelGioHang;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.View.GioHang.ViewGioHang;

import java.util.List;

public class PresenterLogicGioHang implements IPresenterGioHang {

    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang) {
        this.viewGioHang = viewGioHang;
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if (sanPhamList.size() > 0) {
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(sanPhamList) ;
        }
    }
}
