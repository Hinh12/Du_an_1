package com.example.du_an_1.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import androidx.constraintlayout.helper.widget.MotionEffect;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.DonHang;

import java.util.ArrayList;

public class DonHangDAO {
    private DbHelper dbHelper;
    public DonHangDAO(Context context){
        this.dbHelper= new DbHelper(context);
    }

    public ArrayList<DonHang> getDSDonHang(){
        ArrayList<DonHang> list= new ArrayList<>();
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        try{
            Cursor cursor= database.rawQuery("SELECT DonHang.maDonHang, Admin.maAD, Admin.hoTen, DonHang.ngayDatHang, DonHang.tongTien from DonHang, Admin where DonHang.maAD = Admin.maAD", null);
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                do {
                    DonHang donHang= new DonHang();
                    donHang.setMaDonHang(cursor.getInt(0));
                    donHang.setMaAD(cursor.getString(1));
                    donHang.setHoTen(cursor.getString(2));
                    donHang.setNgayDatHang(cursor.getString(3));
                    donHang.setTongTien(cursor.getInt(4));
                    list.add(donHang);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d(TAG, "Loi", e);
        }
        return list;
    }

    public boolean xoaDonHang(DonHang donHang){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        long check = sqLiteDatabase.delete("DONHANG","maDonHang = ?",new String[]{String.valueOf(donHang.getMaDonHang())});
        return check >0;

    }
    public boolean updateDonHang(DonHang donHang) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maAD", donHang.getMaAD());
        values.put("ngayDatHang", donHang.getNgayDatHang());
        values.put("tongTien", donHang.getTongTien());

        long check = sqLiteDatabase.update("DonHang", values, "maDonHang = ?", new String[]{String.valueOf(donHang.getMaDonHang())});
        return check > 0;
    }
    public int insertDonHang(DonHang donHang){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maAD",donHang.getMaAD());
        values.put("ngayDatHang",donHang.getNgayDatHang());
        values.put("tongTien", donHang.getTongTien());

        try {
            long check = db.insert("DonHang",null,values);
            db.close();

            // Kiểm tra xem đơn hàng đã được chèn thành công hay không
            if (check > 0) {
                return (int) check; // Trả về ID của đơn hàng nếu thành công
            } else {
                return -1; // Trả về -1 nếu có lỗi khi chèn đơn hàng
            }
        } catch (Exception e) {
            Log.e(MotionEffect.TAG, "Lỗi khi chèn đơn hàng", e);
            return -1; // Trả về -1 nếu có lỗi khi chèn đơn hàng
        }


    }
}
