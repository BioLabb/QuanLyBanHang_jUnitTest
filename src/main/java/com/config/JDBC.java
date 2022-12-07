package com.config;

import java.nio.channels.ConnectionPendingException;
import java.sql.*;

public class JDBC {
    private Connection cnn;
    private String url = "jdbc:mysql://localhost:3306/northwind";
    private String user = "root";
    private String pass = "231409859";

    static {
        // nạp driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getCnn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","NhoxVipFiora2411");
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","admin123");
//
//        PreparedStatement stm = connection.prepareStatement("select user from employees where user = ?");
//        stm.setString(1, "NVanh");
//        ResultSet rs = stm.executeQuery();
//
//    }
}
