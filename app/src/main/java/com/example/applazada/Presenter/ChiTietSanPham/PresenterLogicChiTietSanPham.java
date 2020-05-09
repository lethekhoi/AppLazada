package com.example.applazada.Presenter.ChiTietSanPham;

import android.content.Context;

import com.example.applazada.Model.ChiTietSanPham.ModelChiTietSanPham;
import com.example.applazada.Model.GioHang.ModelGioHang;
import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.View.ChiTietSanPham.ViewChiTietSanPham;

import java.util.List;

public class PresenterLogicChiTietSanPham implements IPresenterChiTietSanPham {

    ViewChiTietSanPham viewChiTietSanPham;
    ModelChiTietSanPham modelChiTietSanPham;
    ModelGioHang modelGioHang;


    public PresenterLogicChiTietSanPham() {
        modelGioHang = new ModelGioHang();
    }

    public PresenterLogicChiTietSanPham(ViewChiTietSanPham viewChiTietSanPham) {
        this.viewChiTietSanPham = viewChiTietSanPham;
        modelChiTietSanPham = new ModelChiTietSanPham();
        modelGioHang = new ModelGioHang();
    }

    @Override
    public void LayChiTietSanPham(int masp) {
        SanPham sanPham = modelChiTietSanPham.LayChiTietSanPhan("LaySanPhamVaChitietTheoMaSP", "CHITIETSANPHAM", masp);
        if (sanPham.getMASP() > 0) {
            String[] linkhinhanh = sanPham.getANHNHO().split(",");
            viewChiTietSanPham.HienSliderSanPham(linkhinhanh);
            viewChiTietSanPham.HienChiTietSanPham(sanPham);
        }
    }

    @Override
    public void LayDanhSachDanhGiaCuaSanPham(int masp, int limit) {
        List<DanhGia> danhGias = modelChiTietSanPham.LayDanhSachDanhCuaSanPham(masp, limit);
        if (danhGias.size() > 0) {
            viewChiTietSanPham.HienThiDanhGia(danhGias);
        }
    }

    @Override
    public void ThemGioHang(SanPham sanPham, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(sanPham);
        if (kiemtra) {
            viewChiTietSanPham.ThemGioHangThanhCong();
        } else {
            viewChiTietSanPham.ThemGioHangThatBai();
        }
    }


    public int DemSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<SanPham> sanPhamList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        int dem = sanPhamList.size();
        return dem;
    }

}
