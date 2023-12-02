package com.example.du_an_1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.DonHang;

import java.util.ArrayList;
import java.util.HashMap;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.Viewholder> {
    private ArrayList<DonHang> list;
    private DonHangDAO donHangDAO;
    private Context context;


    public DonHangAdapter(ArrayList<DonHang> list, Context context) {
        this.list = list;
        this.context = context;
        donHangDAO= new DonHangDAO(context);
    }

    public interface OnItemClick{
        void onItemClick(int position);
    }
    private OnItemClick mListener;
    public void setOnItemClick(OnItemClick listener){
        mListener = listener;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view= inflater.inflate(R.layout.item_quan_li_donhang, parent, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DonHang donHang = list.get(position);
        holder.txtmadonhang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.txtmanguoidung.setText("Mã người dùng: " + list.get(position).getMaAD());
        holder.txthotennguoidung.setText("Họ tên: " + list.get(position).getHoTen());
        holder.txtngay.setText("Ngày đặt hàng: " + list.get(position).getNgayDatHang());
        holder.txttongtien.setText("Tổng tiền: " + String.valueOf(list.get(position).getTongTien()));
        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa Dơn Hàng này không!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        donHangDAO = new DonHangDAO(context);
                        int check = donHangDAO.xoaDonHang(list.get(holder.getAdapterPosition()).getMaDonHang());
                        switch(check){
                            case 1:
                                //  loadData();
                                Toast.makeText(context, "Xóa đơn hàng thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa đơn hàng không thành", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được đơn hàng này vì đang còn tồn tại trong Hóa đơn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                    }
                });

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    mListener.onItemClick(holder.getAdapterPosition());

                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView txtmadonhang, txtmanguoidung, txthotennguoidung, txtngay, txttongtien;
        ImageButton btnxoa;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtmadonhang= itemView.findViewById(R.id.txtmadonhang);
            txtmanguoidung= itemView.findViewById(R.id.txtmanguoidung);
            txthotennguoidung= itemView.findViewById(R.id.txthotennguoidung);
            txtngay= itemView.findViewById(R.id.txtngay);
            txttongtien= itemView.findViewById(R.id.txttongtien);
            btnxoa= itemView.findViewById(R.id.btnxoa);
        }
    }
}