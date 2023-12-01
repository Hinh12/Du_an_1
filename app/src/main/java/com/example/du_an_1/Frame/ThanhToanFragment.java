package com.example.du_an_1.Frame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangChiTietDao;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.adapter_thanh_toan;
import com.example.du_an_1.model.DonHangChiTiet;

import java.util.ArrayList;


public class ThanhToanFragment extends Fragment {

    public ThanhToanFragment() {
        // Required empty public constructor
    }
    private ArrayList<DonHangChiTiet> list = new ArrayList<>();
    private DonHangChiTietDao chiTietDao;
    private adapter_thanh_toan adapterThanhToan;

    RecyclerView rcvThanhToan;
    Button btntieptucmua;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_thanh_toan, container, false);

        rcvThanhToan = view.findViewById(R.id.rcvThanhToan);
        btntieptucmua=view.findViewById(R.id.btntieptucmua);

        chiTietDao = new DonHangChiTietDao(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvThanhToan.setLayoutManager(layoutManager);
        adapterThanhToan = new adapter_thanh_toan(list,getContext());
        rcvThanhToan.setAdapter(adapterThanhToan);
        adapterThanhToan.notifyDataSetChanged();

        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang", 0);
            Log.d("maDonHang", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterThanhToan = new adapter_thanh_toan(list, getContext());
                rcvThanhToan.setAdapter(adapterThanhToan);

            }
        }

        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationFragment frgGioHang = new NotificationFragment();
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frglayout,frgGioHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return view;
    }
}