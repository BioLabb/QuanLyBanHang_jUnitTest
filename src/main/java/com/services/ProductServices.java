package com.services;

import com.config.JDBC;
import com.project.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<Product> findAll() throws SQLException {
        connection = JDBC.getCnn();

        PreparedStatement smt = connection.prepareStatement("select * from products");
        ResultSet rs = smt.executeQuery();

        ArrayList<Product> productArrayList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("product_name");
            int supplier = rs.getInt("supplier_id");
            double cost= rs.getDouble("standard_cost");
            double price = rs.getDouble("list_price");
            int quantity  = rs.getInt("UnitsInStock");

            productArrayList.add(new Product(id,name,supplier,price,cost,quantity));
        }
        return productArrayList;
    }

//    public static void main(String[] args) throws SQLException {
//        for (Product val: findAll()) {
//            System.out.println(val.getName());
//
//        }
//    }
}
