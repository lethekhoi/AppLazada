package com.example.applazada.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applazada.Adapter.AdapterViewPagerSlider;
import com.example.applazada.Model.ObjectClass.ChiTietSanPham;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.applazada.R;
import com.example.applazada.View.TrangChu.TrangChuActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener {
    Toolbar toolbar;
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots, lnThongSoKyThuat;
    List<Fragment> fragmentList;
    TextView txtTenSanPham, txtGiaTien, txtTenCuaHangDongGoi, txtThongTinChiTiet;
    ImageView imgXemThem;
    Boolean kiemtraxochitiet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        int masp = getIntent().getIntExtra("masp", 0);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        txtTenCuaHangDongGoi = findViewById(R.id.txtTenChDongGoi);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        imgXemThem = findViewById(R.id.imgxemthemchitiet);
        toolbar = findViewById(R.id.toolbarChiTietSP);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutdot);
        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public void HienChiTietSanPham(final SanPham sanPham) {
        txtTenSanPham.setText(sanPham.getTENSP());
        txtTenCuaHangDongGoi.setText(sanPham.getTENNV());
        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(sanPham.getGIA()).toString();
        txtGiaTien.setText(gia + " VND");
        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().subSequence(0, 100));
        if (sanPham.getTHONGTIN().length() < 100) {
            imgXemThem.setVisibility(View.GONE);
        } else {
            imgXemThem.setVisibility(View.VISIBLE);
            imgXemThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    kiemtraxochitiet = !kiemtraxochitiet;
                    if (kiemtraxochitiet) {
                        //sau khi mở chi tiết
                        imgXemThem.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN());
                        lnThongSoKyThuat.setVisibility(View.VISIBLE);
                        HienThiThongSoKyThuat(sanPham);

                    } else {
                        imgXemThem.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                        lnThongSoKyThuat.setVisibility(View.GONE);
                        txtThongTinChiTiet.setText(sanPham.getTHONGTIN().subSequence(0, 100));
                    }
                }
            });
        }


    }

    private void HienThiThongSoKyThuat(SanPham sanPham) {
        List<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPhamList();
        lnThongSoKyThuat.removeAllViews();


        TextView txtTieuDeThongSoLyThuat = new TextView(this);
        txtTieuDeThongSoLyThuat.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        txtTieuDeThongSoLyThuat.setText("Thông Số Kỹ Thuật");
        lnThongSoKyThuat.addView(txtTieuDeThongSoLyThuat);

        for (int i = 0; i < chiTietSanPhams.size(); i++) {
            LinearLayout lnChiTiet = new LinearLayout(this);
            lnChiTiet.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lnChiTiet.setOrientation(LinearLayout.HORIZONTAL);


            TextView txtTenThongSo = new TextView(this);
            txtTenThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtTenThongSo.setText(chiTietSanPhams.get(i).getTENCHITIET());

            TextView txtGiaTriThongSo = new TextView(this);
            txtGiaTriThongSo.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
            txtGiaTriThongSo.setText(chiTietSanPhams.get(i).getGIATRI());


            lnChiTiet.addView(txtTenThongSo);
            lnChiTiet.addView(txtGiaTriThongSo);

            lnThongSoKyThuat.addView(lnChiTiet);
        }
    }

    @Override
    public void HienSliderSanPham(String[] linkhinhsanpham) {
        fragmentList = new ArrayList<>();
        for (int i = 0; i < linkhinhsanpham.length; i++) {

            FragmentSliderChiTietSanPham fragmentSliderChiTietSanPham = new FragmentSliderChiTietSanPham();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh", TrangChuActivity.SERVER + linkhinhsanpham[i]);
            fragmentSliderChiTietSanPham.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietSanPham);
        }

        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();
        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);

    }

    private void ThemDotSlider(int vitrihientai) {
        txtDots = new TextView[fragmentList.size()];
        layoutDots.removeAllViews();
        for (int i = 0; i < fragmentList.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);

        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
