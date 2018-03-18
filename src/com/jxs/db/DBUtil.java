package com.jxs.db;

import java.sql.*;

/**
 * Created by jiangxs on 2018/3/13.
 */
public class DBUtil {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mydb2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static Connection connection;

    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // 加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        // 获得数据库的连接
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // 通过数据库的连接操作数据库，实现增删改查
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT user_name,age FROM girl");

        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name") + "," + resultSet.getString("age"));
        }
    }
}
