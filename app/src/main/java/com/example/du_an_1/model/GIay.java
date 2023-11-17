package com.example.du_an_1.model;

public class GIay {
    private String maGiay;
    private String tenGIay;
    private int giaTien;
    private String maLoai;

    public GIay() {
    }

    public GIay(String maGiay, String tenGIay, int giaTien, String maLoai) {
        this.maGiay = maGiay;
        this.tenGIay = tenGIay;
        this.giaTien = giaTien;
        this.maLoai = maLoai;
    }

    public String getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(String maGiay) {
        this.maGiay = maGiay;
    }

    public String getTenGIay() {
        return tenGIay;
    }

    public void setTenGIay(String tenGIay) {
        this.tenGIay = tenGIay;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
}
