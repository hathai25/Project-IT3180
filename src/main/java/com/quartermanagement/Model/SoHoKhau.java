package com.quartermanagement.Model;

public class SoHoKhau {
    private int maHoKhau, maChuHo;
    private String diaChi, ngayLap, ngayChuyenDi, lyDoChuyen;

    public SoHoKhau() {
    }


    // constructor
    public SoHoKhau(int maHoKhau, String diaChi, int maChuHo, String ngayLap, String ngayChuyenDi, String lyDoChuyen) {
        this.maHoKhau = maHoKhau;
        this.maChuHo = maChuHo;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.ngayChuyenDi = ngayChuyenDi;
        this.lyDoChuyen = lyDoChuyen;
    }

    public int getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public int getMaChuHo() {
        return maChuHo;
    }

    public void setMaChuHo(int maChuHo) {
        this.maChuHo = maChuHo;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(String ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getNgayChuyenDi() {
        return ngayChuyenDi;
    }

    public void setNgayChuyenDi(String ngayChuyenDi) {
        this.ngayChuyenDi = ngayChuyenDi;
    }

    public String getLyDoChuyen() {
        return lyDoChuyen;
    }

    public void setLyDoChuyen(String lyDoChuyen) {
        this.lyDoChuyen = lyDoChuyen;
    }
}
