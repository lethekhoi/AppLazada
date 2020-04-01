package com.example.applazada.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

import com.example.applazada.Adapter.ViewPagerAdapter;
import com.example.applazada.Model.TrangChu.DownloadDuLieu;
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
import java.util.concurrent.ExecutionException;

public class TrangChuActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    EditText editTextMaloaicha;
    Button btnlaydulieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        editTextMaloaicha = findViewById(R.id.edtMaLoaiCha);
        btnlaydulieu = findViewById(R.id.btnlaydulieu);

        viewPager = findViewById(R.id.viewpager);
        toolbar = findViewById(R.id.toolbarTrangChu);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


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
        return super.onOptionsItemSelected(item);
    }


}
