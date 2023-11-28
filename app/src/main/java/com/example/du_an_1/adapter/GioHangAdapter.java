package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.GioHang;

import java.util.ArrayList;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private ArrayList<GioHang> list;
    Context context;
    GioHangDAO dao;

    public GioHangAdapter(ArrayList<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         GioHang gioHang = list.get(position);
         holder.txtgia.setText(String.valueOf(gioHang.getGiaTien()));
         holder.txtsoluong.setText(String.valueOf(gioHang.getSoLuongMua()));
         holder.txttensp.setText(String.valueOf(gioHang.getTenGiay()));


         holder.btncong.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(gioHang.getSoLuongMua() >= 1){
                     gioHang.setSoLuongMua(gioHang.getSoLuongMua() + 1);
                     dao.updateGioHang(gioHang);
                     notifyDataSetChanged();

                 }
             }
         });

         holder.btntru.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (gioHang.getSoLuongMua() >= 1) {
                     gioHang.setSoLuongMua(gioHang.getSoLuongMua() - 1);
                     dao.updateGioHang(gioHang);
                     notifyDataSetChanged();
                 }else {
                     if (dao.deleteGioHang(gioHang)){
                         list.clear();
                         list.addAll(dao.getDSGioHang());
                         notifyDataSetChanged();
                     }else {
                         Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                     }
                 }

             }
         });



    }

    public void updateCartList(ArrayList<GioHang> updatedList) {
        list.clear();
        list.addAll(updatedList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensp,txtsoluong,txtgia;
        ImageButton btntru,btncong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensp = itemView.findViewById(R.id.txttensp);
            txtgia = itemView.findViewById(R.id.txtgia);
            txtsoluong = itemView.findViewById(R.id.txtsoluong);
            btncong = itemView.findViewById(R.id.btncong);
            btntru = itemView.findViewById(R.id.btntru);

        }
    }
}
