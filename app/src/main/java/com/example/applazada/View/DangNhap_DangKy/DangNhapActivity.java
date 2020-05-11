package com.example.applazada.View.DangNhap_DangKy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.example.applazada.Adapter.ViewPagerAdapterDangNhap;
import com.example.applazada.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

public class DangNhapActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    TextInputLayout textInputLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
      //  textInputLayout = findViewById(R.id.input_edtPasswordDangKy);
        appBarLayout = findViewById(R.id.appbarDangNhap);
        tabLayout = findViewById(R.id.tablayoutDangNhap);
        viewPager = findViewById(R.id.viewpagerDangNhap);
        toolbar = findViewById(R.id.toolbarDangNhap);
        toolbar.setTitle("Đăng Nhập/Đăng xuất");
        toolbar.setTitleTextColor(Color.WHITE);
        appBarLayout.setExpanded(true, false);
        setSupportActionBar(toolbar);


        ViewPagerAdapterDangNhap viewPagerAdapterDangNhap = new ViewPagerAdapterDangNhap(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapterDangNhap);
        viewPagerAdapterDangNhap.notifyDataSetChanged();

        tabLayout.setupWithViewPager(viewPager);
    }
}
