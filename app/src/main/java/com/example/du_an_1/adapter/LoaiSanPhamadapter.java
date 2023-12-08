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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.model.LoaiSanPham;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class LoaiSanPhamadapter extends RecyclerView.Adapter<LoaiSanPhamadapter.ViewHolder> {

    private Context context;
    private ArrayList<LoaiSanPham> list;
    LoaiSanPhamDAO lspdao;

    public LoaiSanPhamadapter(Context context, ArrayList<LoaiSanPham> list) {
        this.context = context;
        this.list = list;
        lspdao = new LoaiSanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loaisanpham,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.txtMaLoai.setText("Mã Loại Sản Phẩm: " + String.valueOf(list.get(position).getMaLoai()));
          holder.txtTenLoai.setText("Tên Loại Sản Phẩm: " +String.valueOf(list.get(position).getTenLoai()));
          LoaiSanPham lsp = list.get(position);

        holder.Delete_LSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Xóa loại sạn phẩm");
                builder.setMessage("Bạn có muốn xóa loại sạn phẩm này không!");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiSanPhamDAO lspdao = new LoaiSanPhamDAO(context);
                        int check = lspdao.deleteLSP(list.get(holder.getAdapterPosition()).getMaLoai());
                        switch (check){
                            case 1:
                                list.clear();
                                list = lspdao.getDSLoaiSP();
                                notifyDataSetChanged();
                                Toast.makeText(context, "Xóa loại sạn phẩm thành công", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không thể xóa vì đang có sạn phẩm thuộc thể loại", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa loại sạn phẩm không thành công", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy",null);
                builder.create().show();
            }
        });

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dialogUpdateLSP(lsp);
           }
       });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMaLoai, txtTenLoai;
        ImageButton Delete_LSP;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaLoai = itemView.findViewById(R.id.txtma_loai_san_pham);
            txtTenLoai = itemView.findViewById(R.id.txtten_loai_san_pham);
            Delete_LSP = itemView.findViewById(R.id.imgDeleteLSP);
        }
    }


    private void dialogUpdateLSP(LoaiSanPham lsp){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_loaisanpham,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenLSP = view.findViewById(R.id.in_updateTenLSP);
        TextInputEditText ed_TenLSP = view.findViewById(R.id.ed_updateTenLSP);
        TextInputLayout in_MaLS = view.findViewById(R.id.in_updateMaLSP);
        TextInputEditText ed_MaLSP = view.findViewById(R.id.ed_updateMaLSP);
        Button UpdateLSP = view.findViewById(R.id.LSP_update);
        Button CancelLSP = view.findViewById(R.id.LSP_Cancelupdtae);

        ed_MaLSP.setText(String.valueOf(lsp.getMaLoai()));
        ed_TenLSP.setText(lsp.getTenLoai());

        ed_TenLSP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    in_TenLSP.setError("Vui lòng nhập tên loại sản phẩm");
                }else{
                    in_TenLSP.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_MaLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in_MaLS.setError("Không được sửa trường này");
            }
        });
        UpdateLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lsp.setTenLoai(ed_TenLSP.getText().toString());

                String TenLSP = ed_TenLSP.getText().toString();

                if(TenLSP.isEmpty()){
                    if(TenLSP.equals("")){
                        in_TenLSP.setError("Vui Lòng Nhập Tên Loại Sản Phẩm");
                    }else{
                        in_TenLSP.setError(null);
                    }
                }else{
                    if(lspdao.update(lsp)){
                        list.clear();
                        list.addAll(lspdao.getDSLoaiSP());
                        notifyDataSetChanged();
                        dialog.dismiss();
                        Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, "Update không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        CancelLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
    }

}
