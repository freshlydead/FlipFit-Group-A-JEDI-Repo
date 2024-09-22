package com.flipkart.utils;

import java.sql.*;

public class dbutils {
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/flipfitDB";
    private static final String user = "root";
    private static final String password = "Kanak@1234";
    public static Connection getConnection() throws SQLException {
        return connection = DriverManager.getConnection(url, user, password);
    }

    public static int getTableCnt(String table){
        int count = 0;
        String query = "SELECT COUNT(*) AS cnt FROM " + table;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            if (resultSet.next()) {
                count = resultSet.getInt("cnt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions (e.g., log them or rethrow them)
        }

        return count;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
