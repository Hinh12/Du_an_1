package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.du_an_1.Frame.QLdonHangFragment;
import com.example.du_an_1.Frame.QLnguoiDungFragment;
import com.example.du_an_1.Frame.QLsanPhamFragment;
import com.example.du_an_1.Frame.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

public class menu extends AppCompatActivity {

    Context context= this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        DrawerLayout dralayout = findViewById(R.id.dralayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView nav = findViewById(R.id.menunav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                dralayout, toolbar, R.string.open, R.string.close);
        dralayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_quanLyNguoiDung) {
                    toolbar.setTitle("Quản lý phiếu mượn");
                    QLnguoiDungFragment qlphieumuon = new QLnguoiDungFragment();
                    replay(qlphieumuon);
                } else if (item.getItemId() == R.id.nav_quanLySanPham) {
                    toolbar.setTitle("Quản lý loại sách");
                    QLsanPhamFragment loaisach = new QLsanPhamFragment();
                    replay(loaisach);
                } else if (item.getItemId() == R.id.nav_quanLyDonHang) {
                    toolbar.setTitle("Quản lý sách");
                    QLdonHangFragment sach = new QLdonHangFragment();
                    replay(sach);
                } else if (item.getItemId() == R.id.nav_thongKe) {
                    toolbar.setTitle("Quản lý thành viên");
                    ThongKeFragment thanhvien = new ThongKeFragment();
                    replay(thanhvien);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chăn muướn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(menu.this, LoginActivity.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("Hủy", null);
                    builder.create().show();
                }

                return true;
            }
        });
    }
    public void replay(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frglayout, fragment).commit();

    }
    }
