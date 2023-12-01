package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.Viewmd.SharedViewModel;
import com.example.du_an_1.model.GioHang;
import com.example.du_an_1.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    TextView txt_Ma_San_Pham,txt_Ten_San_Pham,txt_So_Luong_Ban,txt_Gia_Ban,txt_Loai_San_Pham;
    Button addToCartButton;
    private SharedViewModel sharedViewModel;
    private GioHangDAO gioHangDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        txt_Ma_San_Pham = findViewById(R.id.txt_Ma_San_Pham);
        txt_Ten_San_Pham = findViewById(R.id.txt_Ten_San_Pham);
        txt_So_Luong_Ban = findViewById(R.id.txt_So_Luong_Ban);
        txt_Gia_Ban = findViewById(R.id.txt_Gia_Ban);
        txt_Loai_San_Pham = findViewById(R.id.txt_Loai_San_Pham);
        addToCartButton = findViewById(R.id.addToCartButton);

        Intent intent = getIntent();
        if (intent != null) {
            SanPham selectedSanPham = intent.getParcelableExtra("sanPham");
            if (selectedSanPham != null) {
                txt_Ma_San_Pham.setText("Mã sản phẩm: " + selectedSanPham.getMaGiay());
                txt_Ten_San_Pham.setText("Tên sản phẩm: " + selectedSanPham.getTenGiay());
                txt_So_Luong_Ban.setText("Số lượt bán: " + "2000"); // Thay "200" bằng thông tin thực tế
                txt_Gia_Ban.setText("Giá: " + selectedSanPham.getGiaTien());
                txt_Loai_San_Pham.setText("Loại sản phẩm: " + selectedSanPham.getMaLoai());

            }
        }


        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        gioHangDao = new GioHangDAO(this);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham selectedSanPham = intent.getParcelableExtra("sanPham");
                if (selectedSanPham != null) {
                    int maSanPham = selectedSanPham.getMaGiay();

                    // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
                    if (!sharedViewModel.isProductInCart(maSanPham)) {
                        sharedViewModel.setMasp(maSanPham);
                        sharedViewModel.setAddToCartClicked(true);
                        sharedViewModel.addProductToCart(maSanPham);
                        sharedViewModel.setQuantityToAdd(1);


                        String maad = getSharedPreferences("USERNAME", MODE_PRIVATE).getString("maAD", "");
                        gioHangDao.insertGioHang(new GioHang(maSanPham, maad, 1));
                    } else {
                        // Sản phẩm đã tồn tại trong giỏ hàng, thực hiện cập nhật số lượng
                        String maad = getSharedPreferences("NGUOIDUNG", MODE_PRIVATE).getString("maAD", "");
                        GioHang gioHang = gioHangDao.getGioHangByMasp(maSanPham, maad);
                        if (gioHang != null) {
                            gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                            gioHangDao.updateGioHang(gioHang);
                        } else {
                            GioHang newCartItem = new GioHang(maSanPham, maad, 1);
                            gioHangDao.insertGioHang(newCartItem);
                        }
                    }

                    Toast.makeText(ChiTietSanPham.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}