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
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.LoaiSanPhamDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.Frame.HomeFragment;
import com.example.du_an_1.R;
import com.example.du_an_1.model.LoaiSanPham;
import com.example.du_an_1.model.SanPham;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class sanPhamAdapter extends RecyclerView.Adapter<sanPhamAdapter.ViewHoler> {
    private ArrayList<SanPham> list;
    private Context context;
    private ArrayList<HashMap<String, Object>> listHM;
    sanPhamDAO spdao;


    public sanPhamAdapter(Context context, ArrayList<SanPham> list) {
        this.context = context;
        this.list = list;
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
        LoaiSanPhamDAO loaispdao = new LoaiSanPhamDAO(context);
        LoaiSanPham loaisp = loaispdao.getLoaiSanPhamByID(list.get(position).getMaLoai());
        holder.txtmasp.setText("Mã sản phẩm: "+String.valueOf(list.get(position).getMaGiay()));
        holder.txttensp.setText("Tên sản phẩm: "+ list.get(position).getTenGiay());
        holder.txtgiasp.setText("Giá sản phẩm: "+String.valueOf(list.get(position).getGiaTien()));
        holder.txtmaloaisp.setText("Mã loại sản phẩm: "+ loaisp.getMaLoai()+"");
        SanPham sp = list.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                dialogAddGiay(sp);
                return true;
            }
        });

        holder.delete_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa sản phẩm này không!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spdao = new sanPhamDAO(context);
                        int check = spdao.delete(list.get(holder.getAdapterPosition()).getMaGiay());
                        switch (check){
                            case 1:
                              //  loadData();
                                Toast.makeText(context, "Xóa thành công sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "Xóa không thành sản phẩm", Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context, "Không xóa được sản phẩm này vì đang còn tồn tại trong Hóa đơn", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{

        TextView txtmasp, txttensp, txtgiasp, txtmaloaisp, txttenloaisp;
        ImageView delete_sp;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmasp= itemView.findViewById(R.id.txtma_san_pham);
            txttensp= itemView.findViewById(R.id.txt_ten_san_pham);
            txtgiasp= itemView.findViewById(R.id.txtgia_san_pham);
            txtmaloaisp= itemView.findViewById(R.id.txtma_loai_san_pham2);
            delete_sp= itemView.findViewById(R.id.can);
        }
    }



    private void dialogAddGiay(SanPham sp) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.update_sanpham, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputLayout in_TenSP = view.findViewById(R.id.in_updateTenSP);
        TextInputLayout in_GiaThue = view.findViewById(R.id.in_updateGiaThue);
        TextInputEditText ed_TenSanPham = view.findViewById(R.id.ed_updateTenSP);
        TextInputEditText ed_GiaThue = view.findViewById(R.id.ed_updateGiaThue);
        Spinner spnSP = view.findViewById(R.id.spnSanPham);
        Button update = view.findViewById(R.id.SP_update);
        Button cancel = view.findViewById(R.id.SP_Cancel);

        ed_TenSanPham.setText(sp.getTenGiay());
        ed_GiaThue.setText(String.valueOf(sp.getGiaTien()));

        ed_TenSanPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    in_TenSP.setError("Vui lòng không để trống tên sản phẩm");
                } else {
                    in_TenSP.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_GiaThue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    in_GiaThue.setError("Vui lòng không để trống giá tiền");
                } else {
                    in_GiaThue.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        if (listHM != null && !listHM.isEmpty()) {
            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    context,
                    listHM,
                    android.R.layout.simple_list_item_1,
                    new String[]{"tenLoai"},
                    new int[]{android.R.id.text1}
            );
            spnSP.setAdapter(simpleAdapter);

            int index = 0;
            int position = -1;

            for (HashMap<String, Object> item : listHM) {
                if ((int) item.get("maLoai") == sp.getMaLoai()) {
                    position = index;
                    break;  // Thêm break để thoát khỏi vòng lặp khi tìm thấy vị trí
                }
                index++;
            }
            spnSP.setSelection(position);
        } else {
            // Xử lý khi listHM là null hoặc rỗng
            // Ví dụ: Hiển thị thông báo lỗi, ẩn hoặc vô hiệu hóa spnSP, vv.
        }




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = ed_TenSanPham.getText().toString();
                String checktien = ed_GiaThue.getText().toString();;
                HashMap<String, Object> hs = (HashMap<String, Object>) spnSP.getSelectedItem();
                int maloai = (int) hs.get("maLoai");
                if(tensp.isEmpty() || checktien.isEmpty()){
                    if(tensp.equals("")){
                        in_TenSP.setError("Vui lòng không để trống tên Sản phẩm");
                    }else{
                        in_TenSP.setError(null);
                    }
                    if(checktien.equals("")){
                        in_GiaThue.setError("Vui lòng không để trống giá tiền");
                    }else{
                        in_GiaThue.setError(null);
                    }
                }else{
                    int tien = Integer.parseInt(checktien);
                    boolean check = spdao.update(sp.getMaGiay(),tensp,tien,maloai);
                    if(check){
                       // loadData();
                        Toast.makeText(context, "Cập nhật thành công sản phẩm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        Toast.makeText(context, "Cập nhật không thành công sản phẩm", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed_TenSanPham.setText("");
                ed_GiaThue.setText("");
            }
        });


    }
//    private void loadData(){
//        list.clear();
//        list = dao.getDSSanPham();
//        notifyDataSetChanged();
//    }


}
