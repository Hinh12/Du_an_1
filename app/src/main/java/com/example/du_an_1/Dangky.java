package com.example.du_an_1;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1.Dao.AdminDAO;
import com.example.du_an_1.model.Admin;
import com.google.android.material.textfield.TextInputEditText;

public class Dangky extends AppCompatActivity {

    TextInputEditText txtTen, txthoTen;
    TextInputEditText txtMatkhau;
    TextInputEditText txtNhaplai;
    Button btnDK;
    Button btnDN;
    Admin admin= new Admin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        txtTen = findViewById(R.id.txtTen);
        txthoTen = findViewById(R.id.txthoTen);
        txtMatkhau = findViewById(R.id.txtMatkhau);
        txtNhaplai= findViewById(R.id.txtNhaplai);
        btnDK = findViewById(R.id.btnDK);
        btnDN = findViewById(R.id.btnGoBack);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Dangky.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateDangKy()){
                    clickDangKy();
                }
            }
        });
    }

    private void clickDangKy(){
        //Lấy thông tin từ trường nhập dữ liệu
        admin.setMaAD(txtTen.getText().toString());
        admin.setHoTen(txthoTen.getText().toString());
        admin.setMatKhau(txtMatkhau.getText().toString());
        admin.setLoaiTK("khachhang");

        AdminDAO dao= new AdminDAO(Dangky.this);
        boolean result= dao.checkDangKy(admin);
        if (result){
            Intent intent= new Intent(Dangky.this, LoginActivity.class);
            Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else{
            Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateDangKy(){
        AdminDAO dao= new AdminDAO(Dangky.this);
        String maAD= txtTen.getText().toString().trim();
        String hoTen= txthoTen.getText().toString().trim();
        String matKhau= txtMatkhau.getText().toString().trim();
        String nhapLai= txtNhaplai.getText().toString().trim();
        boolean Vali= true;
        if(maAD.isEmpty()){
            txtTen.setError("Vui lòng nhập mã đăng nhập");
            Vali= false;
        }else if(dao.tenDangNhapDaTonTai(maAD)){
            txtTen.setError("Mã đăng nhập đã tồn tại");
            return false;
        }else {
            txtTen.setError(null);
        }

        if(hoTen.isEmpty()){
            txthoTen.setError("Vui lòng nhập họ tên");
            Vali= false;
        }else {
            txthoTen.setError(null);
        }

        if(matKhau.isEmpty()){
            txtMatkhau.setError("Vui lòng nhập mật khẩu");
            Vali= false;
        }else {
            txtMatkhau.setError(null);
        }

        if (nhapLai.isEmpty()){
            txtNhaplai.setError("Vui lòng nhập lại mật khẩu");
            Vali= false;
        }else if (!nhapLai.equals(matKhau)){
            txtNhaplai.setError("Mật khẩu không trùng nhau");
            Vali= false;
        }
        return Vali;
    }

}