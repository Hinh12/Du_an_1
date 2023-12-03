package com.example.du_an_1.Frame;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Dao.DonHangChiTietDao;
import com.example.du_an_1.Dao.DonHangDAO;
import com.example.du_an_1.Dao.GioHangDAO;
import com.example.du_an_1.Dao.sanPhamDAO;
import com.example.du_an_1.R;
import com.example.du_an_1.Viewmd.SharedViewModel;
import com.example.du_an_1.adapter.GioHangAdapter;
import com.example.du_an_1.model.DonHang;
import com.example.du_an_1.model.DonHangChiTiet;
import com.example.du_an_1.model.GioHang;
import com.example.du_an_1.model.SanPham;
import com.google.android.material.snackbar.Snackbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NotificationFragment extends Fragment implements GioHangAdapter.TotalPriceListener{
    private ArrayList<GioHang> list = new ArrayList<>();
    private GioHangAdapter gioHangAdapter;
    RecyclerView rcvGioHang;
    GioHangDAO gioHangDao;
    private DonHangDAO donHangDao;
    private ArrayList<DonHang> listDonHang = new ArrayList<>();
    private SharedViewModel sharedViewModel;
    Button btnmuahang;
    TextView txttongtien;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);


        btnmuahang = view.findViewById(R.id.btnhomemuahang);
        txttongtien = view.findViewById(R.id.txthometongtien);
        rcvGioHang = view.findViewById(R.id.rcvGioHang);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvGioHang.setLayoutManager(layoutManager);
        gioHangAdapter = new GioHangAdapter(list,getContext());
        rcvGioHang.setAdapter(gioHangAdapter);
        gioHangDao = new GioHangDAO(getContext());

        gioHangAdapter.setTotalPriceListener(this);
        DonHangChiTietDao chiTietDao =new DonHangChiTietDao(getContext());
        donHangDao = new DonHangDAO(getContext());

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.getMasp().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (isAdded() && isVisible()) {
                    if (sharedViewModel.getAddToCartClicked().getValue() != null && sharedViewModel.getAddToCartClicked().getValue()) {
                        Boolean addToCartClicked = sharedViewModel.getAddToCartClicked().getValue();
                        updateGioHangByMaSp(integer);
                        sharedViewModel.setAddToCartClicked(true); // Đặt lại trạng thái
                    }
                }
            }
        });

        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (GioHang gioHang : list) {
                    if (gioHang.getSoLuong() == 0) {
                        Toast.makeText(getContext(), "Sản phẩm " + gioHang.getTenGiay() + " đã hết hàng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                String totalAmountString = txttongtien.getText().toString();
                int totalAmount;
                try {
                    totalAmount = Integer.parseInt(totalAmountString);
                } catch (NumberFormatException e) {
                    totalAmount = 0;
                }

//                int totalAmount = Integer.parseInt(txttongtien.getText().toString());
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
                String maad = sharedPreferences.getString("maAD", "");

                LocalDate currentDate = LocalDate.now();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String ngayHienTai = currentDate.format(formatter);
                String hoTen = sharedPreferences.getString("hoTen","");

//                if (totalAmount > 0) {


                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.apply();
                        DonHang donHang = new DonHang(maad, hoTen ,ngayHienTai, totalAmount);

                        int orderId = donHangDao.insertDonHang(donHang);
                        if (orderId != 0){
                            listDonHang.clear();
                            listDonHang.addAll(donHangDao.getDSDonHang());
                            Log.d("tongtien",String.valueOf(txttongtien));
                            if (totalAmount > 0){
                                Log.d("sizeeeeeeeeeee",String.valueOf(list.size()));
                                for (GioHang gioHang : list){
                                    if(gioHang.isSelected()){
                                        sanPhamDAO SPDAO = new sanPhamDAO(getContext());
                                        SanPham sanPham = SPDAO.getSanPhamById(gioHang.getMaGiay());
                                        if (sanPham != null){
                                           DonHangChiTiet donHangChiTiet = new DonHangChiTiet(orderId, gioHang.getMaGiay(), gioHang.getSoLuongMua(), sanPham.getGiaTien(), gioHang.getSoLuongMua() * sanPham.getGiaTien());
                                           chiTietDao.insertDonHangChiTiet(donHangChiTiet);
                                        }else {
                                            Toast.makeText(getContext(), "Sản phẩm không tìm thấy trong cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                            else {
                                Toast.makeText(getContext(), "Vui lòng chọn sản phẩm để thanh toán", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            for (GioHang selected : list) {
                                if (selected.isSelected()) {
                                    gioHangDao.deleteGioHang(selected);
                                }
                            }

                            txttongtien.setText(String.valueOf(0));
                            list = gioHangDao.getDSGioHang();
                            gioHangAdapter.updateCartList(list);
                            displayCart(list);

                            Snackbar.make(getView(), "Thanh toán thành công", Snackbar.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putInt("maDonHang", orderId);

                            ThanhToanFragment frgThanhToan = new ThanhToanFragment();
                            frgThanhToan.setArguments(bundle);
                            FragmentManager fragmentManager = getParentFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.frglayout, frgThanhToan);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        }else {
                            Toast.makeText(getContext(), "Thất bại!", Toast.LENGTH_SHORT).show();
                        }
//                }

            }
        });


        list = gioHangDao.getDSGioHang();
        displayCart(list);

        return view;
    }
    public void updateGioHangByMaSp(int masp) {
        if (masp > 0) {
            ArrayList<GioHang> updatedCartList = gioHangDao.getDSGioHang();
            list.clear();
            list.addAll(updatedCartList);
            gioHangAdapter.notifyDataSetChanged();
            displayCart(updatedCartList);
        }
    }
    private void displayCart(ArrayList<GioHang> cartList) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcvGioHang.setLayoutManager(layoutManager);

        if (gioHangAdapter == null) {
            gioHangAdapter = new GioHangAdapter( cartList,getContext());
            rcvGioHang.setAdapter(gioHangAdapter);

        } else {
            gioHangAdapter.updateCartList(cartList);
            gioHangAdapter.notifyDataSetChanged();
        }
    }

    public void onTotalPriceUpdated(int totalAmount) {
        if (txttongtien != null) {
            txttongtien.setText(String.valueOf(totalAmount));
        }
    }
}