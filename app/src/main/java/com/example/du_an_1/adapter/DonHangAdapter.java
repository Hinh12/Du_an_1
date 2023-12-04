package com.example.du_an_1.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.DonHang;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
        DonHang dh = list.get(position);
        holder.txtmadonhang.setText("Mã đơn hàng: " + String.valueOf(list.get(position).getMaDonHang()));
        holder.txtmanguoidung.setText("Mã người dùng: " + list.get(position).getMaAD());
        holder.txthotennguoidung.setText("Họ tên: " + list.get(position).getHoTen());
        holder.txtngay.setText("Ngày đặt hàng: " + list.get(position).getNgayDatHang());
        holder.txttongtien.setText("Tổng tiền: " + String.valueOf(list.get(position).getTongTien()));
        holder.txttrangthai.setText("Trạng thái: " + list.get(position).getTrangthai());

        holder.btnchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdateLSP(dh);
            }
        });

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
                                list.clear();
                                list = donHangDAO.getDSDonHang();
                                notifyDataSetChanged();
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
                builder.setNegativeButton("NO", null);
                builder.create().show();

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
        TextView txtmadonhang, txtmanguoidung, txthotennguoidung, txtngay, txttongtien, txttrangthai, btnchinhsua;
        ImageView btnxoa;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txtmadonhang= itemView.findViewById(R.id.txtmadonhang);
            txtmanguoidung= itemView.findViewById(R.id.txtmanguoidung);
            txthotennguoidung= itemView.findViewById(R.id.txthotennguoidung);
            txtngay= itemView.findViewById(R.id.txtngay);
            txttongtien= itemView.findViewById(R.id.txttongtien);
            txttrangthai= itemView.findViewById(R.id.txttrangthai);
            btnchinhsua= itemView.findViewById(R.id.btnchinhsuaTrangThai);
            btnxoa= itemView.findViewById(R.id.btnxoa);
        }
    }
    private void dialogUpdateLSP(DonHang dh){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_trangthaidh,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_updonhang = view.findViewById(R.id.in_updonhang);
        TextInputEditText ed_updonhang = view.findViewById(R.id.ed_updonhang);
        Button can= view.findViewById(R.id.btnhuy);
        Button up= view.findViewById(R.id.btnup);

        ed_updonhang.setText(dh.getTrangthai());

        ed_updonhang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_updonhang.setError("Vui lòng nhập trạng thái");
                }else{
                    in_updonhang.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dh.setTrangthai(ed_updonhang.getText().toString());

                String TenLSP = ed_updonhang.getText().toString();

                if(TenLSP.isEmpty()){
                    if(TenLSP.equals("")){
                        in_updonhang.setError("Vui Lòng Nhập Tên Loại Sản Phẩm");
                    }else{
                        in_updonhang.setError(null);
                    }
                }else{
                    if(donHangDAO.updateDonHang(dh)){
                        list.clear();
                        list.addAll(donHangDAO.getDSDonHang());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_updonhang.setText("");
            }
        });
    }
}