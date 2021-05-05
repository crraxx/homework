package com.ff.dao;

import java.sql.*;

public class loginDao {
    static Connection connection;
    //连接数据库检查账户或密码是否正确
    public static boolean link(String account, String password) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/school_db?characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
        connection = DriverManager.getConnection(url, "root", "wyf523");
        String sql = "select * from users where user_account = ? and user_password = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, account);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }

    //将用户数据插入数据库
    public static void insert(String account, String ip, String date) throws SQLException {
        String insertSql = "insert into admin(account,ip,login_time)"+"values(?,?,?)";
        PreparedStatement ps = connection.prepareStatement(insertSql);
        ps.setString(1,account);
        ps.setString(2,ip);
        ps.setString(3,date);
        ps.executeUpdate();
    }
}