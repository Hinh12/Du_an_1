package com.example.du_an_1.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.model.SanPham;

import java.util.ArrayList;

public class sanPhamDAO {
    DbHelper dbHelper;
    public sanPhamDAO(Context context){
        dbHelper= new DbHelper(context);
    }

    public ArrayList<SanPham> getDSSanPham(){
        ArrayList<SanPham> list = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Giay", null);
//        Cursor cursor = db.rawQuery("select sp.maGiay, sp.tenGiay, sp.giaTien, lsp.maLoai,lsp.tenLoai, sp.soLuong from Giay sp, LoaiGiay lsp where sp.maLoai = lsp.maLoai",null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new SanPham(cursor.getInt(0),cursor.getString(1),cursor.getInt(2), cursor.getInt(3),cursor.getInt(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }


    public boolean insert(String tenGiay, int giaTien, int maloai, int soLuong, String anh){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenGiay",tenGiay);
        values.put("giaTien",giaTien);
        values.put("maLoai",maloai);
        values.put("soLuong",soLuong);
        values.put("anh", anh);
        long check = db.insert("Giay",null,values);
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean update(int maGiay, String tenGiay, int giaTien, int maloai, int soLuong, String anh){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenGiay",tenGiay);
        values.put("giaTien",giaTien);
        values.put("maLoai",maloai);
        values.put("soLuong",soLuong);
        values.put("anh", anh);
        long check = db.update("Giay",values,"maGiay = ?", new String[]{String.valueOf(maGiay)});
        if(check == -1){
            return false;
        }else{
            return true;
        }
    }


    public int delete(int maGiay){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from HoaDon where maGiay = ?",new String[]{String.valueOf(maGiay)});
        if(cursor.getCount() != 0){
            return -1;
        }

        long check = db.delete("Giay","maGiay = ?", new String[]{String.valueOf(maGiay)});
        if(check == -1){
            return 0;
        }else{
            return 1;
        }
    }


    public boolean updateSlSanPham(int maSanPham, int newQuantity) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("soLuong", newQuantity);

        // Đảm bảo rằng điều kiện WHERE sử dụng mã sản phẩm đúng
        String whereClause = "maGiay = ?";
        String[] whereArgs = {String.valueOf(maSanPham)};

        // Thực hiện cập nhật
        int rowsAffected = database.update("Giay", values, whereClause, whereArgs);

        // Trả về true nếu có ít nhất một hàng bị ảnh hưởng
        return rowsAffected > 0;
    }






    private static final String COL_MASP = "maGiay";
    private static final String COL_TENSP = "tenGiay";
    private static final String COL_GIA = "giaTien";
    private static final String COL_MALOAI = "maLoai";
    private static final String COL_SOLUONG = "soLuong";
    private static final String COL_ANH = "anh";

    @SuppressLint("Range")
    public SanPham getSanPhamById(int masanpham) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        SanPham sanPham = null;

        String[] columns = {COL_MASP, COL_TENSP, COL_GIA, COL_MALOAI, COL_SOLUONG, COL_ANH};
        String selection = COL_MASP + "=?";
        String[] selectionArgs = {String.valueOf(masanpham)};

        Cursor cursor = database.query("Giay", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int maSanPham = cursor.getInt(cursor.getColumnIndex(COL_MASP));
            String tenSanPham = cursor.getString(cursor.getColumnIndex(COL_TENSP));
            int gia = cursor.getInt(cursor.getColumnIndex(COL_GIA));
            int maLoaiSanPham = cursor.getInt(cursor.getColumnIndex(COL_MALOAI));
            int soLuong = cursor.getInt(cursor.getColumnIndex(COL_SOLUONG));
            String anh = cursor.getString(cursor.getColumnIndex(COL_ANH));
            sanPham = new SanPham(maSanPham, tenSanPham, gia, maLoaiSanPham, soLuong, anh);
        }

        cursor.close();
        return sanPham;
    }


}
