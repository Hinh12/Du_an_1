package com.example.du_an_1.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SanPham implements Parcelable {
    private int maGiay;
    private String tenGiay;
    private int maLoai;
    private int giaTien;
    private String tenLoai;
    private int soLuong;

    public SanPham() {
    }

//    public SanPham(int maGiay, String tenGiay, int maLoai, int giaTien, String tenLoai) {
//        this.maGiay = maGiay;
//        this.tenGiay = tenGiay;
//        this.maLoai = maLoai;
//        this.giaTien = giaTien;
//        this.tenLoai = tenLoai;
//    }


    public SanPham(int maGiay, String tenGiay, int giaTien, int maLoai, int soLuong) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.giaTien = giaTien;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
    }

    public SanPham(int maGiay, String tenGiay, int giaTien, int maLoai ) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.giaTien = giaTien;
        this.maLoai = maLoai;

    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public SanPham(Parcel in) {
        // Đọc dữ liệu từ Parcel và đặt vào các thuộc tính

        maGiay = in.readInt();
        tenGiay = in.readString();
        giaTien = in.readInt();
        maLoai = in.readInt();
        tenLoai = in.readString();
//        masanpham = in.readInt();
//        tensanpham = in.readString();
//        gia = in.readInt();
//        maloaisanpham = in.readInt();
//        tenloaisanpham = in.readString();
//        mota = in.readString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(maGiay);
        parcel.writeString(tenGiay);
        parcel.writeInt(giaTien);
        parcel.writeInt(maLoai);
        parcel.writeString(tenLoai);
    }
    public static final Creator<SanPham> CREATOR = new Creator<SanPham>(){
        @Override
        public SanPham createFromParcel(Parcel parcel) {
            return new SanPham(parcel);
        }

        @Override
        public SanPham[] newArray(int i) {
            return new SanPham[i];
        }
    };
}
