package com.example.du_an_1.Dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.DonHangChiTiet;

import java.util.ArrayList;


public class DonHangChiTietDao {
    private DbHelper dbHelper;

    public DonHangChiTietDao(Context context) {
        this.dbHelper = new DbHelper(context);
    }



    public ArrayList<DonHangChiTiet> getChiTietDonHangByMaDonHang(int maDonHang) {
        ArrayList<DonHangChiTiet> listChiTiet = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        try {
            String query = "SELECT ChiTietDonHang.maChiTietDonHang, ChiTietDonHang.maGiay, Giay.tenGiay, DonHang.maDonHang, ChiTietDonHang.soLuong, ChiTietDonHang.donGia, ChiTietDonHang.thanhTien\n" +
                    "FROM ChiTietDonHang\n" +
                    "INNER JOIN DonHang ON ChiTietDonHang.maDonHang = DonHang.maDonHang\n" +
                    "INNER JOIN Giay ON ChiTietDonHang.maGiay = Giay.maGiay\n" +
                    "WHERE DonHang.maDonHang = ?";

            Cursor cursor = database.rawQuery(query, new String[]{String.valueOf(maDonHang)});

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    DonHangChiTiet chiTietDonHang = new DonHangChiTiet();
                    chiTietDonHang.setMaChiTietDonHang(cursor.getInt(0));
                    chiTietDonHang.setMaGiay(cursor.getInt(1));
                    chiTietDonHang.setTenGiay(cursor.getString(2));
                    chiTietDonHang.setMaDonHang(cursor.getInt(3));
                    chiTietDonHang.setSoLuong(cursor.getInt(4));
                    chiTietDonHang.setDonGia(cursor.getInt(5));
                    chiTietDonHang.setThanhTien(cursor.getInt(6));
                    listChiTiet.add(chiTietDonHang);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi", e);
        } finally {
            database.close();
        }
        return listChiTiet;
    }


    public boolean xoaDonHang(DonHangChiTiet donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("ChiTietDonHang", "maChiTietDonHang = ?", new String[]{String.valueOf(donHang.getMaChiTietDonHang())});
        return check > 0;

    }

    public boolean insertDonHangChiTiet(DonHangChiTiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDonHang", donHangChiTiet.getMaDonHang());
        values.put("maGiay", donHangChiTiet.getMaGiay());
        values.put("soLuong", donHangChiTiet.getSoLuong());
        values.put("donGia", donHangChiTiet.getDonGia());
        values.put("thanhTien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.insert("ChiTietDonHang", null, values);
        return check > 0;
    }

    public boolean updateDonHangChiTiet(DonHangChiTiet donHangChiTiet) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maDonHang", donHangChiTiet.getMaDonHang());
        values.put("maGiay", donHangChiTiet.getMaGiay());
        values.put("soLuong", donHangChiTiet.getSoLuong());
        values.put("donGia", donHangChiTiet.getDonGia());
        values.put("thanhTien", donHangChiTiet.getThanhTien());

        long check = sqLiteDatabase.update("ChiTietDonHang", values, "machitietdonhang = ?", new String[]{String.valueOf(donHangChiTiet.getMaChiTietDonHang())});
        return check > 0;
    }


}
