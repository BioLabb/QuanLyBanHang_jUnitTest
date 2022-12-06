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
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","admin123");
    }

//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//        Class.forName("com.mysql.cj.jdbc.Driver");
//
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/northwind","root","admin123");
//
//        Statement statement = connection.createStatement();
//        ResultSet rs = statement.executeQuery("Select * from customers");
//
//        while (rs.next()){
//            int id = rs.getInt("id");
//            String lastName = rs.getString("last_name");
//            System.out.println(id + " " + lastName);
//        }
//
//
//    }
}
