package com.example.du_an_1.model;

public class DonHangChiTiet {
    private int maChiTietDonHang;
    private int maDonHang;
    private int maGiay;
    private String tenGiay;
    private int soLuong;
    private int donGia;
    private int thanhTien;
    private  String anhsp;


    public DonHangChiTiet() {
    }

    public DonHangChiTiet(int maDonHang, int maGiay, int soLuong, int donGia, int thanhTien) {
        this.maDonHang = maDonHang;
        this.maGiay = maGiay;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public DonHangChiTiet(int maChiTietDonHang, int maDonHang, int maGiay, String tenGiay, int soLuong, int donGia, int thanhTien) {
        this.maChiTietDonHang = maChiTietDonHang;
        this.maDonHang = maDonHang;
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }


    public String getAnhsp() {
        return anhsp;
    }

    public void setAnhsp(String anhsp) {
        this.anhsp = anhsp;
    }

    public int getMaChiTietDonHang() {
        return maChiTietDonHang;
    }

    public void setMaChiTietDonHang(int maChiTietDonHang) {
        this.maChiTietDonHang = maChiTietDonHang;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(int maDonHang) {
        this.maDonHang = maDonHang;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
