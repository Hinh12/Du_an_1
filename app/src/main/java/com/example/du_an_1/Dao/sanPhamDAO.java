package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.GIay;

import java.util.ArrayList;

public class sanPhamDAO {
    DbHelper dbHelper;
    public sanPhamDAO(Context context){
        dbHelper= new DbHelper(context);
    }
    public ArrayList<GIay> getGiayAll(){
        ArrayList<GIay> list= new ArrayList<>();
        SQLiteDatabase db= dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery("select g.maGiay, g.tenGiay, g.giaTien, lg.maLoai, lg.tenLoai from Giay g, LoaiGIay lg where g.maLoai = lg.maLoai", null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new GIay(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
