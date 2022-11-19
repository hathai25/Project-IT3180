package com.quartermanagement.Model;

public class SoHoKhau {
    private int MaHoKhau;
    private String MaChuHo, DiaChi;

    // constructor
    public SoHoKhau(String MaChuHo, String diaChi, int maHoKhau) {
        MaHoKhau = maHoKhau;
        this.MaChuHo = MaChuHo;
        DiaChi = diaChi;
    }

    // Getter and setter


    public int getMaHoKhau() {
        return MaHoKhau;
    }

    public void setMaHoKhau(int maHoKhau) {
        MaHoKhau = maHoKhau;
    }

    public String getMaChuHo() {
        return MaChuHo;
    }

    public void setMaChuHo(String maChuHo) {
        MaChuHo = maChuHo;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }
}
