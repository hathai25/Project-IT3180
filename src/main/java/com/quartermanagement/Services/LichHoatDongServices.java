package com.quartermanagement.Services;

import com.quartermanagement.Model.LichHoatDong;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LichHoatDongServices {
    public static String getNamebyID(Connection conn, int id) throws SQLException {
        String query = "SELECT HoTen FROM nhankhau WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getString("HoTen");
    }

    public static ResultSet getAllPeople(Connection conn) throws SQLException {
        String query = "SELECT HoTen FROM nhankhau";
        PreparedStatement ps = conn.prepareStatement(query);
        return ps.executeQuery();
    }

    public static ResultSet getCoSoVatChatFromLichHoatDong(Connection conn, LichHoatDong lichHoatDong) throws SQLException {
        String query = "SELECT csvc.TenDoDung, hdcsvc.SoLuong " +
                "FROM cosovatchat csvc, hoatdong_cosovatchat hdcsvc " +
                "WHERE csvc.MaDoDung = hdcsvc.MaDoDung AND hdcsvc.MaHoatDong = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1,lichHoatDong.getMaHoatDong());
        System.out.println(ps);
        return ps.executeQuery();
    }


}
