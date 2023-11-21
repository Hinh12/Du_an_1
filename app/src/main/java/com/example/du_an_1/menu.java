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

import com.example.du_an_1.Frame.DoiMatKhauFragment;
import com.example.du_an_1.Frame.QLdonHangFragment;
import com.example.du_an_1.Frame.QLloaiSanPhamFragment;
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

    }

    }
