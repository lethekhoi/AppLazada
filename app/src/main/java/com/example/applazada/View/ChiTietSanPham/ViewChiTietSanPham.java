package com.example.applazada.View.ChiTietSanPham;

import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewChiTietSanPham {
    void HienChiTietSanPham(SanPham sanPham);

    void HienSliderSanPham(String[] linkhinhsanpham);

    void HienThiDanhGia(List<DanhGia> danhGiaList);

    void ThemGioHangThanhCong();

    void ThemGioHangThatBai();
}
