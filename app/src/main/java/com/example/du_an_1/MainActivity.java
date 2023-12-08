package com.example.du_an_1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_1.Frame.DoiMatKhauFragment;
import com.example.du_an_1.Frame.HomeFragment;
import com.example.du_an_1.Frame.LichSuDonHangFragment;
import com.example.du_an_1.Frame.NotificationFragment;
import com.example.du_an_1.Frame.QLdonHangFragment;
import com.example.du_an_1.Frame.QLloaiSanPhamFragment;
import com.example.du_an_1.Frame.QLnguoiDungFragment;
import com.example.du_an_1.Frame.QLsanPhamFragment;
import com.example.du_an_1.Frame.ThongKeFragment;
import com.example.du_an_1.Frame.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navController;
    NavigationView navigationView;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acctivity_main_menu);

        DrawerLayout dralayout = findViewById(R.id.dralayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.menunav);
        navController = findViewById(R.id.bottomNavigationView);
        setUpNavigation();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null ){
            replay(new HomeFragment() );
            getSupportActionBar().setTitle("trang chủ");
        }

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                dralayout, toolbar, R.string.open, R.string.close);
        dralayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_quanLyNguoiDung) {
                    toolbar.setTitle("Quản lý người dùng");
                    QLnguoiDungFragment qlnguoidung = new QLnguoiDungFragment();
                    replay(qlnguoidung);
                    dralayout.closeDrawer(GravityCompat.START, false);
                } else if (item.getItemId() == R.id.nav_quanLySanPham) {
                    toolbar.setTitle("Quản lý sản phẩm");
                    QLsanPhamFragment qlsanpham = new QLsanPhamFragment();
                    replay(qlsanpham);
                    dralayout.closeDrawer(GravityCompat.START, false);
                } else if (item.getItemId() == R.id.nav_quanLyLoaiSanPham) {
                    toolbar.setTitle("Quản lý loại sản phẩm");
                    QLloaiSanPhamFragment qlloaisanpham = new QLloaiSanPhamFragment();
                    replay(qlloaisanpham);
                    dralayout.closeDrawer(GravityCompat.START, false);
                }else if (item.getItemId() == R.id.nav_quanLyDonHang) {
                    toolbar.setTitle("Quản đơn hàng");
                    QLdonHangFragment qldonhang = new QLdonHangFragment();
                    replay(qldonhang);
                    dralayout.closeDrawer(GravityCompat.START, false);
                } else if (item.getItemId() == R.id.nav_thongKe) {
                    toolbar.setTitle("Quản lý thông kê");
                    ThongKeFragment qlthongke = new ThongKeFragment();
                    replay(qlthongke);
                    dralayout.closeDrawer(GravityCompat.START, false);
                }else if (item.getItemId() == R.id.nav_lichsu) {
                    toolbar.setTitle("Lịch sử đơn hàng");
                    LichSuDonHangFragment lichSuDonHangFragment = new LichSuDonHangFragment();
                    replay(lichSuDonHangFragment);
                    dralayout.closeDrawer(GravityCompat.START, false);
                } else if (item.getItemId() == R.id.nav_doiMatKhau) {
                    toolbar.setTitle("Đổi mật khẩu");
                    DoiMatKhauFragment doimk = new DoiMatKhauFragment();
                    replay(doimk);
                    dralayout.closeDrawer(GravityCompat.START, false);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Đăng Xuất");
                    builder.setMessage("Bạn chắc chăn muốn đăng xuất chứ!");
                    builder.setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                            finish();
                        }
                    });

                    dralayout.openDrawer(GravityCompat.START);
                    builder.setNegativeButton("Hủy", null);
                    builder.create().show();

                }

                return false;
            }
        });

        SharedPreferences sharedPreferences= getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String loaiTK= sharedPreferences.getString("loaiTK", "");
        if (loaiTK.equals("admin")) {
            // Ẩn menu item lịch sử đơn hàng cho tài khoản admin
            navigationView.getMenu().findItem(R.id.nav_lichsu).setVisible(false);
        } else if (loaiTK.equals("khachhang")) {
            // Ẩn các menu item khác ngoại trừ lịch sử đơn hàng cho tài khoản khách hàng
            navigationView.getMenu().findItem(R.id.nav_quanLyNguoiDung).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_quanLySanPham).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_quanLyLoaiSanPham).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_quanLyDonHang).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_thongKe).setVisible(false);
            navigationView.getMenu().findItem(R.id.nav_doiMatKhau).setVisible(true); // Hiển thị đổi mật khẩu
        }


    }

    private void setUpNavigation() {
////        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        navController.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                SharedPreferences sharedPreferences= getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String loaiTK= sharedPreferences.getString("loaiTK", "");
                if (item.getItemId() == R.id.homeFragment) {
                    replay(new HomeFragment());
                } else if (item.getItemId() == R.id.notificationFragment) {
                    if(loaiTK.equals("admin")){
                        Toast.makeText(context, "Không có quyền truy cập", Toast.LENGTH_SHORT).show();
                    } else {
                        replay(new NotificationFragment());
                    }
                  

                } else if (item.getItemId() == R.id.userFragment) {
                    replay(new UserFragment());

                }
                getSupportActionBar().setTitle(item.getTitle());

                return true;
            }
        });



    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        return navController.navigateUp() || super.onSupportNavigateUp();
//    }
    public void replay(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frglayout, fragment).commit();

    }
}