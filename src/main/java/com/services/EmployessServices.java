package com.services;

import com.config.JDBC;
import project.Employess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployessServices {
    public static void addEmployees(Employess emp) throws SQLException {
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
        PreparedStatement stm = connection.prepareStatement("INSERT INTO employees\n(" +
                "last_name,\n" +
                "first_name,\n" +
                "birth,\n" +
                "email_address,\n" +
                "mobile_phone,\n" +
                "address,\n" +
                "user,\n" +
                "password,\n" +
                "maganer)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?,? )"
        );
        stm.setString(1,emp.getLastName());
        stm.setString(2,emp.getFirstName());
        stm.setDate(3, (Date) emp.getBirth());
        stm.setString(4,emp.getEmail());
        stm.setString(5,emp.getPhone());
        stm.setString(6,emp.getAddress());
        stm.setString(7,emp.getUser());
        stm.setString(8,emp.getPassword());
        stm.setBoolean(9,emp.isMaganer());

        int rs = stm.executeUpdate();
        System.out.println(rs);
    }
}
