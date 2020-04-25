package com.example.applazada.View.TrangChu;

import com.example.applazada.Model.ObjectClass.DienTu;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ObjectClass.ThuongHieu;

import java.util.List;

public interface ViewDienTu {
    void HienThiDanhSach(List<DienTu> dienTus);

    void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus);

    void LoiLayDuLieu();

    void HienThiSanPhamMoiVe(List<SanPham> sanPhams);
}
