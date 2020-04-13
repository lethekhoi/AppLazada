package com.example.applazada.Presenter.DangKy;

import com.example.applazada.Model.DangNhap_DangKy.ModelDangKy;
import com.example.applazada.Model.ObjectClass.NhanVien;
import com.example.applazada.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy) {

        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }

    @Override
    public void ThucHienDangKy(NhanVien nhanvien) {
        Boolean kiemtra = modelDangKy.DangKyThanhVien(nhanvien);
        if (kiemtra) {
            viewDangKy.DangKyThanhCong();
        } else {
            viewDangKy.DangKyThatBai();
        }


    }
}
