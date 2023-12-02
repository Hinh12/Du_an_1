package com.example.du_an_1.Frame;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Dao.DonHangChiTietDao;
import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.DonHangAdapter;
import com.example.du_an_1.adapter.LoaiSanPhamadapter;
import com.example.du_an_1.adapter.sanPhamAdapter;
import com.example.du_an_1.model.DonHang;
import com.example.du_an_1.model.DonHangChiTiet;
import com.example.du_an_1.model.LoaiSanPham;

import java.util.ArrayList;


public class QLdonHangFragment extends Fragment {

    RecyclerView rcvdonhang;
    DonHangAdapter donHangAdapter;
    private ArrayList<DonHang> list = new ArrayList<>();
    DonHangDAO donHangDAO;
    private DonHangChiTietDao chiTietDao;
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
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        rcvdonhang.setLayoutManager(layoutManager);
        list = donHangDAO.getDSDonHang();
        donHangAdapter= new DonHangAdapter(list, getContext());
        rcvdonhang.setAdapter(donHangAdapter);
        chiTietDao = new DonHangChiTietDao(getContext());
//        donHangAdapter.setOnItemClick(new DonHangAdapter.OnItemClick() {
//            @Override
//            public void onItemClick(int position) {
//                DonHang donHang = list.get(position);
//                int maDonHang = donHang.getMaDonHang();
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("maDonHang", maDonHang);
//                DonHangChiTietFragment frgDonHangChiTiet = new DonHangChiTietFragment();
//                frgDonHangChiTiet.setArguments(bundle);
//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.frglayout, frgDonHangChiTiet);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });


        return view;
    }


}