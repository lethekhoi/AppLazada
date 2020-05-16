package com.example.applazada.Presenter.TimKiem;

import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.TimKiem.ModelTimKiem;
import com.example.applazada.View.TimKiem.ViewTimKiem;

import java.util.List;

public class PresenterLogicTimKiem implements IPresenterTimKiem {


    ViewTimKiem viewTimKiem;
    ModelTimKiem modelTimKiem;

    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem) {
        this.viewTimKiem = viewTimKiem;
        modelTimKiem = new ModelTimKiem();
    }

    @Override
    public void TimKiemSanPhamThjeoTenSanPham(String tensp, int limit) {
        List<SanPham> sanPhamList = modelTimKiem.LayDanhSachSanPhamTheoMaLoai(tensp, "TimKiemSanPhamTheoTenSP", "DANHSACHSANPHAM", limit);
        if (sanPhamList.size() > 0) {
            viewTimKiem.TimKiemThanhCong(sanPhamList);
        } else {
            viewTimKiem.TimKiemThatBai();
        }
    }
}
