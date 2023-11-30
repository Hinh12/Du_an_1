package com.example.du_an_1.model;

public class Admin {
    private String maAD;
    private String hoTen;
    private String matKhau;
    private String loaiTK;

    public Admin() {
    }

    public Admin(String maAD, String hoTen, String matKhau, String loaiTK) {
        this.maAD = maAD;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.loaiTK = loaiTK;
    }

    public String getMaAD() {
        return maAD;
    }

    public void setMaAD(String maAD) {
        this.maAD = maAD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }
}
