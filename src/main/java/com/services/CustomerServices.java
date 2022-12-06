package com.services;

import com.config.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerServices {

    void add() throws SQLException {
        Connection connection = JDBC.getCnn();

        PreparedStatement stm = connection.prepareStatement("" +
                "INSERT INTO customers\n" +
                "()\n" +
                "VALUES\n" +
                "();");
    }
}
