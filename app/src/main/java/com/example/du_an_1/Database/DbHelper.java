package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLG";
    private static final int DbVersion = 3;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Admin
        String  tb_Admin = ("CREATE TABLE Admin (" +
                "maAD TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL)");
        db.execSQL(tb_Admin);

        // Bang loai giay
        String tb_LoaiGiay= "CREATE TABLE LoaiGiay(" +
                "maLoai TEXT PRIMARY KEY AUTOINCREMENT," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(tb_LoaiGiay);

        // Bang giay
        String tb_Giay= "CREATE TABLE Giay(" +
                "maGiay TEXT PRIMARY KEY AUTOINCREMENT," +
                "tenGiay TEXT NOT NULL," +
                "size INTEGER NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "maLoai TEXT REFERENCES LoaiGiay(maLoai))";
        db.execSQL(tb_Giay);

        // Bang nhan vien
        String tb_NhanVien= "CREATE TABLE NhanVIen(" +
                "maNV TEXT PRIMARY KEY AUTOINCREMENT," +
                "hoTenNV TEXT NOT NULL," +
                "namSinh TEXT NOT NULL," +
                "CCCD TEXT NOT NULL)";
        db.execSQL(tb_NhanVien);

        // Bang hoa don
        String tb_HoaDon= "CREATE TABLE HoaDon(" +
                "maHD TEXT PRIMARY KEY AUTOINCREMENT," +
                "maGiay TEXT REFERENCES Giay(maGiay)," +
                "tenGiay TEXT REFERENCES Giay(tenGiay)," +
                "soLuong TEXT NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "ngaY TEXT NOT NULL)";
        db.execSQL(tb_HoaDon);


        //insert date
        db.execSQL("INSERT INTO Admin VALUES ('admin','Nguyễn Văn Admin','admin'), " +
                "('admin1','Nguyen Văn A ','123456')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS Admin");
            db.execSQL("DROP TABLE IF EXISTS LoaiGiay");
            db.execSQL("DROP TABLE IF EXISTS Giay");
            db.execSQL("DROP TABLE IF EXISTS HoaDon");
            db.execSQL("DROP TABLE IF EXISTS NhanVien");

            onCreate(db);
        }

    }
}
