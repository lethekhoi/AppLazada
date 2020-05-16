package com.example.applazada.View.TimKiem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.applazada.Model.ObjectClass.ILoadMore;
import com.example.applazada.Model.ObjectClass.LoadMoreScroll;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Presenter.TimKiem.PresenterLogicTimKiem;
import com.example.applazada.R;

import java.util.List;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, ILoadMore, SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerTimKiem);
        toolbar.setTitle(" ");
        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem, menu);
        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(itSearch);
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void TimKiemThanhCong(List<SanPham> sanPhamList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTopDienThoaiDienTu adapter = new AdapterTopDienThoaiDienTu(this, R.layout.custom_layout_list_topdienthoaivamaytinhbang, sanPhamList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new LoadMoreScroll(layoutManager, this));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {

    }

    @Override
    public void LoadMore(int tongitem) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        presenterLogicTimKiem.TimKiemSanPhamThjeoTenSanPham(s, 0);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}
