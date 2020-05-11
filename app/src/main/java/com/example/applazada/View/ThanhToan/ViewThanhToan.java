package com.example.applazada.View.ThanhToan;

import com.example.applazada.Model.ObjectClass.SanPham;

import java.util.List;

public interface ViewThanhToan {
    void DatHangThanhCong();

    void DatHangThatBai();

    void LayDanhSachSanPhanTrongGioHang(List<SanPham> sanPhamList);
}
