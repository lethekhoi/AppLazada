package com.example.applazada.Presenter.HienThiSanPhamTheoDanhMuc;

import com.example.applazada.Model.HienThiSanPhamTheoDanhMuc.ModelHienThiSanPhamTheoDanhMuc;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.View.HienThiSanPhamTheoDanhMuc.ViewHienThiSanPhamTheoDanhMuc;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicHienThiSanPhamTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {
    ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc;
    ModelHienThiSanPhamTheoDanhMuc modelHienThiSanPhamTheoDanhMuc;

    public PresenterLogicHienThiSanPhamTheoDanhMuc(ViewHienThiSanPhamTheoDanhMuc viewHienThiSanPhamTheoDanhMuc) {
        this.viewHienThiSanPhamTheoDanhMuc = viewHienThiSanPhamTheoDanhMuc;
        modelHienThiSanPhamTheoDanhMuc = new ModelHienThiSanPhamTheoDanhMuc();
    }


    @Override
    public void LayDanhSachSanPhamTheoMaLoai(int masp, boolean kiemtra) {
        List<SanPham> sanPhamList = new ArrayList<>();
        if (kiemtra) {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaThuongHieu", "DANHSACHSANPHAM", 20);
        } else {
            sanPhamList = modelHienThiSanPhamTheoDanhMuc.LayDanhSachSanPhamTheoMaLoai(masp, "LayDanhSachSanPhamTheoMaLoaiDanhMuc", "DANHSACHSANPHAM", 20);
        }

        if (sanPhamList.size() > 0) {
            viewHienThiSanPhamTheoDanhMuc.HienThiDanhSachSanPham(sanPhamList);
        } else {
            viewHienThiSanPhamTheoDanhMuc.LoiHienThiDanhSachSanPham();
        }


    }
}
