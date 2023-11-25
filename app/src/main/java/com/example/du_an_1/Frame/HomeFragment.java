package com.example.du_an_1.Frame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1.ChiTietSanPham;
import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.adapter.adapter_slide;
import com.example.du_an_1.adapter.sanPhamAdapter;
import com.example.du_an_1.adapter.sanPhamHomeAdapter;
import com.example.du_an_1.model.LoaiSanPham;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.du_an_1.R;
import com.example.du_an_1.model.Slideiten;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class HomeFragment extends Fragment {

    RecyclerView rcv;
    sanPhamDAO dao;
    sanPhamHomeAdapter adapter;
    ArrayList<SanPham> list= new ArrayList<>();
    LinearLayout sanphamhome;

//    adapter_slide;

    ViewPager2 viewpage;
    private List<Slideiten> slidelist;
    private Handler slideHanlder = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv= view.findViewById(R.id.rcvgiay);
        viewpage = view.findViewById(R.id.viewpager);
        dao= new sanPhamDAO(getContext());
        list= dao.getDSSanPham();

//        sanphamhome = view.findViewById(R.id.sanphamhome);
        slidelist = new ArrayList<>(); // Khởi tạo slidelist trước khi sử dụng
        slidelist.add(new Slideiten(R.drawable.img1));
        slidelist.add(new Slideiten(R.drawable.img2));
        slidelist.add(new Slideiten(R.drawable.img3));
        slidelist.add(new Slideiten(R.drawable.img5));
        slidelist.add(new Slideiten(R.drawable.img6));
        slidelist.add(new Slideiten(R.drawable.img7));
        viewpage.setAdapter(new adapter_slide(slidelist, viewpage));
//        chamduoi.setViewPager(viewpage);
        //cài đặt thuộc tính viewpager 2
        viewpage.setClipToPadding(false);
        viewpage.setClipChildren(false);
        viewpage.setOffscreenPageLimit(3);///nhìn đc 3 ảnh :2 ảnh 2 bên và một ảnh ở giữa
        viewpage.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewpage.setPageTransformer(compositePageTransformer);
        viewpage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                slideHanlder.removeCallbacks(sildeRunnable);
                slideHanlder.postDelayed(sildeRunnable, 3000);
                // Kiểm tra nếu đang ở vị trí cuối cùng
//                if (position == slidelist.size() - 1) {
//                    // Post runnable để tự động cuộn về vị trí đầu tiên
//                    slideHanlder.postDelayed(sildeRunnable, 3000);
//                } else {
//                    // Nếu không phải vị trí cuối cùng, hủy runnable
//                    slideHanlder.removeCallbacks(sildeRunnable);
//                }
            }
        });



        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcv.setLayoutManager(gridLayoutManager);
        adapter = new sanPhamHomeAdapter(getContext(),list,getDSLoaiGiay());
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        adapter.setOnItemClickListener(position -> {
            SanPham selectedSanPham = list.get(position);
            Intent intent = new Intent(getContext(), ChiTietSanPham.class);
            intent.putExtra("sanPham", selectedSanPham);
            startActivity(intent);
        });



        return view;


    }


    private ArrayList<HashMap<String , Object>> getDSLoaiGiay(){
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list1 = loaiSanPhamDAO.getDSLoaiSP();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSanPham ls : list1){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", ls.getMaLoai());
            hs.put("tenLoai", ls.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
    private Runnable sildeRunnable = new Runnable() {
        @Override
        public void run() {
//            binding.viewpage.setCurrentItem(binding.viewpage.getCurrentItem() + 1);
            int vitri =viewpage.getCurrentItem();
            if (vitri == slidelist.size() - 1) {
                viewpage.setCurrentItem(0);
            } else {
                viewpage.setCurrentItem(vitri + 1);
            }
        }
    };


}