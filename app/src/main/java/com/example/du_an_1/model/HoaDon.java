package com.example.du_an_1.model;

public class HoaDon {
    private int maHD;
    private int maGIay;
    private String tenGiay;
    private int soLuong;
    private int giaTien;
    private String ngay;

    public HoaDon(int maHD, int maGIay, String tenGiay, int soLuong, int giaTien, String ngay) {
        this.maHD = maHD;
        this.maGIay = maGIay;
        this.tenGiay = tenGiay;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.ngay = ngay;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaGIay() {
        return maGIay;
    }

    public void setMaGIay(int maGIay) {
        this.maGIay = maGIay;
    }

    public String getTenGiay() {
        return tenGiay;
    }

    public void setTenGiay(String tenGiay) {
        this.tenGiay = tenGiay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }
}
