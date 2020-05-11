package com.example.applazada.Presenter.ThanhToan;

import android.content.Context;

import com.example.applazada.Model.GioHang.ModelGioHang;
import com.example.applazada.Model.ObjectClass.HoaDon;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ThanhToan.ModelThanhToan;
import com.example.applazada.View.ThanhToan.ViewThanhToan;

import java.util.List;

public class PresenterLogicThanhToan implements IPresenterThanhToan {

    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if (kiemtra) {
            viewThanhToan.DatHangThanhCong();
        } else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhanTrongGioHang(sanPhamList);
    }
}
