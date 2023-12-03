package com.example.du_an_1.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.du_an_1.ChiTietSanPham;
import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.Viewmd.SharedViewModel;
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
    GioHangAdapter gioHangAdapter;
    GioHangDAO gioHangDAO;
    private SharedViewModel sharedViewModel;
    ArrayList<GioHang> listGioHang = new ArrayList<>();

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
        list= spdao.getDSSanPham();
        gioHangDAO = new GioHangDAO(getContext());
        gioHangAdapter = new GioHangAdapter(new ArrayList<>(),getContext());


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
        sanPhamHomeAdapter = new sanPhamHomeAdapter(getContext(),list);
        rcv.setAdapter(sanPhamHomeAdapter);
        sanPhamHomeAdapter.notifyDataSetChanged();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sanPhamHomeAdapter.setOnAddToCartClickListener(new sanPhamHomeAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(SanPham sanPham) {
                themVaoGio(sanPham);
            }
        });





        sanPhamHomeAdapter.setOnItemClickListener(position -> {
            SanPham selectedSanPham = list.get(position);
            Intent intent = new Intent(getContext(), ChiTietSanPham.class);
            intent.putExtra("sanPham", selectedSanPham);
            startActivity(intent);
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
        String maad = sharedPreferences.getString("USERNAME", "");
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




    private void addToCart(SanPham sanPham) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String maad = sharedPreferences.getString("USERNAME", "");
        int maSanPham = sanPham.getMaGiay();
        int slSanPham = getSoLuongSp(maSanPham);

        if (!sharedViewModel.isProductInCart(sanPham.getMaGiay())) {
            sharedViewModel.setMasp(sanPham.getMaGiay());
            sharedViewModel.setAddToCartClicked(true);
            sharedViewModel.addProductToCart(sanPham.getMaGiay());
            sharedViewModel.setQuantityToAdd(1);
            Log.i("tengiayuuuuu",sanPham.getTenGiay());
            Log.i("ma nguoi dunggggggggg",maad);
            gioHangDAO.insertGioHang(new GioHang(sanPham.getMaGiay(), maad, 1));
            Snackbar.make(getView(),"Đã thêm vào giỏ Hàng",Snackbar.LENGTH_SHORT).show();
        } else {
            GioHang hang = gioHangDAO.getGioHangByMasp(sanPham.getMaGiay(),maad);
            if (hang != null) {
                hang.setSoLuongMua(hang.getSoLuongMua() + 1);
                gioHangDAO.updateGioHang(hang);
                Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
            } else {
//                GioHang newCartItem = new GioHang(sanPham.getMaGiay(), maad, 1);
//                gioHangDAO.insertGioHang(newCartItem);
                Toast.makeText(getContext(), "Đã cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();

            }
//            gioHangAdapter.notifyDataSetChanged();
        }
//        ArrayList<GioHang> updatedCartList = gioHangDAO.getDSGioHang();
//        gioHangAdapter.updateCartList(updatedCartList);
//        gioHangAdapter.notifyDataSetChanged();
//        Toast.makeText(getContext(), "Đã cập nhật giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }







}