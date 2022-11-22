package com.quartermanagement.Model;

public class LichHoatDong {
    private int maHoatDong;
    private String tenHoatDong;
    private String startTime;
    private String endTime;
    private String status;

    private String maNguoiTao;

    public int getMaHoatDong() {
        return maHoatDong;
    }

    public void setMaHoatDong(int mahoatdong) {
        this.maHoatDong = mahoatdong;
    }

    public String getTenHoatDong() {
        return tenHoatDong;
    }

    public void setTenHoatDong(String tenhoatdong) {
        this.tenHoatDong = tenhoatdong;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaNguoiTao() {
        return maNguoiTao;
    }

    public void setMaNguoiTao(String maNguoiTao) {
        this.maNguoiTao = maNguoiTao;
    }

    public LichHoatDong(int mahoatdong, String tenhoatdong, String startTime, String endTime, String status, String maNguoiTao){
        this.maHoatDong = mahoatdong;
        this.tenHoatDong = tenhoatdong;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.maNguoiTao = maNguoiTao;
    }

    public LichHoatDong(){};
}
