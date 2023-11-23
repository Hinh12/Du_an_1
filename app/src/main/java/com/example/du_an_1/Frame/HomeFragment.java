package com.example.du_an_1.Frame;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.du_an_1.ChiTietSanPham;
import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.adapter.sanPhamAdapter;
import com.example.du_an_1.adapter.sanPhamHomeAdapter;
import com.example.du_an_1.model.LoaiSanPham;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class HomeFragment extends Fragment {

    RecyclerView rcv;
    sanPhamDAO dao;
    sanPhamHomeAdapter adapter;
    ArrayList<SanPham> list= new ArrayList<>();
    LinearLayout sanphamhome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv= view.findViewById(R.id.rcvgiay);
        dao= new sanPhamDAO(getContext());
        list= dao.getDSSanPham();
//        sanphamhome = view.findViewById(R.id.sanphamhome);


        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rcv.setLayoutManager(gridLayoutManager);
        adapter = new sanPhamHomeAdapter(getContext(),list,getDSLoaiGiay());
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        adapter.setOnItemClickListener(position -> {
            SanPham selectedSanPham = list.get(position);
            Intent intent = new Intent(getContext(), ChiTietSanPham.class);
            intent.putExtra("sanPham", selectedSanPham);
            startActivity(intent);
        });



        return view;


    }


    private ArrayList<HashMap<String , Object>> getDSLoaiGiay(){
        LoaiSanPhamDAO loaiSanPhamDAO = new LoaiSanPhamDAO(getContext());
        ArrayList<LoaiSanPham> list1 = loaiSanPhamDAO.getDSLoaiSP();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSanPham ls : list1){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maLoai", ls.getMaLoai());
            hs.put("tenLoai", ls.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }


}