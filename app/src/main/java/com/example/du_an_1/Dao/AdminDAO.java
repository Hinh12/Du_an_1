package com.example.du_an_1.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.Admin;

import java.util.ArrayList;

public class AdminDAO {
    DbHelper dbHelper;
    AdminDAO adminDAO;

    public AdminDAO(Context context){
        dbHelper = new DbHelper(context);
    }
    public ArrayList<Admin> getDSNguoiDung(){
        ArrayList<Admin> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Admin", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Admin(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
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

    public boolean register(String username, String hoten, String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("maAD", username);
            values.put("hoTen", hoten);
            values.put("matKhau", password);

            long check = db.insert("Admin", null, values);
            return check != -1;

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

    public boolean tenDangNhapDaTonTai(String tenDangNhap) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM Admin WHERE maAD =?";
        Cursor cursor = db.rawQuery(query, new String[]{tenDangNhap});
        boolean tonTai = cursor.getCount() > 0;
        cursor.close();
        return tonTai;
    }
    public boolean xoaNguoiDung(Admin admin){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long check = database.delete("Admin","maAD = ?",new String[]{String.valueOf(admin.getMaAD())});
        return check>0;
    }

    public int delete(String maadmin){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Admin where maAD = ?",new String[]{String.valueOf(maadmin)});
        if(cursor.getCount() != 0){
            return -1;
        }

        long check = db.delete("Admin","maAD = ?", new String[]{String.valueOf(maadmin)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }



}
