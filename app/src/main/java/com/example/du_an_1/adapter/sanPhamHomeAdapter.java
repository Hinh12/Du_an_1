package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.LoaiSanPham;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;

public class sanPhamHomeAdapter extends RecyclerView.Adapter<sanPhamHomeAdapter.ViewHoler> {
    private ArrayList<SanPham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    sanPhamDAO spdao;


    public sanPhamHomeAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
        spdao = new sanPhamDAO(context);
    }

    public void setSanPhamList(ArrayList<SanPham> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Biến để lưu trữ listener
    private OnItemClickListener mListener;

    // Phương thức để thiết lập listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    private OnAddToCartClickListener mAddToCartClickListener;

    //nút thêm vào giỏ hàng
    public interface OnAddToCartClickListener {
        void onAddToCartClick(SanPham sanPham);
    }

    public void setOnAddToCartClickListener(OnAddToCartClickListener listener) {
        mAddToCartClickListener = listener;
    }



    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanphamhome, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        LoaiSanPhamDAO loaispdao = new LoaiSanPhamDAO(context);
        LoaiSanPham loaisp = loaispdao.getLoaiSanPhamByID(list.get(position).getMaLoai());
        holder.txttensp.setText(list.get(position).getTenGiay());
        holder.txtgiasp.setText(String.valueOf(list.get(position).getGiaTien()));
        holder.txt_so_luong_san_pham.setText(String.valueOf(list.get(position).getSoLuong()));
        SanPham sp = list.get(position);


        if (list.get(position).getSoLuong() == 0) {
            holder.btn_themvagiohang.setVisibility(View.GONE);
            holder.txtHetHang.setVisibility(View.VISIBLE);
        } else {
            holder.btn_themvagiohang.setVisibility(View.VISIBLE);
            holder.txtHetHang.setVisibility(View.GONE);
            holder.btn_themvagiohang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mAddToCartClickListener != null) {
                        mAddToCartClickListener.onAddToCartClick(list.get(holder.getAdapterPosition()));
                    }
                }
            });
        }


//        holder.btn_themvagiohang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mAddToCartClickListener != null){
//                    mAddToCartClickListener.onAddToCartClick(list.get(holder.getAdapterPosition()));
//                }
//            }
//        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView txtmasp, txttensp, txtgiasp, txtmaloaisp, txtHetHang, txt_so_luong_san_pham;
        Button btn_themvagiohang;
        LinearLayout sanphamhome;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmasp = itemView.findViewById(R.id.txtma_san_pham);
            txttensp = itemView.findViewById(R.id.txt_ten_san_pham);
            txtgiasp = itemView.findViewById(R.id.txtgia_san_pham);
            txtmaloaisp = itemView.findViewById(R.id.txtma_loai_san_pham2);
            sanphamhome = itemView.findViewById(R.id.sanphamhome);
            txt_so_luong_san_pham = itemView.findViewById(R.id.txt_so_luong_san_pham);
            txtHetHang = itemView.findViewById(R.id.txtHetHang);
            btn_themvagiohang = itemView.findViewById(R.id.btn_themvaogiohang);




        }
    }


}
