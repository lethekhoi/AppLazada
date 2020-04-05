package com.example.applazada.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.applazada.Adapter.ExpandAdapter;
import com.example.applazada.Adapter.ViewPagerAdapter;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Model.TrangChu.DownloadDuLieu;
import com.example.applazada.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.example.applazada.R;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    EditText editTextMaloaicha;
    Button btnlaydulieu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        drawerLayout = findViewById(R.id.drawerlayouttrangchu);
        editTextMaloaicha = findViewById(R.id.edtMaLoaiCha);
        btnlaydulieu = findViewById(R.id.btnlaydulieu);
        viewPager = findViewById(R.id.viewpager);
        expandableListView = findViewById(R.id.epMenu);


        toolbar = findViewById(R.id.toolbarTrangChu);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();


        tabLayout = findViewById(R.id.tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        PresenterLogicXuLyMenu logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();


        btnlaydulieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String maloaicha = editTextMaloaicha.getText().toString();
                //diachi genymotion 10.0.3.2
                //dia chi may ao android studio 127.0.0.1
                //đường dẫn dạng GET
                //String duongdan = "http://10.0.3.2/weblazada/loaisanpham.php?maloaicha=" + maloaicha;
                //đường dẫn dạng POST
                String duongdan = "http://10.0.3.2/weblazada/loaisanpham.php";
                DownloadDuLieu downloadDuLieu = new DownloadDuLieu();
                downloadDuLieu.execute(duongdan, maloaicha);
                try {
                    String dulieu = downloadDuLieu.get();
                    Toast.makeText(TrangChuActivity.this, dulieu, Toast.LENGTH_SHORT).show();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return true;
    }


    @Override
    public void HienThiDanhSachMenu(List<LoaiSanPham> loaiSanPhamList) {
        Log.d("kiemtra", loaiSanPhamList.get(0).getTENLOAISP());
        ExpandAdapter expandAdapter = new ExpandAdapter(this, loaiSanPhamList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }
}
