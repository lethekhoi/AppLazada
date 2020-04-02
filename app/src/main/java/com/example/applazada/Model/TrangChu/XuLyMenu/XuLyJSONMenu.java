package com.example.applazada.Model.TrangChu.XuLyMenu;

import com.example.applazada.Model.ObjectClass.LoaiSanPham;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XuLyJSONMenu {
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
}
