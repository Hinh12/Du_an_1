package com.example.du_an_1.model;

public class GIay {
    private int maGiay;
    private String tenGIay;
    private int trangThai;
    private int giaTien;
    private String tenLoai;

    public GIay() {
    }

    public GIay(int maGiay, String tenGIay, int trangThai, int giaTien, String tenLoai) {
        this.maGiay = maGiay;
        this.tenGIay = tenGIay;
        this.trangThai = trangThai;
        this.giaTien = giaTien;
        this.tenLoai = tenLoai;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGIay() {
        return tenGIay;
    }

    public void setTenGIay(String tenGIay) {
        this.tenGIay = tenGIay;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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
