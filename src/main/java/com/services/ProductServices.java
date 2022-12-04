package com.services;

import com.config.JDBC;
import project.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductServices {
    private static Connection connection;

    public static Product findProductById(int id) throws SQLException {
        connection = JDBC.getCnn();
        String query = "select * from products where ?";
        PreparedStatement stm = connection.prepareStatement("select * from products where id = ?");
        stm.setInt(1,id);

        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            int productId = rs.getInt("id");
            String productName = rs.getString("product_name");
            double productPrice = rs.getDouble("list_price");
            return new Product(productId,productName,productPrice);
        }

        return null;
    }

//    public static void main(String[] args) throws SQLException {
//        Product product = findProductById(1);
//        if(product == null){
//            System.out.println("ko tim thay");
//        }
//        else
//            System.out.println(product.getId());
//    }
}
