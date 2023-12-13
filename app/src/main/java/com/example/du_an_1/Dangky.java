package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.Dao.AdminDAO;
import com.example.du_an_1.model.Admin;
import com.google.android.material.textfield.TextInputEditText;

public class Dangky extends AppCompatActivity {

    TextInputEditText txtTen, txthoTen;
    TextInputEditText txtMatkhau;
    TextInputEditText txtNhaplai, txtSoDienThoai, txtDiaChi;
    Button btnDK;
    Button btnDN;
    Admin admin= new Admin();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        txtSoDienThoai = findViewById(R.id.edtSoDienThoaiDK);
        txtDiaChi = findViewById(R.id.edtDiaChiDK);
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
        admin.setAnh("https://cdn.sforum.vn/sforum/wp-content/uploads/2023/10/avatar-trang-4.jpg");
        admin.setDiaChi(txtDiaChi.getText().toString());
        admin.setSdt(txtSoDienThoai.getText().toString());
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


        if(txtDiaChi.getText().toString().trim().isEmpty()){
            txtDiaChi.setError("Vui lòng nhập địa chỉ");
            Vali= false;
        }else {
            txtDiaChi.setError(null);
        }

        if(txtSoDienThoai.getText().toString().trim().isEmpty()){
            txtSoDienThoai.setError("Vui lòng nhập số điện thoại");
            Vali= false;
        }else if(!isValidSDT(txtSoDienThoai.getText().toString().trim())){
            txtSoDienThoai.setError("Số điện thoại không hợp lệ");
            Vali=false;
        }else {
            txtSoDienThoai.setError(null);
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

    private boolean isValidSDT(String phoneNumber) {
        String regex = "0\\d{9}";
        return phoneNumber.matches(regex);
    }

}