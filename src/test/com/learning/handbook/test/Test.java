package com.learning.handbook.test;

import java.sql.*;

public class Test {
    public static void main(String[] args) {
        Connection conn;
        Connection connLocal;
        String sql;
        String url = "jdbc:mysql:///soeasy_dict?user=soeasy&password=&useUnicode=true&characterEncoding=UTF8";
        String urlLocal = "jdbc:mysql://localhost:3306/blog?user=root&password=admin&useUnicode=true&characterEncoding=UTF8";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            sql = "SELECT code,name,status,is_zxs from area_code";
            ResultSet resultSet = stmt.executeQuery(sql);
            connLocal = DriverManager.getConnection(urlLocal);
            Statement stmtLocal = connLocal.createStatement();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3) + "\t" + resultSet.getString(4));
                StringBuilder sqlLocal = new StringBuilder("insert into area_code (code,name,status,is_zxs) VALUES ('")
                        .append(resultSet.getString(1))
                        .append("','")
                        .append(resultSet.getString(2))
                        .append("','")
                        .append(resultSet.getString(3))
                        .append("','")
                        .append(resultSet.getString(4))
                        .append("')");
                System.out.println(stmtLocal.executeUpdate(sqlLocal.toString()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}