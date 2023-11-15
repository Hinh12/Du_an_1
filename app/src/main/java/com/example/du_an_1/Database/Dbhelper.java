package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {
    public static final String DB_name= "Du_An_1_APP_Ban_Giay";
    public static final int DB_version= 2;
    public Dbhelper (@Nullable Context context){
        super(context, DB_name, null, DB_version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Table Admin
        String tb_Admin= "CREATE TABLE Admin(" +
                "maAD TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL," +
                "loaiTK TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tb_Admin);

        // Table nhan vien
        String tb_NhanVien= "CREATE TABLE NhanVien(" +
                "maNV INTEGER PRIMARY KEY AUTOINCREMENT," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL," +
                "CCCD TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tb_NhanVien);

        // Table loai giay
        String tb_LoaiGiay= "CREATE TABLE LoaiGiay(" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tb_LoaiGiay);

        // Table giay
        String tb_Giay= "CREATE TABLE Giay(" +
                "maGiay INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenGiay TEXT NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "size INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiGiay(maLoai))";
        sqLiteDatabase.execSQL(tb_Giay);

        //Table hoa don
        String tb_HoaDon= "CREATE TABLE HoaDon(" +
                "maHD INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maGiay INTEGER REFERENCES Giay(maGiay)," +
                "tenGiay TEXT REFERENCES Giay(tenGiay)," +
                "soLuong INTEGER NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "ngay TEXT NOT NULL)";
        sqLiteDatabase.execSQL(tb_HoaDon);

        sqLiteDatabase.execSQL("INSERT INTO Admin VALUES('admin', 'Admin', 'admin', 'ad')," +
                "('1', 'PHT', '123', 'kh')");
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!= i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Admin");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NhanVien");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LoaiGiay");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Giay");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HoaDon");
            onCreate(sqLiteDatabase);
        }
    }
}
