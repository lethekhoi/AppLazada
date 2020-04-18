package com.example.applazada.Presenter.TrangChu_DienTu;

import android.view.View;

import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.Model.TrangChu_DienTu.ModelDienTu;
import com.example.applazada.View.TrangChu.ViewDienTu;

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
        List<ThuongHieu> thuongHieuList = modelDienTu.LayDanhSachThuongHieuLon();
        if (thuongHieuList.size() > 0) {
            viewDienTu.HienThiDanhSach(thuongHieuList);
        }
    }
}
