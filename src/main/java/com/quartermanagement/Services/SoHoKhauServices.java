package com.quartermanagement.Services;

import java.sql.*;

import static com.quartermanagement.Constants.DBConstants.*;

public class SoHoKhauServices {
    public static int getTotalSoHoKhau() {
        int total = 0;
        String GET_QUERY = "SELECT COUNT(*) FROM sohokhau";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(GET_QUERY);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                total = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
