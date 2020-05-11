package com.example.applazada.View.ThanhToan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.applazada.Model.ObjectClass.ChiTietHoaDon;
import com.example.applazada.Model.ObjectClass.HoaDon;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.ThanhToan.PresenterLogicThanhToan;
import com.example.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class ThanhToanActivity extends AppCompatActivity implements View.OnClickListener, ViewThanhToan {
    Toolbar toolbar;
    EditText edtTenNguoiNhan, edtSoDT, edtDiaChi;
    ImageButton imgThanhToanTrucTiep, imgChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbCamKet;
    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
    PresenterLogicThanhToan presenterLogicThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = findViewById(R.id.toolbarThanhToan);
        edtTenNguoiNhan = findViewById(R.id.edtTenNguoiNhan);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        imgThanhToanTrucTiep = findViewById(R.id.imgThanhToanTrucTiep);
        imgChuyenKhoan = findViewById(R.id.imgChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        cbCamKet = findViewById(R.id.cbCamKet);
        presenterLogicThanhToan = new PresenterLogicThanhToan(this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang(this);
        btnThanhToan.setOnClickListener(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnThanhToan:
                String tennguoinhan = edtTenNguoiNhan.getText().toString();
                String diachi = edtDiaChi.getText().toString();
                String sodt = edtSoDT.getText().toString();
                if (tennguoinhan.trim().length() > 0 && diachi.trim().length() > 0 && sodt.trim().length() > 0) {

                    if (cbCamKet.isChecked()) {
                        HoaDon hoaDon = new HoaDon();
                        hoaDon.setTENNGUOINHAN(tennguoinhan);
                        hoaDon.setDIACHI(diachi);
                        hoaDon.setSODT(sodt);
                        hoaDon.setChiTietHoaDonList(chiTietHoaDonList);
                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                    } else {
                        Toast.makeText(this, "Bạn chưa nhấn chọn cam kết", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhanTrongGioHang(List<SanPham> sanPhamList) {

        for (int i = 0; i < sanPhamList.size(); i++) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setMASP(sanPhamList.get(i).getMASP());
            chiTietHoaDon.setSOLUONG(sanPhamList.get(i).getSOLUONG());
            chiTietHoaDonList.add(chiTietHoaDon);
        }
    }
}
