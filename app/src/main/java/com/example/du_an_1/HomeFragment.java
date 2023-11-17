package com.example.du_an_1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.adapter.sanPhamAdapter;
import com.example.du_an_1.model.GIay;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    sanPhamDAO dao;
    sanPhamAdapter adapter;
    ArrayList<GIay> list= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView= view.findViewById(R.id.rcvgiay);

        dao= new sanPhamDAO(getContext());
        list= dao.getGiayAll();

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}