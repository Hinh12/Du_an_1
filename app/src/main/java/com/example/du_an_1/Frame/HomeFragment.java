package com.example.du_an_1.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.GioHangAdapter;
import com.example.du_an_1.adapter.adapter_slide;
import com.example.du_an_1.adapter.sanPhamHomeAdapter;
import com.example.du_an_1.model.GioHang;
import com.example.du_an_1.model.SanPham;
import com.example.du_an_1.model.Slideiten;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    RecyclerView rcv;
    sanPhamDAO spdao;
    sanPhamHomeAdapter sanPhamHomeAdapter;
    ArrayList<SanPham> list= new ArrayList<>();
    ArrayList<SanPham> tempListSanPham= new ArrayList<>();
    GioHangAdapter gioHangAdapter;
    GioHangDAO gioHangDAO;
    ArrayList<GioHang> listGioHang = new ArrayList<>();
    EditText edSearch;

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
        spdao= new sanPhamDAO(getContext());
        list = spdao.getDSSanPham();
        tempListSanPham = spdao.getDSSanPham();
        sanPhamHomeAdapter = new sanPhamHomeAdapter(getContext(), list);
        rcv.setAdapter(sanPhamHomeAdapter);
        gioHangDAO = new GioHangDAO(getContext());
        gioHangAdapter = new GioHangAdapter(new ArrayList<>(),getContext());
        edSearch = view.findViewById(R.id.edSearch);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                ArrayList<SanPham> filteredList = new ArrayList<>();
                for (SanPham sanPham : list) {
                    if (sanPham.getTenGiay().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(sanPham);
                    }
                }
                sanPhamHomeAdapter.setSanPhamList(filteredList);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


//        sanphamhome = view.findViewById(R.id.sanphamhome);
        slidelist = new ArrayList<>(); // Khởi tạo slidelist trước khi sử dụng
        slidelist.add(new Slideiten(R.drawable.banner6));
        slidelist.add(new Slideiten(R.drawable.banner5));
        slidelist.add(new Slideiten(R.drawable.banner2));
        slidelist.add(new Slideiten(R.drawable.banner4));
//        slidelist.add(new Slideiten(R.drawable.banner3));
//        slidelist.add(new Slideiten(R.drawable.banner1));
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
        sanPhamHomeAdapter = new sanPhamHomeAdapter(getContext(),list);
        rcv.setAdapter(sanPhamHomeAdapter);
        sanPhamHomeAdapter.notifyDataSetChanged();

        sanPhamHomeAdapter.setOnAddToCartClickListener(new sanPhamHomeAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(SanPham sanPham) {
                themVaoGio(sanPham);
            }
        });



        sanPhamHomeAdapter.setOnItemClickListener(position -> {
            Bundle bundle = new Bundle();
            bundle.putInt("maGiay", list.get(position).getMaGiay());
            bundle.putString("tenLoai", list.get(position).getTenLoai());
            SanPhamCTFragment sanPhamCTFragment = new SanPhamCTFragment();
            sanPhamCTFragment.setArguments(bundle);

            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frglayout, sanPhamCTFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;
    }

    private int getSoLuongSp(int maSanPham) {
        for (SanPham sanPham : list) {
            if (sanPham.getMaGiay() == maSanPham) {
                return sanPham.getSoLuong();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy sản phẩm
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return new ViewModelStore();
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

    private void themVaoGio(SanPham sanPham) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String maad = sharedPreferences.getString("maAD", "");
        int maSanPham = sanPham.getMaGiay();
        int slSanPham = getSoLuongSp(maSanPham);
        listGioHang = gioHangDAO.getDanhSachGioHangByMaNguoiDung(maad);

        boolean isProductInCart = false;

        for (GioHang gioHang : listGioHang) {
            if (gioHang.getMaGiay() == maSanPham) {
                isProductInCart = true;
                if (gioHang.getSoLuongMua() < slSanPham) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    gioHangDAO.updateGioHang(gioHang);
                    Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        if (!isProductInCart) {
            if (slSanPham > 0) {
                gioHangDAO.insertGioHang(new GioHang(maSanPham, maad, 1));
            } else {
                Toast.makeText(getActivity(), "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }




}