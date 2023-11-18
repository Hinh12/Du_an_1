package com.example.du_an_1.model;

public class SanPham {
    private int maGiay;
    private String tenGiay;
    private int maLoai;
    private int giaTien;
    private String tenLoai;

    public SanPham() {
    }

//    public SanPham(int maGiay, String tenGiay, int maLoai, int giaTien, String tenLoai) {
//        this.maGiay = maGiay;
//        this.tenGiay = tenGiay;
//        this.maLoai = maLoai;
//        this.giaTien = giaTien;
//        this.tenLoai = tenLoai;
//    }

    public SanPham(int maGiay, String tenGiay, int giaTien, int maLoai ) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.giaTien = giaTien;
        this.maLoai = maLoai;

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
}
