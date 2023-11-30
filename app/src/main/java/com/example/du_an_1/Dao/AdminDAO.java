package com.example.du_an_1.Dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.Admin;

import java.util.ArrayList;

public class AdminDAO {
    private final DbHelper dbHelper;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public AdminDAO(Context context){
        this.dbHelper = new DbHelper(context);

        if (context != null){
            sharedPreferences= context.getSharedPreferences("USER_FILE", context.MODE_PRIVATE);
        }else {
            Log.e(TAG, "Context is null in USER_FILE contructor");
        }
    }
    public ArrayList<Admin> getDSNguoiDung(){
        ArrayList<Admin> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Admin", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Admin(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    //đăng nhập

    public boolean checkLogin(String maAD,String matKhau) {
        Log.d(TAG, "CheckDangNhap: " + maAD + " - " + matKhau);
        SQLiteDatabase database= dbHelper.getReadableDatabase();
        try{
            Cursor cursor = database.rawQuery("SELECT * FROM Admin WHERE maAD = ? AND matKhau = ?", new String[]{maAD, matKhau});
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                editor= sharedPreferences.edit();
                editor.putString("maAD", cursor.getString(0));
                editor.putString("hoTen", cursor.getString(1));
                editor.putString("matKhau", cursor.getString(2));
                editor.putString("loaiTK", cursor.getString(3));
                editor.commit();
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            Log.e(TAG, "Lỗi kiểm tra đăng nhập", e);
            return false;
        }
    }

    public boolean checkDangKy(Admin admin){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("maAD", admin.getMaAD());
        values.put("hoTen", admin.getHoTen());
        values.put("matKhau", admin.getMatKhau());
        values.put("loaiTK", admin.getLoaiTK());
        long result= db.insert("Admin", null, values);
        return result != -1;
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
