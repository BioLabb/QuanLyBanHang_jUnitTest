package com.services;

import com.config.JDBC;
import project.Employess;

import java.sql.*;
import java.util.PrimitiveIterator;

public class EmployessServices {
    private static Connection connection;
    private  static Employess emp;
    public static void addEmployees(Employess emp) throws SQLException {
        Connection connection = JDBC.getCnn();
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

        stm.executeUpdate();
    }

    public static Employess findEmployeeByUser(String user) throws SQLException {
        connection = JDBC.getCnn();

        PreparedStatement stm = connection.prepareStatement("select * from employees where user = ?");
        stm.setString(1,user);
        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            int id = rs.getInt("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String phone = rs.getString("mobile_phone");
            String email = rs.getString("email_address");
            emp = new Employess(id,user,lastName,firstName,email,phone);
            return emp;
        }
        return null;
    }

    public static boolean removeById(int id) throws SQLException {
        connection = JDBC.getCnn();

        PreparedStatement stm = connection.prepareStatement("delete from employees where id = ?");
        stm.setInt(1,id);
        int rs = stm.executeUpdate();
        if(rs != 0){
            return true;
        }
        return false;
    }
}
