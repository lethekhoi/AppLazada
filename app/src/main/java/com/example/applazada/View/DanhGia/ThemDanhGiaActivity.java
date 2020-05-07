package com.example.applazada.View.DanhGia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.Presenter.DanhGia.PresenterLogicDanhGia;
import com.example.applazada.R;
import com.google.android.material.textfield.TextInputLayout;

public class ThemDanhGiaActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener, ViewDanhGia, View.OnClickListener {
    private static int READ_PHONE_STATE_PERMISSION = 1;
    TelephonyManager telephonyManager;
    String imei;
    TextInputLayout input_edTieuDe, input_edNoiDung;
    EditText edTieuDe, edNoiDung;
    RatingBar rbDanhGia;
    int masp = 0;
    int sosao = 0;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_danh_gia);
        input_edTieuDe = findViewById(R.id.input_edTieuDeDanhGia);
        input_edNoiDung = findViewById(R.id.input_edNoiDungDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);

        masp = getIntent().getIntExtra("masp", 0);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);

        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this, "Bạn không thể đánh giá sản phẩm này", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String madg = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) ==
//                    PackageManager.PERMISSION_DENIED) {
//                String[] permissions = {Manifest.permission.READ_PHONE_STATE};
//                requestPermissions(permissions, READ_PHONE_STATE_PERMISSION);
//            } else {
//                madg = telephonyManager.getDeviceId();
//
//            }
//        } else {
//            madg = telephonyManager.getDeviceId();
//        }

        String tenthietbi = Build.MODEL;
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();
        if (tieude.trim().length() > 0) {
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        } else {
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập tiêu để");
        }
        if (noidung.trim().length() > 0) {
            input_edNoiDung.setErrorEnabled(false);
            input_edNoiDung.setError("");
        } else {
            input_edNoiDung.setErrorEnabled(true);
            input_edNoiDung.setError("Bạn chưa nhập nội dung");
        }

        if (!input_edNoiDung.isErrorEnabled() && !input_edTieuDe.isErrorEnabled()) {
            DanhGia danhGia = new DanhGia();
            danhGia.setMADG(madg);
            danhGia.setMASP(masp);
            danhGia.setNOIDUNG(noidung);
            danhGia.setSOSAO(sosao);
            danhGia.setTENTHIETBI(tenthietbi);
            danhGia.setTIEUDE(tieude);
            presenterLogicDanhGia.ThemDanhGia(danhGia);
            finish();
        }


    }


}