package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.DonHang;

import java.util.ArrayList;


public class Adapter_lich_su_don_hang extends RecyclerView.Adapter<Adapter_lich_su_don_hang.ViewHolder> {
    protected ArrayList<DonHang> list;
    protected DonHangDAO dao;
    private Context context;

    public Adapter_lich_su_don_hang(ArrayList<DonHang> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new DonHangDAO(context);
    }

    public interface OnItemClick {
        void onItemClick(int position);
    }

    private OnItemClick mListener;

    public void setOnItemClick(OnItemClick listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_lich_su_don_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DonHang donHang = list.get(position);
        holder.txt_Mdonhang.setText("Mã đơn hàng: " +String.valueOf(donHang.getMaDonHang()));
        holder.txt_Mnguoidung.setText("Mã người dung: " +donHang.getMaAD());
        holder.txt_DH_tennguoidung.setText("Tên người dùng: " + donHang.getHoTen());
        holder.txt_ngay_dat.setText("Ngày đặt hàng: " + donHang.getNgayDatHang());
        holder.txt_tong_tien.setText("Tổng tiền: " +String.valueOf(donHang.getTongTien()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(holder.getAdapterPosition());

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView txt_Mdonhang,txt_Mnguoidung,txt_DH_tennguoidung,txt_ngay_dat,txt_Trang_Thai,txt_tong_tien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_Mdonhang = itemView.findViewById(R.id.txt_Mdonhang);
            txt_Mnguoidung = itemView.findViewById(R.id.txt_Mnguoidung);
            txt_DH_tennguoidung = itemView.findViewById(R.id.txt_DH_tennguoidung);
            txt_ngay_dat = itemView.findViewById(R.id.txt_ngay_dat);
            txt_Trang_Thai = itemView.findViewById(R.id.txt_Trang_Thai);
            txt_tong_tien = itemView.findViewById(R.id.txt_tong_tien);

        }
    }
}
