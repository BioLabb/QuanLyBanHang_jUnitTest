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
        System.out.println(rs);
    }
}
