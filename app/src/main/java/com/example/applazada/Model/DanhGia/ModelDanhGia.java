package com.example.applazada.Model.DanhGia;

import com.example.applazada.ConnectInternet.DownloadJSON;
import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ModelDanhGia {

    public boolean ThemDanhGia(DanhGia danhGia) {
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> attrs = new ArrayList<>();
        Boolean kiemtra = false;


        HashMap<String, String> hsHam = new HashMap<>();
        hsHam.put("ham", "ThemDanhGia");

        HashMap<String, String> hsMaDG = new HashMap<>();
        hsMaDG.put("madg", danhGia.getMADG());

        HashMap<String, String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp", String.valueOf(danhGia.getMASP()));

        HashMap<String, String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude", danhGia.getTIEUDE());

        HashMap<String, String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung", danhGia.getNOIDUNG());

        HashMap<String, String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSOSAO()));

        HashMap<String, String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi", String.valueOf(danhGia.getTENTHIETBI()));


        attrs.add(hsHam);
        attrs.add(hsMaDG);
        attrs.add(hsMaSP);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);
        attrs.add(hsTenThietBi);

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
