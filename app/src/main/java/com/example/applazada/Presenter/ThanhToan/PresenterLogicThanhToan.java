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
    List<SanPham> sanPhamList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context) {
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    @Override
    public void ThemHoaDon(HoaDon hoaDon) {
        boolean kiemtra = modelThanhToan.ThemHoaDon(hoaDon);
        if (kiemtra) {
            viewThanhToan.DatHangThanhCong();
            int dem = sanPhamList.size();
            for (int i = 0; i < dem; i++) {
                modelGioHang.XoaSanPhamTrongGioHang(sanPhamList.get(i).getMASP());
            }
        } else {
            viewThanhToan.DatHangThatBai();
        }
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {

        sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhanTrongGioHang(sanPhamList);
    }
}
