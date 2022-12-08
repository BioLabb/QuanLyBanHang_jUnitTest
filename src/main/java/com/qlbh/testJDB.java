package com.qlbh;

import com.config.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class testJDB {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBC.getCnn();
//        this.id = id;
//        this.lastName = lastName;
//        this.firstName = firstName;
//        this.email = email;
//        this.phone = phone;
//        this.address = address;
//        this.user = user;
//        this.password = password;
//        this.maganer = maganer;
        PreparedStatement stm = connection.prepareStatement("INSERT INTO employees (last_name, first_name, user, password) VALUE(?,?,?,?)");

        stm.setString(1,"tien");
        stm.setString(2,"nguyen");
        stm.setString(3, "sinhtien");
        stm.setString(4,"1234");

        int rs = stm.executeUpdate();
    }

    public static class OrderTable {
        private int id;
        int productID;
        private String productName;
        private int productQuantity;
        private double productPrice;
        private double total;

        public OrderTable(int id, int productID, String productName, int productQuantity, double productPrice, double total) {
            this.id = id;
            this.productID = productID;
            this.productName = productName;
            this.productQuantity = productQuantity;
            this.productPrice = productPrice;
            this.total = total;
        }

        public OrderTable() {
        }

    //    public int getId() {
    //        return id;
    //    }
    //
    //    public void setId(int id) {
    //        this.id = id;
    //    }

        public int getProductID() {
            return productID;
        }

        public void setProductID(int productID) {
            this.productID = productID;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getProductQuantity() {
            return productQuantity;
        }

        public void setProductQuantity(int productQuantity) {
            this.productQuantity = productQuantity;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }
    }
}
