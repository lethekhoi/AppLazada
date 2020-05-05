package com.example.applazada.Model.ChiTietSanPham;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.ChiTietSanPham;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelChiTietSanPham {


    public SanPham LayChiTietSanPhan(String tenham, String tenmang, int masp) {

        SanPham sanPham = new SanPham();
        List<ChiTietSanPham> chiTietSanPhams = new ArrayList<>();
        String dataJSON = "";
        List<HashMap<String, String>> attrs = new ArrayList<>();
        //lấy bằng POST
        String duongdan = TrangChuActivity.SERVER_NAME;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", tenham);

        HashMap<String, String> hsMasp = new HashMap<>();
        hsMasp.put("masp", String.valueOf(masp));

        attrs.add(hsHam);
        attrs.add(hsMasp);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        //end phương thức post

        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray(tenmang);
            int dem = jsonArrayDanhSachSanPham.length();
            for (int i = 0; i < dem; i++) {

                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);
                sanPham.setMASP(object.getInt("MASP"));
                sanPham.setTENSP(object.getString("TENSP"));
                sanPham.setGIA(object.getInt("GIATIEN"));
                sanPham.setANHNHO(object.getString("ANHNHO"));
                sanPham.setSOLUONG(object.getInt("SOLUONG"));
                sanPham.setTHONGTIN(object.getString("THONGTIN"));
                sanPham.setMALOAISP(object.getInt("MALOAISP"));
                sanPham.setMATHUONGHIEU(object.getInt("MATHUONGHIEU"));
                sanPham.setMANV(object.getInt("MANV"));
                sanPham.setTENNV(object.getString("TENNV"));
                sanPham.setLUOTMUA(object.getInt("LUOTMUA"));

                JSONArray jsonArrayThongSoKyThuat = object.getJSONArray("THONGSOKYTHUAT");
                for (int j = 0; j < jsonArrayThongSoKyThuat.length(); j++) {
                    JSONObject jsonObject1 = jsonArrayThongSoKyThuat.getJSONObject(j);

                    for (int k = 0; k < jsonObject1.names().length(); k++) {
                        String tenchitiet = jsonObject1.names().getString(k);
                        ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                        chiTietSanPham.setTENCHITIET(tenchitiet);
                        chiTietSanPham.setGIATRI(jsonObject1.getString(tenchitiet));

                        chiTietSanPhams.add(chiTietSanPham);
                    }
                }

                sanPham.setChiTietSanPhamList(chiTietSanPhams);
            }


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return sanPham;


    }


}
