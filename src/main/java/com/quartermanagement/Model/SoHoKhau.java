package com.quartermanagement.Model;

public class SoHoKhau {
    private String MaHoKhau;
    private String MaChuHo, DiaChi;

    // constructor
    public SoHoKhau(String MaChuHo, String diaChi, String maHoKhau) {
        MaHoKhau = maHoKhau;
        this.MaChuHo = MaChuHo;
        DiaChi = diaChi;
    }

    // Getter and setter


    public String getMaHoKhau() {
        return MaHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
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
