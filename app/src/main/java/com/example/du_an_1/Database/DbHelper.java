package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLG";
    private static final int DbVersion = 4;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Admin
        // Table Admin
        String tb_Admin= "CREATE TABLE Admin(" +
                "maAD TEXT PRIMARY KEY," +
                "hoTen TEXT NOT NULL," +
                "matKhau TEXT NOT NULL)";
        db.execSQL(tb_Admin);

        // Table nhan vien
        String tb_NhanVien= "CREATE TABLE NhanVien(" +
                "maNV INTEGER PRIMARY KEY ," +
                "hoTen TEXT NOT NULL," +
                "namSinh TEXT NOT NULL," +
                "CCCD TEXT NOT NULL)";
        db.execSQL(tb_NhanVien);

        // Table loai giay
        String tb_LoaiGiay= "CREATE TABLE LoaiGiay(" +
                "maLoai INTEGER PRIMARY KEY ," +
                "tenLoai TEXT NOT NULL)";
        db.execSQL(tb_LoaiGiay);

        // Table giay
        String tb_Giay= "CREATE TABLE Giay(" +
                "maGiay INTEGER PRIMARY KEY ," +
                "tenGiay TEXT NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "maLoai INTEGER REFERENCES LoaiGiay(maLoai))";
        db.execSQL(tb_Giay);

        //Table hoa don
        String tb_HoaDon= "CREATE TABLE HoaDon(" +
                "maHD INTEGER PRIMARY KEY ," +
                "maGiay INTEGER REFERENCES Giay(maGiay)," +
                "soLuong INTEGER NOT NULL," +
                "giaTien INTEGER NOT NULL," +
                "ngay TEXT NOT NULL)";
        db.execSQL(tb_HoaDon);
        db.execSQL("INSERT INTO Admin VALUES ('1','Nguyễn Văn Admin','1'), " +
                "('khachhang1','Nguyen Văn A ','123456')");
        db.execSQL("INSERT INTO Giay VALUES " +
                "('1','Giày đá bóng',200000,1)," +
                "('2','Giày thời trang',250000,2)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists Admin");

            onCreate(db);
        }

    }
}
