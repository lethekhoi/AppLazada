package com.example.applazada.Presenter.DanhGia;

import com.example.applazada.Model.DanhGia.ModelDanhGia;
import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.View.DanhGia.ViewDanhGia;

public class PresenterLogicDanhGia implements IPresenterDanhDia {

    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia(ViewDanhGia viewDanhGia) {
        this.viewDanhGia = viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra) {
            viewDanhGia.DanhGiaThanhCong();
        } else {
            viewDanhGia.DanhGiaThatBai();
        }
    }
}
