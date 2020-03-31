package com.example.applazada.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.applazada.View.TrangChu.FragmentChuongTrinhKhuyenMai;
import com.example.applazada.View.TrangChu.FragmentDienTu;
import com.example.applazada.View.TrangChu.FragmentLamDep;
import com.example.applazada.View.TrangChu.FragmentMeVaBe;
import com.example.applazada.View.TrangChu.FragmentNhaCuaVaDoiSong;
import com.example.applazada.View.TrangChu.FragmentNoiBat;
import com.example.applazada.View.TrangChu.FragmentTheThaoVaDuLich;
import com.example.applazada.View.TrangChu.FragmentThoiTrang;
import com.example.applazada.View.TrangChu.FragmentThuongHieu;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> titleFragment = new ArrayList<String>();

    public ViewPagerAdapter(@NonNull FragmentManager fm) {


        super(fm);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChuongTrinhKhuyenMai());
        listFragment.add(new FragmentDienTu());
        listFragment.add(new FragmentNhaCuaVaDoiSong());
        listFragment.add(new FragmentMeVaBe());
        listFragment.add(new FragmentLamDep());
        listFragment.add(new FragmentThoiTrang());
        listFragment.add(new FragmentTheThaoVaDuLich());
        listFragment.add(new FragmentThuongHieu());

        titleFragment.add("Nổi bật");
        titleFragment.add("Chương trình khuyến mãi");
        titleFragment.add("Điện tử");
        titleFragment.add("Nhà cửa & đời sống");
        titleFragment.add("Mẹ và bé");
        titleFragment.add("Làm đẹp");
        titleFragment.add("Thời trang");
        titleFragment.add("Thể thao & du lịch");
        titleFragment.add("Thương hiệu");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titleFragment.get(position);

    }
}
