package com.example.du_an_1.Frame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangChiTietDao;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.adapter_don_hang_chi_tiet;
import com.example.du_an_1.model.DonHangChiTiet;

import java.util.ArrayList;


public class DonHangChiTietFragment extends Fragment {

    private ArrayList<DonHangChiTiet> list = new ArrayList<>();

    private adapter_don_hang_chi_tiet adapterDonHangChiTiet;
    DonHangChiTietDao chiTietDao;
    RecyclerView rcvDonHangChiTiet;
    TextView btnback;

    public DonHangChiTietFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_don_hang_chi_tiet, container, false);
        rcvDonHangChiTiet = view.findViewById(R.id.rcv_don_hang_chiTiet);
        btnback = view.findViewById(R.id.btnback);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvDonHangChiTiet.setLayoutManager(layoutManager);
        adapterDonHangChiTiet = new adapter_don_hang_chi_tiet(list,getContext());
        rcvDonHangChiTiet.setAdapter(adapterDonHangChiTiet);
        adapterDonHangChiTiet.notifyDataSetChanged();
        chiTietDao = new DonHangChiTietDao(getContext());

        Bundle bundle = getArguments();
        if (bundle != null) {
            int maDonHang = bundle.getInt("maDonHang");
            Log.d("maDonHang", String.valueOf(maDonHang));
            if (maDonHang != 0) {
                list = chiTietDao.getChiTietDonHangByMaDonHang(maDonHang);
                adapterDonHangChiTiet = new adapter_don_hang_chi_tiet(list, getContext());
                rcvDonHangChiTiet.setAdapter(adapterDonHangChiTiet);

            }
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QLdonHangFragment frgQuanLyDonHang = new QLdonHangFragment();//fragment được chuyển đến sau khi ấn
                FragmentManager fragmentManager=getParentFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frglayout,frgQuanLyDonHang);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}