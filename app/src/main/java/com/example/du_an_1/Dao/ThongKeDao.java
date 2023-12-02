package com.example.du_an_1.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DbHelper;

public class ThongKeDao {
    DbHelper dbs;

    public ThongKeDao(Context context) {
        dbs = new DbHelper(context);
    }

    public int tongDoanhThu(String ngayBatDau, String ngayKetThuc){
        ngayBatDau = ngayBatDau.replace("/","");
        ngayKetThuc = ngayKetThuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbs.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tongTien) FROM DONHANG WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau,ngayKetThuc});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }else {

            return 0;
        }
    }
    public int tongDonHang(String ngayBatDau, String ngayKetThuc){
        ngayBatDau = ngayBatDau.replace("/","");
        ngayKetThuc = ngayKetThuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbs.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT COUNT(madonhang) FROM DONHANG WHERE substr(ngaydathang,7) || substr(ngaydathang,4,2) || substr(ngaydathang,1,2) BETWEEN ? AND ?", new String[]{ngayBatDau,ngayKetThuc});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }else {
            return 0;
        }
    }

}
