package com.example.applazada.Presenter.TrangChu;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.applazada.View.TrangChu.TrangChuActivity;
import com.example.applazada.View.TrangChu.ViewXuLyMenu;
import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    String TenNguoiDung = "";
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu) {
        this.viewXuLyMenu = viewXuLyMenu;
    }

    @Override
    public void LayDanhSachMenu() {
        List<LoaiSanPham> loaiSanPhamList;
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //lấy bằng GET
//          String duongdan = "http://10.0.3.2/weblazada/loaisanpham.php?maloaicha=0";
//        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
        //end phương thức get
        //lấy bằng POST
        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", "0");

        attrs.add(hsHam);
        attrs.add(hsMaLoaiCha);



        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post

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

    @Override
    public AccessToken LayTenNguoiDungFacebook() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();


        return accessToken;
    }
}
