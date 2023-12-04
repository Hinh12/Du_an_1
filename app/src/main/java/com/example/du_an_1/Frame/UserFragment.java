package com.example.du_an_1.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.R;
import com.example.du_an_1.menu_quanly;
import com.example.du_an_1.model.SanPham;
import com.squareup.picasso.Picasso;

public class UserFragment extends Fragment {

    TextView txtPTenDangNhap, txtPHoTen;

    ImageView imgAvatar_Profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        imgAvatar_Profile = view.findViewById(R.id.imgAvatar_Profile);

        txtPTenDangNhap = view.findViewById(R.id.txtPTenDangNhap);
        txtPHoTen = view.findViewById(R.id.txtPHoTen);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String urlAnh = sharedPreferences.getString("anh","");
        Picasso.get().load(urlAnh).into(imgAvatar_Profile);
        SharedPreferences preferences = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String maad = preferences.getString("maAD", "");
        String hoten = preferences.getString("hoTen", "");

        txtPTenDangNhap.setText("Mã tài khoản: " + String.valueOf(maad));
        txtPHoTen.setText("Họ Tên: "+ String.valueOf(hoten));
        return view;
    }
}