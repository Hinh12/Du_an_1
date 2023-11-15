package com.example.du_an_1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QLG";
    private static final int DbVersion = 2;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tao bang Admin
        String  tb_Admin = ("CREATE TABLE Admin (" +
                "maAD TEXT PRIMARY KEY, " +
                "hoTen TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL," +
                "Loaitaikhoan TEXT NOT NULL)");
        db.execSQL(tb_Admin);

        //insert date
        db.execSQL("INSERT INTO Admin VALUES ('admin1','Nguyễn Văn Admin','admin','Admin'), " +
                "('khachhang1','Nguyen Văn A ','123456','khachhang')");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
            db.execSQL("drop table if exists Admin");

            onCreate(db);
        }

    }
}
