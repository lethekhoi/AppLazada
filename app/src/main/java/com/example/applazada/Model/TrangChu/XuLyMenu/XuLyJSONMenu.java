package com.example.applazada.Model.TrangChu.XuLyMenu;

import android.os.Bundle;
import android.util.Log;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.View.TrangChu.TrangChuActivity;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XuLyJSONMenu {
    String TenNguoiDung = "";

    public List<LoaiSanPham> ParserJSONMenu(String dulieujson) {
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaisanpham = jsonObject.getJSONArray("LOAISANPHAM");
            int count = loaisanpham.length();
            for (int i = 0; i < count; i++) {

                JSONObject value = loaisanpham.getJSONObject(i);
                LoaiSanPham dataloaiSanPham = new LoaiSanPham();
                dataloaiSanPham.setMALOAISP(Integer.parseInt(value.getString("MALOAISP")));
                dataloaiSanPham.setMALOAICHA(Integer.parseInt(value.getString("MALOAI_CHA")));
                dataloaiSanPham.setTENLOAISP(value.getString("TENLOAISP"));
                loaiSanPhamList.add(dataloaiSanPham);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return loaiSanPhamList;

    }

    public List<LoaiSanPham> LayLoaiSanPhamTheoMaLoai(int maloaisp) {
        String dataJSON = "";
        List<LoaiSanPham> loaiSanPhamList = new ArrayList<>();
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //lấy bằng POST
        String duongdan = TrangChuActivity.SERVER_NAME;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "LayDanhSachMenu");

        HashMap<String, String> hsMaLoaiCha = new HashMap<>();
        hsMaLoaiCha.put("maloaicha", String.valueOf(maloaisp));
        attrs.add(hsHam);
        attrs.add(hsMaLoaiCha);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post

        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiSanPhamList = xuLyJSONMenu.ParserJSONMenu(dataJSON);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loaiSanPhamList;

    }


}
