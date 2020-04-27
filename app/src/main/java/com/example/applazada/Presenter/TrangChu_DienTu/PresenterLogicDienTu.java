package com.example.applazada.Presenter.TrangChu_DienTu;

import android.view.View;

import com.example.applazada.Model.ObjectClass.DienTu;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.Model.TrangChu_DienTu.ModelDienTu;
import com.example.applazada.View.TrangChu.ViewDienTu;

import java.util.ArrayList;
import java.util.List;

public class PresenterLogicDienTu implements IPresenterDienTu {

    ViewDienTu viewDienTu;
    ModelDienTu modelDienTu;

    public PresenterLogicDienTu(ViewDienTu viewDienTu) {
        this.viewDienTu = viewDienTu;
        modelDienTu = new ModelDienTu();
    }


    @Override
    public void LayDanhSachDienTu() {
        List<DienTu> dienTuList = new ArrayList<>();

        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachCacThuongHieuLon", "DANHSACHTHUONGHIEU");
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopDienThoaiVaMayTinhBang", "TOPDIENTHOAI&MAYTINHBANG");


        DienTu dienTu = new DienTu();
        dienTu.setThuongHieus(thuongHieuList);
        dienTu.setSanPhams(sanPhamList);
        dienTu.setTennoibat("Thương hiệu lớn");
        dienTu.setTentopnoibat("Top điện thoại và máy tính bảng");
        dienTu.setThuonghieu(true);
        dienTuList.add(dienTu);

        List<ThuongHieu> topphukienList = modelDienTu.LayDanhSachThuongHieuLon("LayDanhSachPhuKien", "DANHSACHPHUKIEN");
        List<SanPham> phukienList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTopPhuKien", "TOPPHUKIEN");


        DienTu dienTu1 = new DienTu();
        dienTu1.setThuongHieus(topphukienList);
        dienTu1.setSanPhams(phukienList);
        dienTu1.setTennoibat("Phụ Kiện");
        dienTu1.setTentopnoibat("Top Phụ kiện");
        dienTu1.setThuonghieu(false);
        dienTuList.add(dienTu1);


        List<ThuongHieu> toptienichList = modelDienTu.LayDanhSachThuongHieuLon("LayTopTienIch", "TOPTIENICH");
        List<SanPham> tienichList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachTienIch", "DANHSACHTIENICH");


        DienTu dienTu2 = new DienTu();
        dienTu2.setThuongHieus(toptienichList);
        dienTu2.setSanPhams(tienichList);
        dienTu2.setTennoibat("Tiện ích");
        dienTu2.setTentopnoibat("Top Video & Tivi");
        dienTu2.setThuonghieu(false);
        dienTuList.add(dienTu2);


        if (thuongHieuList.size() > 0 && sanPhamList.size() > 0) {
            viewDienTu.HienThiDanhSach(dienTuList);
        }
    }

    @Override
    public void LayDanhSachLogoThuongHieu() {
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon("LayLogoThuongHieuLon", "DANHSACHTHUONGHIEU");
        if (thuongHieuList.size() > 0) {
            viewDienTu.HienThiLogoThuongHieu(thuongHieuList);
        } else {
            viewDienTu.LoiLayDuLieu();
        }
    }

    @Override
    public void LayDanhSachSanPhamMoi() {
        List<SanPham> sanPhamList = modelDienTu.LayDanhSachSanPhamTop("LayDanhSachSanPhamMoi", "DANHSACHSANPHAMMOIVE");
        if (sanPhamList.size() > 0) {
            viewDienTu.HienThiSanPhamMoiVe(sanPhamList);
        } else {
            viewDienTu.LoiLayDuLieu();
        }
    }
}
