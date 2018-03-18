package com.jxs.dao;

import com.jxs.db.DBUtil;
import com.jxs.model.Girl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/15.
 */
public class GirlDao {

    public void addGirl(Girl girl) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                "insert into girl" +
                "(user_name,sex,age,birthday,email,mobile," +
                "create_user,create_date,update_user,update_date,isdel)" +
                "values(" +
                "?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ptmt=connection.prepareStatement(sql);

        ptmt.setString(1,girl.getUserName());
        ptmt.setInt(2,girl.getSex());
        ptmt.setInt(3,girl.getAge());
        ptmt.setDate(4,new Date(girl.getBirthday().getTime()));
        ptmt.setString(5,girl.getEmail());
        ptmt.setString(6,girl.getMobile());
        ptmt.setString(7,girl.getCreateUser());
        ptmt.setDate(8,new Date(girl.getCreateDate().getTime()));
        ptmt.setString(9,girl.getUpdateUser());
        ptmt.setDate(10,new Date(girl.getUpdateDate().getTime()));
        ptmt.setInt(11,girl.getIsdel());

        ptmt.execute();
    }

    public void delGirl(int id) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = ""+
                " delete from girl"+
                " where id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1,id);

        pstm.execute();
    }

    public void updateGirl(Girl girl) throws SQLException {

        Connection connection = DBUtil.getConnection();
        String sql = "" +
                "update girl" +
                " set sex=?,birthday=?,mobile=?,email=?," +
                " update_user=?,update_date=?,isdel=?" +
                " where id=?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1,girl.getSex());
        pstm.setDate(2,new Date(girl.getBirthday().getTime()));
        pstm.setString(3,girl.getMobile());
        pstm.setString(4,girl.getEmail());
        pstm.setString(5,girl.getUpdateUser());
        pstm.setDate(6,new Date(girl.getUpdateDate().getTime()));
        pstm.setInt(7,girl.getIsdel());
        pstm.setInt(8,girl.getId());

        pstm.execute();
    }

    public List<Girl> queryGirl(List<Map<String,Object>> parameters) throws SQLException {

        List<Girl> result = new ArrayList<>();
        Girl girl = null;
        Connection connection = DBUtil.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from girl where 1=2");
        if (parameters != null && parameters.size() > 0) {
            for (Map<String, Object> map : parameters) {
                sql.append(" or "+map.get("name")+" "+map.get("relation")+" "+map.get("value"));
            }
        }
        PreparedStatement pstm = connection.prepareStatement(sql.toString());
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            girl = new Girl();
            girl.setId(resultSet.getInt("id"));
            girl.setUserName(resultSet.getString("user_name"));
            girl.setSex(resultSet.getInt("sex"));
            girl.setAge(resultSet.getInt("age"));
            girl.setBirthday(resultSet.getDate("birthday"));
            girl.setMobile(resultSet.getString("mobile"));
            girl.setEmail(resultSet.getString("email"));
            girl.setCreateUser(resultSet.getString("create_user"));
            girl.setCreateDate(resultSet.getDate("create_date"));
            girl.setUpdateUser(resultSet.getString("update_user"));
            girl.setUpdateDate(resultSet.getDate("update_date"));
            girl.setIsdel(resultSet.getInt("isdel"));
            result.add(girl);
        }

        System.out.println(sql.toString());
        return result;
    }

    public Girl getSpecifiedGirlInfo(int id) throws SQLException {

        Girl girl = null;
        Connection connection = DBUtil.getConnection();
        String sql = "" +
                " select * from girl" +
                " where id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1,id);
        pstm.execute();
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            girl = new Girl();
            girl.setId(resultSet.getInt("id"));
            girl.setUserName(resultSet.getString("user_name"));
            girl.setSex(resultSet.getInt("sex"));
            girl.setAge(resultSet.getInt("age"));
            girl.setBirthday(resultSet.getDate("birthday"));
            girl.setMobile(resultSet.getString("mobile"));
            girl.setEmail(resultSet.getString("email"));
            girl.setCreateUser(resultSet.getString("create_user"));
            girl.setCreateDate(resultSet.getDate("create_date"));
            girl.setUpdateUser(resultSet.getString("update_user"));
            girl.setUpdateDate(resultSet.getDate("update_date"));
            girl.setIsdel(resultSet.getInt("isdel"));
        }
        return girl;
    }

}
