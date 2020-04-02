package com.example.applazada.Presenter.TrangChu;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.applazada.View.TrangChu.ViewXuLyMenu;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        String duongdan = "http://10.0.3.2/weblazada/loaisanpham.php?maloaicha=0";
        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiSanPhamList);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
