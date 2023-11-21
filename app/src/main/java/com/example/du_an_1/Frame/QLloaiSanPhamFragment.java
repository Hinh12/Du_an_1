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

import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.LoaiSanPhamadapter;
import com.example.du_an_1.model.LoaiSanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class QLloaiSanPhamFragment extends Fragment {
    FloatingActionButton fltAdd;
    RecyclerView rcvLSP;
    LoaiSanPhamDAO lspdao;


    public QLloaiSanPhamFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_q_lloai_san_pham, container, false);

        rcvLSP = view.findViewById(R.id.rcvLSP);
        fltAdd = view.findViewById(R.id.add_LSP);
        lspdao = new LoaiSanPhamDAO(getContext());
        loadData();

        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogAddLSP();
            }
        });

        return view;
    }

    public void DiaLogAddLSP(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.add_loaisanpham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenLSP = view.findViewById(R.id.in_addTenLSP);
        TextInputEditText ed_TenLSP = view.findViewById(R.id.ed_addTenLSP);
        Button AddLSP = view.findViewById(R.id.LSP_add);
        Button CancelLSP = view.findViewById(R.id.LSP_Cancel);

        ed_TenLSP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenLSP.setError("Vui lòng nhập tên loại sách");
                }else{
                    in_TenLSP.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        AddLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = ed_TenLSP.getText().toString();
                if(tenloai.isEmpty()){
                    if(tenloai.equals("")){
                        in_TenLSP.setError("Vui lòng nhập tên loại sản phẩm");
                    }else{
                        in_TenLSP.setError(null);
                    }
                }else{
                    if(lspdao.insert(tenloai)){
                        loadData();
                        Toast.makeText(getContext(), "Thêm loại sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(getContext(), "Thêm loại sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        CancelLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TenLSP.setText("");
            }
        });

    }

    private void loadData(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvLSP.setLayoutManager(layoutManager);
        ArrayList<LoaiSanPham> list = lspdao.getDSLoaiSP();
        LoaiSanPhamadapter adapter = new LoaiSanPhamadapter(getContext(),list);
        rcvLSP.setAdapter(adapter);
    }

}