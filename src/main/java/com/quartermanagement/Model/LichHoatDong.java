package com.quartermanagement.Model;

public class LichHoatDong {
    private int maHoatDong;
    private String tenHoatDong, startTime, endTime, status;

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

    public LichHoatDong(int mahoatdong, String tenhoatdong, String startTime, String endTime, String status){
        this.maHoatDong = mahoatdong;
        this.tenHoatDong = tenhoatdong;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public LichHoatDong(){};
}
