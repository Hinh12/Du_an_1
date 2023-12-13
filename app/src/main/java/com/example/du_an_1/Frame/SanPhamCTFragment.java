package com.example.du_an_1.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.adapter_slide;
import com.example.du_an_1.model.GioHang;
import com.example.du_an_1.model.SanPham;
import com.example.du_an_1.model.Slideiten;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class SanPhamCTFragment extends Fragment {
    TextView txt_Ma_San_Pham,txt_Ten_San_Pham,txt_So_Luong_Ban,txt_Gia_Ban,txt_Loai_San_Pham,txtHetHangchitietsp;
    Button addToCartButton;
    ImageView imgAnhSPCT;
    private GioHangDAO gioHangDao;
    ViewPager2 viewPager ;

    private adapter_slide adapter;

    private List<Slideiten> list;
    private ArrayList<GioHang> listGioHang = new ArrayList<>();
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private sanPhamDAO sanPhamDao;
    ImageButton bntBack;

    public SanPhamCTFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_san_pham_c_t, container, false);

        txt_Ma_San_Pham = view.findViewById(R.id.txt_Ma_San_Pham);
        txt_Ten_San_Pham = view.findViewById(R.id.txt_Ten_San_Pham);
        txt_So_Luong_Ban = view.findViewById(R.id.txt_So_Luong_Ban);
        txt_Gia_Ban = view.findViewById(R.id.txt_Gia_Ban);
        txt_Loai_San_Pham = view.findViewById(R.id.txt_Loai_San_Pham);
        txtHetHangchitietsp = view.findViewById(R.id.txtHetHangchitietsp);
        imgAnhSPCT = view.findViewById(R.id.imgAnhSPCT);
        addToCartButton = view.findViewById(R.id.btn_Them_gio_hang_chi_tiet);
        sanPhamDao = new sanPhamDAO(getContext());
        gioHangDao = new GioHangDAO(getContext());
        listSanPham = sanPhamDao.getDSSanPham();

        bntBack = view.findViewById(R.id.backButton);

        bntBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình trước đó
                HomeFragment homeFragment = new HomeFragment();

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frglayout, homeFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        Bundle bundle = getArguments();
        if (bundle != null){
            int maGiay = bundle.getInt("maGiay");
            SanPham sanPham = sanPhamDao.getSanPhamById(maGiay);

            txt_Ma_San_Pham.setText("Mã sản phẩm: " + sanPham.getMaGiay());
            txt_Ten_San_Pham.setText("Tên sản phẩm: " + sanPham.getTenGiay());
            txt_So_Luong_Ban.setText("Số lượt bán: " + "2000"); // Thay "200" bằng thông tin thực tế
            txt_Gia_Ban.setText("Giá: " + sanPham.getGiaTien());
            txt_Loai_San_Pham.setText("Loại sản phẩm: " + sanPham.getMaLoai());
            Picasso.get().load(sanPham.getAnh()).into(imgAnhSPCT);

            if (sanPham.getSoLuong() == 0) {
                addToCartButton.setVisibility(View.GONE);
                txtHetHangchitietsp.setVisibility(View.VISIBLE);
            } else {
                addToCartButton.setVisibility(View.VISIBLE);
                txtHetHangchitietsp.setVisibility(View.GONE);


                addToCartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        themVaoGio(sanPham);
                    }
                });
            }
        }




        return view;
    }

    private void themVaoGio(SanPham sanPham) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String maad = sharedPreferences.getString("maAD", "");
        int maSanPham = sanPham.getMaGiay();
        int slSanPham = getSoLuongSp(maSanPham);
        listGioHang = gioHangDao.getDanhSachGioHangByMaNguoiDung(maad);

        boolean isProductInCart = false;

        for (GioHang gioHang : listGioHang) {
            if (gioHang.getMaGiay() == maSanPham) {
                isProductInCart = true;
                if (gioHang.getSoLuongMua() < slSanPham) {
                    gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                    gioHangDao.updateGioHang(gioHang);
                    Snackbar.make(getView(), "Đã cập nhật giỏ hàng thành công", Snackbar.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Số lượng sản phẩm đã đạt giới hạn", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }

        if (!isProductInCart) {
            if (slSanPham > 0) {
                gioHangDao.insertGioHang(new GioHang(maSanPham, maad, 1));
            } else {
                Toast.makeText(getActivity(), "Sản phẩm hết hàng", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private int getSoLuongSp(int maSanPham) {
        for (SanPham sanPham : listSanPham) {
            if (sanPham.getMaGiay() == maSanPham) {
                return sanPham.getSoLuong();
            }
        }
        return 0; // Trả về 0 nếu không tìm thấy sản phẩm
    }
}