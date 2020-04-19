package com.example.applazada.Model.TrangChu_DienTu;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDienTu {

    public List<SanPham> LayDanhSachSanPhamTop() {

        String dataJSON = "";
        List<SanPham> sanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //lấy bằng POST
        String duongdan = TrangChuActivity.SERVER_NAME;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachTopDienThoaiVaMayTinhBang");


        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post

        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("TOPDIENTHOAI&MAYTINHBANG");
            int dem = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < dem; i++) {
                SanPham sanPham = new SanPham();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHLON(object.getString("HINHSANPHAM"));


                sanPhamList.add(sanPham);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPhamList;


    }


    public List<ThuongHieu> LayDanhSachThuongHieuLon() {
        String dataJSON = "";
        List<ThuongHieu> thuongHieuList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //lấy bằng POST
        String duongdan = TrangChuActivity.SERVER_NAME;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachCacThuongHieuLon");


        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post

        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThuongHieu = jsonObject.getJSONArray("DANHSACHTHUONGHIEU");
            int dem = jsonArrayDanhSachThuongHieu.length();
            for (int i = 0; i < dem; i++) {
                ThuongHieu thuongHieu = new ThuongHieu();
                JSONObject object = jsonArrayDanhSachThuongHieu.getJSONObject(i);
                thuongHieu.setMATHUONGHIEU(object.getInt("MASP"));
                thuongHieu.setTENTHUONGHIEU(object.getString("TENSP"));
                thuongHieu.setHINHTHUONGHIEU(object.getString("HINHSANPHAM"));


                thuongHieuList.add(thuongHieu);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return thuongHieuList;
    }
}
