package com.example.du_an_1.Frame;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1.Dao.AdminDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.Adminadapter;
import com.example.du_an_1.model.Admin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class QLnguoiDungFragment extends Fragment {

    RecyclerView rcv;
    FloatingActionButton fltAdd;
    AdminDAO dao;
    Adminadapter adapter;
    ArrayList<Admin> list= new ArrayList<>();

    public QLnguoiDungFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_lnguoi_dung, container, false);
        rcv= view.findViewById(R.id.rcvnguoidung);
        fltAdd = view.findViewById(R.id.add_nguoidung);
        dao = new AdminDAO(getContext());
        list= dao.getDSNguoiDung();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        adapter = new Adminadapter(list,getContext());
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        view.findViewById(R.id.add_nguoidung).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAllNguoiDung();
            }
        });

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAllNguoiDung();
            }
        });


        return view;
    }
    private void dialogAllNguoiDung(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_add_nguoidung,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenTaiKhoan = view.findViewById(R.id.in_ten_tai_khoan);
        TextInputLayout in_HoTen = view.findViewById(R.id.in_ho_ten);
        TextInputLayout in_MatKhau = view.findViewById(R.id.in_mat_khau);
        TextInputEditText ed_TenTaiKhoan = view.findViewById(R.id.edt_ten_tai_khoan);
        TextInputEditText ed_HoTen = view.findViewById(R.id.edt_ho_ten);
        TextInputEditText ed_MatKhau = view.findViewById(R.id.edt_mat_khau);
        Button btn_huy_ND = view.findViewById(R.id.btn_huy_ND);
        Button btn_Them_ND = view.findViewById(R.id.btn_Them_ND);


        ed_TenTaiKhoan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenTaiKhoan.setError("Vui lòng không để trống tên tài khoản");
                }else{
                    in_TenTaiKhoan.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_HoTen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_HoTen.setError("Vui lòng không để trống Họ tên");
                }else{
                    in_HoTen.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_MatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_MatKhau.setError("Vui lòng không để trống Mật khẩu");
                }else{
                    in_MatKhau.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btn_Them_ND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tentaikhoan = ed_TenTaiKhoan.getText().toString();
                String hoten = ed_HoTen.getText().toString();
                String matkhau = ed_MatKhau.getText().toString();
                if(tentaikhoan.isEmpty() || hoten.isEmpty() || matkhau.isEmpty() ){
                    if(tentaikhoan.equals("")){
                        in_TenTaiKhoan.setError("Vui lòng không để trống tên tài khoản");
                    }else{
                        in_TenTaiKhoan.setError(null);
                    }

                    if(hoten.equals("")){
                        in_HoTen.setError("Vui lòng không để trống Họ Tên");
                    }else{
                        in_HoTen.setError(null);
                    }

                    if(matkhau.equals("")){
                        in_MatKhau.setError("Vui lòng không để trống Mật khẩu");
                    }else{
                        in_MatKhau.setError(null);
                    }
                }else{
                    boolean check = dao.register(tentaikhoan, hoten, matkhau);
                    if (check) {
                        loadData();
                        Toast.makeText(getContext(), "Thêm thành công ngươi dùng", Toast.LENGTH_SHORT).show();
                        list.clear();
                        list.addAll(dao.getDSNguoiDung());
                        adapter = new Adminadapter(list,getContext());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm không thành công người dùng", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_huy_ND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
    }

    private void loadData(){
        list = dao.getDSNguoiDung();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(layoutManager);
        Adminadapter adapter = new Adminadapter(list,getContext());
        rcv.setAdapter(adapter);
    }


}