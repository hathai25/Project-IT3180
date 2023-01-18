package com.quartermanagement.Services;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static com.quartermanagement.Constants.DBConstants.*;

public class CoSoVatChatServices {
    public static Map<String, Integer> getLeastFiveFacility() {
        Map<String, Integer> result = new HashMap<>();
        String GET_QUERY = "SELECT TenDoDung, SoLuongKhaDung FROM `cosovatchat` ORDER BY SoLuongKhaDung ASC LIMIT 5";
        try {
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(GET_QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
