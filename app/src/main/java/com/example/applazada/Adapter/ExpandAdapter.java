package com.example.applazada.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.applazada.Model.ObjectClass.LoaiSanPham;
import com.example.applazada.Model.TrangChu.XuLyMenu.XuLyJSONMenu;
import com.example.applazada.R;

import java.util.List;

public class ExpandAdapter extends BaseExpandableListAdapter {

    Context context;
    List<LoaiSanPham> loaiSanPhams;

    public ExpandAdapter(Context context, List<LoaiSanPham> loaiSanPhams) {
        this.context = context;
        this.loaiSanPhams = loaiSanPhams;

        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiSanPhams.size();
        for (int i = 0; i < count; i++) {
            int maloaisp = loaiSanPhams.get(i).getMALOAISP();
            loaiSanPhams.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
        }

    }

    @Override
    public int getGroupCount() {
        return loaiSanPhams.size();
    }

    @Override
    public int getChildrenCount(int vitriGroupCha) {
        if (loaiSanPhams.get(vitriGroupCha).getListCon().size() != 0) {
            return 1;

        } else {
            return 0;
        }

    }

    @Override
    public Object getGroup(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha);
    }

    @Override
    public Object getChild(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon);
    }

    @Override
    public long getGroupId(int vitriGroupCha) {
        return loaiSanPhams.get(vitriGroupCha).getMALOAISP();
    }

    @Override
    public long getChildId(int vitriGroupCha, int vitriGroupCon) {
        return loaiSanPhams.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);
        TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
        ImageView imageView = viewGroupCha.findViewById(R.id.imgMenuPlus);
        txtTenLoaiSP.setText(loaiSanPhams.get(vitriGroupCha).getTENLOAISP());
        int demsanphamcon = loaiSanPhams.get(vitriGroupCha).getListCon().size();
        imageView.setVisibility(demsanphamcon > 0 ? View.VISIBLE : View.INVISIBLE);
        imageView.setImageResource(isExpanded ? R.drawable.ic_remove_black_24dp : R.drawable.ic_add_black_24dp);
        viewGroupCha.setBackgroundResource(isExpanded ? R.color.colorGray : R.color.colorWhite);
        viewGroupCha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Log.d("maloai", loaiSanPhams.get(vitriGroupCha).getTENLOAISP() + "-" + loaiSanPhams.get(vitriGroupCha).getMALOAISP());
                return false;
            }
        });
        return viewGroupCha;
    }

    @Override
    public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
        // LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //View viewGroupCon = layoutInflater.inflate(R.layout.custom_layout_group_con, viewGroup, false);
        // ExpandableListView expandableListView = viewGroupCon.findViewById(R.id.epMenuCon);

        SecondExpanable secondExpanable = new SecondExpanable(context);
        ExpandAdapter secondAdapter = new ExpandAdapter(context, loaiSanPhams.get(vitriGroupCha).getListCon());
        secondExpanable.setAdapter(secondAdapter);
        secondExpanable.setGroupIndicator(null);
        notifyDataSetChanged();

        return secondExpanable;
    }


    public class SecondExpanable extends ExpandableListView {

        public SecondExpanable(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            // widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            //chiếm toàn màn hình bỏ width
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }


    @Override
    public boolean isChildSelectable(int vitriGroupCha, int vitriGroupCon) {
        return false;
    }

//    public class SecondAdapter extends BaseExpandableListAdapter {
//        List<LoaiSanPham> listCon;
//
//        public SecondAdapter(List<LoaiSanPham> listCon) {
//            this.listCon = listCon;
//
//            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
//            int count = listCon.size();
//            for (int i = 0; i < count; i++) {
//                int maloaisp = listCon.get(i).getMALOAISP();
//                listCon.get(i).setListCon(xuLyJSONMenu.LayLoaiSanPhamTheoMaLoai(maloaisp));
//            }
//        }
//
//
//        @Override
//        public int getGroupCount() {
//            return listCon.size();
//        }
//
//        @Override
//        public int getChildrenCount(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getListCon().size();
//        }
//
//        @Override
//        public Object getGroup(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha);
//        }
//
//        @Override
//        public Object getChild(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon);
//        }
//
//        @Override
//        public long getGroupId(int vitriGroupCha) {
//            return listCon.get(vitriGroupCha).getMALOAISP();
//        }
//
//        @Override
//        public long getChildId(int vitriGroupCha, int vitriGroupCon) {
//            return listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getMALOAISP();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return false;
//        }
//
//        @Override
//        public View getGroupView(int vitriGroupCha, boolean isExpanded, View view, ViewGroup viewGroup) {
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View viewGroupCha = layoutInflater.inflate(R.layout.custom_layout_group_cha, viewGroup, false);
//            TextView txtTenLoaiSP = viewGroupCha.findViewById(R.id.txtTenLoaiSP);
//            txtTenLoaiSP.setText(listCon.get(vitriGroupCha).getTENLOAISP());
//            return viewGroupCha;
//        }
//
//        @Override
//        public View getChildView(int vitriGroupCha, int vitriGroupCon, boolean isExpanded, View view, ViewGroup viewGroup) {
//            TextView tv = new TextView(context);
//            tv.setText(listCon.get(vitriGroupCha).getListCon().get(vitriGroupCon).getTENLOAISP());
//            tv.setPadding(15, 5, 5, 5);
//            tv.setBackgroundColor(Color.YELLOW);
//            tv.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//
//            return tv;
//        }
//
//
//        @Override
//        public boolean isChildSelectable(int i, int i1) {
//            return false;
//        }
//    }
}
