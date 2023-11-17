package com.example.du_an_1.model;

public class HoaDon {
    private String maHD;
    private String maGIay;
    private String tenGiay;
    private int soLuong;
    private int giaTien;
    private String ngay;

    public HoaDon() {
    }

    public HoaDon(String maHD, String maGIay, String tenGiay, int soLuong, int giaTien, String ngay) {
        this.maHD = maHD;
        this.maGIay = maGIay;
        this.tenGiay = tenGiay;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.ngay = ngay;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaGIay() {
        return maGIay;
    }

    public void setMaGIay(String maGIay) {
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
