package com.example.applazada.View.TrangChu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applazada.Adapter.ExpandAdapter;
import com.example.applazada.Adapter.ViewPagerAdapter;
import com.example.applazada.Model.DangNhap_DangKy.ModelDangNhap;
import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Presenter.ChiTietSanPham.PresenterLogicChiTietSanPham;
import com.example.applazada.Presenter.TrangChu.PresenterLogicXuLyMenu;
import com.example.applazada.R;
import com.example.applazada.View.DangNhap_DangKy.DangNhapActivity;
import com.example.applazada.View.GioHang.GioHangActivity;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu, GoogleApiClient.OnConnectionFailedListener, AppBarLayout.OnOffsetChangedListener {
    public static final String SERVER_NAME = "http://10.0.3.2//weblazada/loaisanpham.php";
    public static final String SERVER = "http://10.0.3.2//weblazada";
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String TenNguoiDung = "";
    AccessToken accessToken;
    Menu menu;
    TextView txtGioHang;
    ModelDangNhap modelDangNhap;
    MenuItem itemDangNhap, menuItDangXuat;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInResult googleSignInResult;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_trang_chu);

        drawerLayout = findViewById(R.id.drawerlayouttrangchu);

        viewPager = findViewById(R.id.viewpager);
        expandableListView = findViewById(R.id.epMenu);

        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        appBarLayout = findViewById(R.id.appbar);
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
        appBarLayout.addOnOffsetChangedListener(this);


    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu, menu);
        this.menu = menu;
        MenuItem itemGioHang = this.menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(itemGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

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

        String tennv = modelDangNhap.LayCachedDangNhap(this);
        if (!tennv.equals("")) {
            itemDangNhap.setTitle(tennv);

        }


        if (accessToken != null || googleSignInResult != null || !tennv.equals("")) {

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
                if (accessToken == null && googleSignInResult == null && modelDangNhap.LayCachedDangNhap(this).equals("")) {
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

                if (!modelDangNhap.LayCachedDangNhap(this).equals("")) {
                    modelDangNhap.CapNhatCachedDangNhap(this, "");
                    this.menu.clear();
                    this.onCreateOptionsMenu(menu);
                }
                break;


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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        if (collapsingToolbarLayout.getHeight() + verticalOffset <= (1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(0).setDuration(200);

        } else {
            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
            linearLayout.animate().alpha(1).setDuration(200);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (onPause) {
            PresenterLogicChiTietSanPham presenterLogicChiTietSanPham = new PresenterLogicChiTietSanPham();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietSanPham.DemSanPhamTrongGioHang(this)));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        onPause = true;
    }

}
