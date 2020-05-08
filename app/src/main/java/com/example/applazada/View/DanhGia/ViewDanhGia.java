package com.example.applazada.View.DanhGia;

import com.example.applazada.Model.ObjectClass.DanhGia;

import java.util.List;

public interface ViewDanhGia {
    void DanhGiaThanhCong();

    void DanhGiaThatBai();

    void HienThiDanhSachDanhGiaTheoSanPham(List<DanhGia> danhGiaList);
}
