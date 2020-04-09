package com.example.applazada.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
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
import com.example.applazada.Model.DangNhap.ModelDangNhap;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Model.TrangChu.DownloadDuLieu;
import com.example.applazada.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.example.applazada.R;
import com.example.applazada.View.DangNhap.DangNhapActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

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

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    EditText editTextMaloaicha;
    Button btnlaydulieu;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String TenNguoiDung = "";
    AccessToken accessToken;
    Menu menu;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap, menuItDangXuat;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
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

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        modelDangNhap = new ModelDangNhap();


        logicXuLyMenu.LayDanhSachMenu();
        logicXuLyMenu.LayTenNguoiDungFacebook();

        mGoogleApiClient = modelDangNhap.LayGoogleApiClient(this, this);

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
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        itemDangNhap = menu.findItem(R.id.itDangNhap);
        menuItDangXuat = menu.findItem(R.id.itDangXuat);
        accessToken = logicXuLyMenu.LayTenNguoiDungFacebook();
        googleSignInResult = modelDangNhap.LayThongTinDangNhapGoogle(mGoogleApiClient);

        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {

                        TenNguoiDung = object.getString("name");

                        itemDangNhap.setTitle(TenNguoiDung);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
            Bundle parameter = new Bundle();
            parameter.putString("fields", "name");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }


        if (googleSignInResult != null) {
            itemDangNhap.setTitle(googleSignInResult.getSignInAccount().getDisplayName());
        }


        if (accessToken != null || googleSignInResult != null) {

            menuItDangXuat.setVisible(true);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        int id = item.getItemId();
        switch (id) {

            case R.id.itDangNhap:
                if (accessToken == null && googleSignInResult == null) {
                    Intent itDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(itDangNhap);
                }
                ;
                break;
            case R.id.itDangXuat:
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                if (googleSignInResult != null) {
                    Auth.GoogleSignInApi.signOut(mGoogleApiClient);
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }


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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
