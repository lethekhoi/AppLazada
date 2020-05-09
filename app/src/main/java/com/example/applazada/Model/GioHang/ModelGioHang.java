package com.example.applazada.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.applazada.Model.ObjectClass.SanPham;

import java.util.ArrayList;
import java.util.List;

public class ModelGioHang {

    SQLiteDatabase database;

    public void MoKetNoiSQL(Context context) {
        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();

    }

    public boolean ThemGioHang(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP, sanPham.getMASP());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP, sanPham.getTENSP());
        contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN, sanPham.getGIA());
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH, sanPham.getHinhgiohang());

        long id = database.insert(DataSanPham.TB_GIOHANG, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }

    }

    public List<SanPham> LayDanhSachSanPhamTrongGioHang() {
        List<SanPham> sanPhamList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));


            SanPham sanPham = new SanPham();
            sanPham.setMASP(masp);
            sanPham.setTENSP(tensp);
            sanPham.setGIA(giatien);
            sanPham.setHinhgiohang(hinhanh);


            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }


        return sanPhamList;
    }


}
