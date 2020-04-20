package com.example.applazada.View.TrangChu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applazada.Adapter.AdapterDienTu;
import com.example.applazada.Model.ObjectClass.DienTu;
import com.example.applazada.Model.ObjectClass.SanPham;
import com.example.applazada.Model.ObjectClass.ThuongHieu;
import com.example.applazada.Presenter.TrangChu_DienTu.PresenterLogicDienTu;
import com.example.applazada.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentDienTu extends Fragment implements ViewDienTu {
    View view;
    RecyclerView recyclerView;
    List<DienTu> dienTuList;
    PresenterLogicDienTu presenterLogicDienTu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_dientu, container, false);
        recyclerView = view.findViewById(R.id.recyclerDienTu);
        presenterLogicDienTu = new PresenterLogicDienTu(this);
        dienTuList = new ArrayList<>();
        presenterLogicDienTu.LayDanhSachDienTu();


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
}
