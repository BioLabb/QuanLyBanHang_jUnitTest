package com.qlbh;

import com.config.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class testdt {
    public List<dt> getdt(){
        List<dt> results = new ArrayList<>();
        try(Connection conn = JDBC.getCnn()) {

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT  * FROM dt");

            while (rs.next())
            {
                dt c = new dt(rs.getInt("ID"), rs.getString("Mã Sản Phẩm"), rs.getString("Tên Sản Phẩm"), rs.getInt("Số Lượng"), rs.getString("Đơn vị"), rs.getBigDecimal("Giá"), rs.getBigDecimal("Thành Tiền"), rs.getBigDecimal("Mua"), rs.getDate("Date"));
                results.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
