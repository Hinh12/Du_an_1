package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserNameActivity extends AppCompatActivity {
    Button btn;
    RecyclerView rcvGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_name);
        btn = findViewById(R.id.editProfileButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserNameActivity.this, menu_quanly.class);
                startActivity(intent);
            }
        });

    }
}