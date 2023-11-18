package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class menu_quanly extends AppCompatActivity {
    ImageButton btn_bac;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quanly);
        btn_bac = findViewById(R.id.btn_back);
        btn_bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay lại màn hình trước đó
                Intent intent= new Intent(menu_quanly.this, UserFragment.class);
                startActivity(intent);
            }
        });
    }
}