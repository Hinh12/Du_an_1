package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1.Dao.AdminDAO;
import com.google.android.material.textfield.TextInputEditText;

public class Dangky extends AppCompatActivity {

    TextInputEditText txtTen, txthoTen;
    TextInputEditText txtMatkhau;
    TextInputEditText txtNhaplai;
    Button btnDK;
    Button btnDN;

    AdminDAO adminDAO = new AdminDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        txtTen = findViewById(R.id.txtTen);
        txthoTen = findViewById(R.id.txthoTen);
        txtMatkhau = findViewById(R.id.txtMatkhau);
        txtNhaplai = findViewById(R.id.txtNhaplai);
        btnDK = findViewById(R.id.btnDK);
        btnDN = findViewById(R.id.btnGoBack);

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dangky.this, LoginActivity.class));
            }
        });

    }
    private void registerUser() {
        String username = txtTen.getText().toString();
        String hoten = txthoTen.getText().toString();
        String password = txtMatkhau.getText().toString();
        String RePassword = txtNhaplai.getText().toString();

        if (username.isEmpty() || hoten.isEmpty() || password.isEmpty() || RePassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(RePassword)) {
            Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
        } else {
            boolean check = adminDAO.register(username, hoten, password);
            if (check) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                finish(); // Đóng Activity đăng ký và quay về màn hình đăng nhập
            } else {
                Toast.makeText(this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
            }
        }
    }


}