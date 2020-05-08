package com.example.applazada.Presenter.DanhGia;

import android.widget.ProgressBar;

import com.example.applazada.Model.ObjectClass.DanhGia;

public interface IPresenterDanhDia {
    void ThemDanhGia(DanhGia danhGia);

    void LayDanhSachDanhGiaCuaSanPham(int masp, int limit, ProgressBar progressBar);
}
