package com.example.du_an_1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;


public class GioHangActivity extends AppCompatActivity {
    TextView giohongtrong, txttongtien;
    Toolbar toobar2;
    RecyclerView rcvgiohang;
    Button btnmuahang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        initView();

    }
    private void initView(){
        giohongtrong = findViewById(R.id.rcvgiohang);
        txttongtien = findViewById(R.id.txttongtien);
        btnmuahang = findViewById(R.id.btnmuahang);
        toobar2 = findViewById(R.id.toobar2);
        rcvgiohang = findViewById(R.id.rcvgiohang);
    }
}