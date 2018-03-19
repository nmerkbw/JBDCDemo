package com.jxs.dao;

import com.jxs.db.DBUtil;
import com.jxs.model.Girl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangxs on 2018/3/18.
 */
public class ProcedureDao {

    public void selectProcedure() throws SQLException {
        // 1.建立数据库的连接
        Connection connection = DBUtil.getConnection();
        // 2.获得CallableStatement
        String sql = "call girlSelectNotify";
        CallableStatement cs = null;
        try {
             cs = connection.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("获得CallableStatement失败！");
        }
        // 3.执行存储过程
        try {
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("执行存储过程失败");
        }
        ResultSet resultSet = cs.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_name")
                    + ","
                    + resultSet.getString("mobile"));
        }
    }

    public List<Girl> spSelectFilter(String spName) throws SQLException {

        // 1.建立数据库的连接
        Connection connection = DBUtil.getConnection();
        // 2.获取CallableStatement对象
        String sql = "call spSelectFilter(?)";
        CallableStatement cs = connection.prepareCall(sql);
        cs.setString(1,spName);
        cs.execute();
        //获得结果集
        Girl girl = null;
        List<Girl> result = new ArrayList<>();
        ResultSet resultSet = cs.getResultSet();
        while (resultSet.next()) {
            girl = new Girl();
            girl.setId(resultSet.getInt("id"));
            girl.setUserName(resultSet.getString("user_name"));
            girl.setAge(resultSet.getInt("age"));
            girl.setMobile(resultSet.getString("mobile"));
            result.add(girl);
        }
        return result;
    }

    public Integer spSelectFilterOut() throws SQLException {

        // 1.建立与数据库的连接
        Connection connection = DBUtil.getConnection();
        // 2.获取CallableStatement对象并执行
        int count = 0;
        String sql = "call spSelectFilterOut(?)";
        CallableStatement cs = connection.prepareCall(sql);
        cs.registerOutParameter(1, Types.INTEGER);
        cs.execute();
        count = cs.getInt(1);
        return count;
    }
}
