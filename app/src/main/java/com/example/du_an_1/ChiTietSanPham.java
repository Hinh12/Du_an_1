package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.du_an_1.model.SanPham;

public class ChiTietSanPham extends AppCompatActivity {
    TextView txt_Ma_San_Pham,txt_Ten_San_Pham,txt_So_Luong_Ban,txt_Gia_Ban,txt_Loai_San_Pham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);


        txt_Ma_San_Pham = findViewById(R.id.txt_Ma_San_Pham);
        txt_Ten_San_Pham = findViewById(R.id.txt_Ten_San_Pham);
        txt_So_Luong_Ban = findViewById(R.id.txt_So_Luong_Ban);
        txt_Gia_Ban = findViewById(R.id.txt_Gia_Ban);
        txt_Loai_San_Pham = findViewById(R.id.txt_Loai_San_Pham);

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



    }
}