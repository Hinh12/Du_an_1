package com.example.du_an_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdminDAO {
    DbHelper dbHelper;

    public AdminDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    //đăng nhập

    public boolean checkLogin(String maAD,String matKhau) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Admin WHERE maAD=? AND matKhau=?",new String[]{maAD, matKhau});
        if (cursor.getCount()  != 0){
            return true;
        }
        return false;
    }

    public boolean updatePass(String username, String oldPass, String newPass){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Admin where maAD = ? and matKhau = ?", new String[]{username,oldPass});
        if (cursor.getCount() > 0){
            ContentValues values = new ContentValues();
            values.put("matKhau", newPass);
            long check = db.update("Admin",values,"maAD = ?",new String[]{username});
            if (check == -1){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }



}
