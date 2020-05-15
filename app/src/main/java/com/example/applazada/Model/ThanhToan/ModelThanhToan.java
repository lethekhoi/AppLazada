package com.example.applazada.Model.ThanhToan;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.ChiTietHoaDon;
import com.example.applazada.Model.ObjectClass.HoaDon;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelThanhToan {
    public boolean ThemHoaDon(HoaDon hoaDon) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        Boolean kiemtra = false;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemHoaDon");

        List<ChiTietHoaDon> chiTietHoaDonList = hoaDon.getChiTietHoaDonList();

        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i = 0; i < chiTietHoaDonList.size(); i++) {
            chuoijson += "{";
            chuoijson += "\"masp\" : " + chiTietHoaDonList.get(i).getMASP() + ",";
            chuoijson += "\"soluong\" : " + chiTietHoaDonList.get(i).getSOLUONG();

            if (i == chiTietHoaDonList.size() - 1) {
                chuoijson += "}";
            } else {
                chuoijson += "},";
            }

        }
        chuoijson += "]}";

        HashMap<String, String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham", chuoijson);

        HashMap<String, String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan", hoaDon.getTENNGUOINHAN());

        HashMap<String, String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt", String.valueOf(hoaDon.getSODT()));

        HashMap<String, String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", String.valueOf(hoaDon.getDIACHI()));

        HashMap<String, String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan", String.valueOf(hoaDon.getClass()));


        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsChuyenKhoan);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan, attrs);
        downloadJSON.execute();
        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            if (ketqua.equals("true")) {
                kiemtra = true;
            } else {
                kiemtra = false;
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }

}
