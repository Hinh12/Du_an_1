package com.example.du_an_1.Frame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.Viewmd.SharedViewModel;
import com.example.du_an_1.adapter.GioHangAdapter;
import com.example.du_an_1.model.GioHang;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {
    private ArrayList<GioHang> list = new ArrayList<>();
    private GioHangAdapter gioHangAdapter;
    RecyclerView rcvGioHang;
    GioHangDAO gioHangDao;
    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);


        rcvGioHang = view.findViewById(R.id.rcvGioHang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvGioHang.setLayoutManager(layoutManager);
        gioHangAdapter = new GioHangAdapter(list,getContext());
        rcvGioHang.setAdapter(gioHangAdapter);
        gioHangDao = new GioHangDAO(getContext());
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMasp().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (isAdded() && isVisible()) {
                    if (sharedViewModel.getAddToCartClicked().getValue() != null && sharedViewModel.getAddToCartClicked().getValue()) {
                        Boolean addToCartClicked = sharedViewModel.getAddToCartClicked().getValue();
                        updateGioHangByMaSp(integer);
                        sharedViewModel.setAddToCartClicked(true); // Đặt lại trạng thái
                    }
                }
            }
        });

        list = gioHangDao.getDSGioHang();
        displayCart(list);

        return view;
    }
    public void updateGioHangByMaSp(int masp) {
        if (masp > 0) {

            ArrayList<GioHang> updatedCartList = gioHangDao.getDSGioHang();
            displayCart(updatedCartList);
        } else {
        }
    }
    private void displayCart(ArrayList<GioHang> cartList) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvGioHang.setLayoutManager(layoutManager);

        if (gioHangAdapter == null) {
            gioHangAdapter = new GioHangAdapter( cartList,getContext());
            rcvGioHang.setAdapter(gioHangAdapter);

        } else {
            gioHangAdapter.updateCartList(cartList);
            gioHangAdapter.notifyDataSetChanged();
        }
    }
}