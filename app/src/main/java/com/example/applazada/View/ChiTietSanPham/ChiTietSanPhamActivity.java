package com.example.applazada.View.ChiTietSanPham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applazada.Adapter.AdapterDanhGia;
import com.example.applazada.Adapter.AdapterViewPagerSlider;
import com.example.applazada.Model.ObjectClass.ChiTietSanPham;
import com.example.applazada.Model.ObjectClass.DanhGia;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.ChiTietSanPham.FragmentSliderChiTietSanPham;
import com.example.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.applazada.R;
import com.example.applazada.View.DanhGia.DanhSachDanhGiaActivity;
import com.example.applazada.View.DanhGia.ThemDanhGiaActivity;
import com.example.applazada.View.GioHang.GioHangActivity;
import com.example.applazada.View.TrangChu.TrangChuActivity;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity implements ViewChiTietSanPham, ViewPager.OnPageChangeListener, View.OnClickListener {
    Toolbar toolbar;
    ViewPager viewPager;
    PresenterLogicChiTietSanPham presenterLogicChiTietSanPham;
    TextView[] txtDots;
    LinearLayout layoutDots, lnThongSoKyThuat;
    List<Fragment> fragmentList;
    TextView txtTenSanPham, txtGiaTien, txtTenCuaHangDongGoi, txtThongTinChiTiet, txtVietDanhGia, txtXemTatCaNhanXet, txtGioHang;
    ImageView imgXemThem, imgThemGioHang;
    Boolean kiemtraxochitiet = false;
    List<DanhGia> danhGiaList;
    RecyclerView recyclerView;
    int masp;
    SanPham sanPhamioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        masp = getIntent().getIntExtra("masp", 0);
        txtTenSanPham = findViewById(R.id.txtTenSanPham);
        txtThongTinChiTiet = findViewById(R.id.txtThongTinChiTiet);
        txtTenCuaHangDongGoi = findViewById(R.id.txtTenChDongGoi);
        txtGiaTien = findViewById(R.id.txtGiaTien);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        txtXemTatCaNhanXet = findViewById(R.id.txtXemTatCaNhanXet);
        lnThongSoKyThuat = findViewById(R.id.lnThongSoKyThuat);
        imgXemThem = findViewById(R.id.imgxemthemchitiet);
        imgThemGioHang = findViewById(R.id.imgThemGioHang);
        toolbar = findViewById(R.id.toolbarChiTietSP);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutdot);
        recyclerView = findViewById(R.id.recyclerDanhGia);

        presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham(this);
        presenterLogicChiTietSanPham.LayChiTietSanPham(masp);
        presenterLogicChiTietSanPham.LayDanhSachDanhGiaCuaSanPham(masp, 0);

        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imgThemGioHang.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        MenuItem itemGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGioHang = new Intent(ChiTietSanPhamActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });
        return true;
    }

    @Override
    public void HienChiTietSanPham(final SanPham sanPham) {
        masp = sanPham.getMASP();
        sanPhamioHang = sanPham;


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

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {

            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("masp", masp);
                startActivity(iThemDanhGia);
                break;
            case R.id.txtXemTatCaNhanXet:
                Intent iDanhSachDanhGia = new Intent(this, DanhSachDanhGiaActivity.class);
                iDanhSachDanhGia.putExtra("masp", masp);
                startActivity(iDanhSachDanhGia);
                break;

            case R.id.imgThemGioHang:
                Fragment fragment = fragmentList.get(0);
                View view1 = fragment.getView();
                ImageView imageView = view1.findViewById(R.id.imgHinhSlider);
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();
                sanPhamioHang.setHinhgiohang(hinhsanphamgiohang);

                presenterLogicChiTietSanPham.ThemGioHang(sanPhamioHang, this);
                break;
        }


    }

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this, danhGiaList, 3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDanhGia);

        adapterDanhGia.notifyDataSetChanged();

    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Sản phẩm đã được thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
    }
}
