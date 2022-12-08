package com.services;

import com.config.JDBC;
import com.project.OrderDetails;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsServices {

    public static void addOrderDetail(OrderDetails orderDetails) throws SQLException {
        Connection connection = JDBC.getCnn();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO order_details\n(" +
                "product_id,\n" +
                "quantity,\n" +
                "unit_price,\n" +
                "date_allocated)\n" +
                "VALUES(?, ?, ?, ?)");
        statement.setInt(1,orderDetails.getProductId());
        statement.setInt(2,orderDetails.getQuantity());
        statement.setDouble(3,orderDetails.getPrice());
        statement.setDate(4, orderDetails.getDate());

//        PreparedStatement statement = connection.prepareStatement("INSERT INTO order_details\n(" +
//        "quantity,\n" +
//        "unit_price,\n" +
//        "discount)\n" +
//        "VALUES(?, ?, ?)");
//
//        statement.setInt(1,orderDetails.getQuantity());
//        statement.setDouble(2,orderDetails.getPrice());
//        statement.setDouble(3,orderDetails.getDiscount());
        int rs = statement.executeUpdate();

    }
}
