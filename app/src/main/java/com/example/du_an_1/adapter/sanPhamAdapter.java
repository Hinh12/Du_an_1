package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.GIay;

import java.util.ArrayList;

public class sanPhamAdapter extends RecyclerView.Adapter<sanPhamAdapter.ViewHoler> {
    private ArrayList<GIay> list;
    private Context context;
    sanPhamDAO dao;


    public sanPhamAdapter(ArrayList<GIay> list, Context context) {
        this.list = list;
        this.context = context;
        dao= new sanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_sanpham, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        holder.txtmasp.setText(String.valueOf(list.get(position).getMaGiay()));
        holder.txttensp.setText(list.get(position).getTenGIay());
        holder.txtgiasp.setText(String.valueOf(list.get(position).getGiaTien()));
        holder.txttenloaisp.setText(String.valueOf(list.get(position).getTenLoai()));
        GIay g= list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        TextView txtmasp, txttensp, txttrangthai, txtgiasp, txtloaisp, txttenloaisp;
        ImageView delete;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmasp= itemView.findViewById(R.id.txtma_san_pham);
            txttensp= itemView.findViewById(R.id.txt_ten_san_pham);
            txttrangthai= itemView.findViewById(R.id.txttrang_thai_san_pham);
            txtgiasp= itemView.findViewById(R.id.txtgia_san_pham);
            txtloaisp= itemView.findViewById(R.id.txtma_loai_san_pham2);
            txttenloaisp= itemView.findViewById(R.id.txtten_loai_san_pham2);
            delete= itemView.findViewById(R.id.can);
        }
    }
}
