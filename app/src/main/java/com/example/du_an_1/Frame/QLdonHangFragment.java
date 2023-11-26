package com.example.du_an_1.Frame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.DonHangAdapter;
import com.example.du_an_1.adapter.LoaiSanPhamadapter;
import com.example.du_an_1.adapter.sanPhamAdapter;
import com.example.du_an_1.model.DonHang;
import com.example.du_an_1.model.LoaiSanPham;

import java.util.ArrayList;


public class QLdonHangFragment extends Fragment {

    RecyclerView rcvdonhang;
    DonHangAdapter donHangAdapter;
    ArrayList<DonHang> list;
    DonHangDAO donHangDAO;
    public QLdonHangFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_q_ldon_hang, container, false);
        rcvdonhang= view.findViewById(R.id.rcvQLDH);
        donHangDAO= new DonHangDAO(getContext());
        ArrayList<DonHang> list= donHangDAO.getDSDonHang();
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        rcvdonhang.setLayoutManager(layoutManager);
        donHangAdapter= new DonHangAdapter(list, getContext());
        rcvdonhang.setAdapter(donHangAdapter);
        return view;
    }

}