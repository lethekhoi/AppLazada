package com.example.applazada.View.ThanhToan;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

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
    TextView txtNhanTienKhiGiaoHang, txtChuyenKhoan;
    List<ChiTietHoaDon> chiTietHoaDonList = new ArrayList<>();
    PresenterLogicThanhToan presenterLogicThanhToan;
    int chonHinhThuc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        toolbar = findViewById(R.id.toolbarThanhToan);
        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);
        edtTenNguoiNhan = findViewById(R.id.edtTenNguoiNhan);
        edtSoDT = findViewById(R.id.edtSoDT);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        imgThanhToanTrucTiep = findViewById(R.id.imgThanhToanTrucTiep);
        imgChuyenKhoan = findViewById(R.id.imgChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        cbCamKet = findViewById(R.id.cbCamKet);
        presenterLogicThanhToan = new PresenterLogicThanhToan(this, this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();
        btnThanhToan.setOnClickListener(this);
        imgChuyenKhoan.setOnClickListener(this);
        imgThanhToanTrucTiep.setOnClickListener(this);
        ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang, txtChuyenKhoan);
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
                        hoaDon.setCHUYENKHOAN(chonHinhThuc);
                        presenterLogicThanhToan.ThemHoaDon(hoaDon);

                    } else {
                        Toast.makeText(this, "Bạn chưa nhấn chọn cam kết", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.imgThanhToanTrucTiep:
                ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang, txtChuyenKhoan);
                chonHinhThuc = 0;
                break;

            case R.id.imgChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan, txtNhanTienKhiGiaoHang);
                chonHinhThuc = 0;
                break;
        }
    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon) {
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorBlack));
    }

    private int getIdColor(int idcolor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idcolor);
        } else {
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Thành công", Toast.LENGTH_SHORT).show();
        finish();
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
