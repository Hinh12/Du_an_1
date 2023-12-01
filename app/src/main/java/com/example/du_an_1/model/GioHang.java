package com.example.du_an_1.model;

public class GioHang {
    private int maGioHang;
    private int maGiay;
    private String maAD;
    private int soLuongMua;
    private String tenGiay;
    private int giaTien;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public GioHang() {
    }

    public GioHang(int maGioHang, int maGiay, String maAD, int soLuongMua) {
        this.maGioHang = maGioHang;
        this.maGiay = maGiay;
        this.maAD = maAD;
        this.soLuongMua = soLuongMua;
    }

    public GioHang(int maGiay, String maAD, int soLuongMua) {
        this.maGiay = maGiay;
        this.maAD = maAD;
        this.soLuongMua = soLuongMua;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public int getMaGiay() {
        return maGiay;
    }

    public void setMaGiay(int maGiay) {
        this.maGiay = maGiay;
    }

    public String getMaAD() {
        return maAD;
    }

    public void setMaAD(String maAD) {
        this.maAD = maAD;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
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
}
