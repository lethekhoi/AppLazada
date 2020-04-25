package com.example.applazada.View.TrangChu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Adapter.AdapterDienTu;
import com.example.applazada.Adapter.AdapterThuongHieuLonDienTu;
import com.example.applazada.Adapter.AdapterTopDienThoaiDienTu;
import com.example.applazada.Model.ObjectClass.DienTu;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.applazada.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FragmentDienTu extends Fragment implements ViewDienTu {
    View view;
    RecyclerView recyclerView, recyclerViewTopCacThuongHieuLon, recyclerViewHangMoiVe;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;
    ImageView imgSanPham1, imgSanPham2, imgSanPham3;
    TextView txtSanPham1, txtSanPham2, txtSanPham3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView = view.findViewById(R.id.recyclerDienTu);
        recyclerViewTopCacThuongHieuLon = view.findViewById(R.id.recyclerTopThuongHieuLon);
        recyclerViewHangMoiVe = view.findViewById(R.id.recyclerHanMoiVe);
        imgSanPham1 = view.findViewById(R.id.imgSanPham1);
        imgSanPham2 = view.findViewById(R.id.imgSanPham2);
        imgSanPham3 = view.findViewById(R.id.imgSanPham3);
        txtSanPham1 = view.findViewById(R.id.txtSanPham1);
        txtSanPham2 = view.findViewById(R.id.txtSanPham2);
        txtSanPham3 = view.findViewById(R.id.txtSanPham3);
        presenterLogicDienTu = new PresenterLogicDienTu(this);
        dienTuList = new ArrayList<>();
        presenterLogicDienTu.LayDanhSachDienTu();
        presenterLogicDienTu.LayDanhSachLogoThuongHieu();
        presenterLogicDienTu.LayDanhSachSanPhamMoi();
        return view;
    }

    @Override
    public void HienThiDanhSach(List<DienTu> dienTus) {

        dienTuList = dienTus;


        AdapterDienTu adapterDienTu = new AdapterDienTu(getContext(), dienTuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterDienTu);
        adapterDienTu.notifyDataSetChanged();


    }

    @Override
    public void HienThiLogoThuongHieu(List<ThuongHieu> thuongHieus) {

        AdapterThuongHieuLonDienTu adapterThuongHieuLonDienTu = new AdapterThuongHieuLonDienTu(getContext(), thuongHieus);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        recyclerViewTopCacThuongHieuLon.setLayoutManager(layoutManager);
        recyclerViewTopCacThuongHieuLon.setAdapter(adapterThuongHieuLonDienTu);
        adapterThuongHieuLonDienTu.notifyDataSetChanged();
    }

    @Override
    public void LoiLayDuLieu() {

    }

    @Override
    public void HienThiSanPhamMoiVe(List<SanPham> sanPhams) {
        AdapterTopDienThoaiDienTu adapterTopDienThoaiDienTu = new AdapterTopDienThoaiDienTu(getContext(), sanPhams);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewHangMoiVe.setLayoutManager(layoutManager1);
        recyclerViewHangMoiVe.setAdapter(adapterTopDienThoaiDienTu);
        adapterTopDienThoaiDienTu.notifyDataSetChanged();
        Random random = new Random();

        int vitri1 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri1).getANHLON()).fit().centerInside().into(imgSanPham1);
        txtSanPham1.setText(sanPhams.get(vitri1).getTENSP());

        int vitri2 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri2).getANHLON()).fit().centerInside().into(imgSanPham2);
        txtSanPham2.setText(sanPhams.get(vitri2).getTENSP());


        int vitri3 = random.nextInt(sanPhams.size());
        Picasso.with(getContext()).load(sanPhams.get(vitri3).getANHLON()).fit().centerInside().into(imgSanPham3);
        txtSanPham3.setText(sanPhams.get(vitri3).getTENSP());
    }
}
