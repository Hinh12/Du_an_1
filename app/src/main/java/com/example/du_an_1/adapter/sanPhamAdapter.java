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


    public sanPhamAdapter(Context context, ArrayList<SanPham> list,ArrayList<HashMap<String, Object>> listHM ) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        spdao = new sanPhamDAO(context);
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sanpham, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
        LoaiSanPhamDAO loaispdao = new LoaiSanPhamDAO(context);
        LoaiSanPham loaisp = loaispdao.getLoaiSanPhamByID(list.get(position).getMaLoai());
        holder.txtmasp.setText("Mã sản phẩm: " + String.valueOf(list.get(position).getMaGiay()));
        holder.txttensp.setText("Tên sản phẩm: " + list.get(position).getTenGiay());
        holder.txtgiasp.setText("Giá sản phẩm: " + String.valueOf(list.get(position).getGiaTien()));
        holder.txtmaloaisp.setText("Mã loại sản phẩm: " + loaisp.getMaLoai() + "");
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
                        switch (check) {
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
                builder.setNegativeButton("NO", null);
                builder.create().show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        TextView txtmasp, txttensp, txtgiasp, txtmaloaisp, txttenloaisp;
        ImageView delete_sp;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            txtmasp = itemView.findViewById(R.id.txtma_san_pham);
            txttensp = itemView.findViewById(R.id.txt_ten_san_pham);
            txtgiasp = itemView.findViewById(R.id.txtgia_san_pham);
            txtmaloaisp = itemView.findViewById(R.id.txtma_loai_san_pham2);
            delete_sp = itemView.findViewById(R.id.can);
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
        TextInputLayout in_GiaTien = view.findViewById(R.id.in_updateGiaTien);
        TextInputEditText ed_TenSanPham = view.findViewById(R.id.ed_updateTenSP);
        TextInputEditText ed_GiaTien = view.findViewById(R.id.ed_updateGiaTien);
        Spinner spnUpadateSP = view.findViewById(R.id.spnSanPham2);
        Button update = view.findViewById(R.id.SP_update);
        Button cancel = view.findViewById(R.id.SP_Cancel2);

        ed_TenSanPham.setText(sp.getTenGiay());
        ed_GiaTien.setText(String.valueOf(sp.getGiaTien()));

        ed_TenSanPham.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    in_TenSP.setError("Vui lòng không để trống tên sản phẩm");
                    return;
                } else {
                    in_TenSP.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ed_GiaTien.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    in_GiaTien.setError("Vui lòng không để trống giá tiền");
                    return;
                } else {
                    in_GiaTien.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


            SimpleAdapter simpleAdapter = new SimpleAdapter(
                    context,
                    listHM,
                    android.R.layout.simple_list_item_1,
                    new String[]{"tenLoai"},
                    new int[]{android.R.id.text1}
            );
            spnUpadateSP.setAdapter(simpleAdapter);

            int index = 0;
            int position = -1;

            for (HashMap<String, Object> item : listHM) {
                if ((int) item.get("maLoai") == sp.getMaLoai()) {
                    position = index;
                    break;  // Thêm break để thoát khỏi vòng lặp khi tìm thấy vị trí
                }
                index++;
            }
            spnUpadateSP.setSelection(position);



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String tensanpham = ed_TenSanPham.getText().toString();
                    String giasanpham = ed_GiaTien.getText().toString();
                    HashMap<String, Object> hs = (HashMap<String, Object>) spnUpadateSP.getSelectedItem();
                    int maloaisp = (int) hs.get("maLoai");

                    if (tensanpham.isEmpty() || giasanpham.isEmpty()) {
                        if (tensanpham.equals("")) {
                            ed_TenSanPham.setError("Vui lòng không để trống tên sản phẩm");
                        } else {
                            ed_GiaTien.setError(null);
                        }
                        if (giasanpham.equals("")) {
                            ed_TenSanPham.setError("Vui lòng không để trống giá sản phẩm");
                        } else {
                            ed_GiaTien.setError(null);
                        }
                    } else {
                        try {
                            int tien = Integer.parseInt(giasanpham);
                            if (tien <= 0) {
                                ed_GiaTien.setError("Giá sản phẩm phải lớn hơn 0");
                            } else {
                                ed_GiaTien.setError(null);
                                boolean check = spdao.update(sp.getMaGiay(), tensanpham, tien, maloaisp);

                                if (check) {
                                    list.clear();
                                    list = spdao.getDSSanPham();
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Cập nhật thành công sách", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(context, "Cập nhật không thành công sách", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (NumberFormatException e) {
                            ed_GiaTien.setError("Giá sản phẩm phải là số");
                        }
                    }
                }

            });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }

//    private void loadData(){
//        list.clear();
//        list = dao.getDSSanPham();
//        notifyDataSetChanged();
//    }


}
