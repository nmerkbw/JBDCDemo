package com.jxs.action;

import com.jxs.dao.GirlDao;
import com.jxs.model.Girl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxs on 2018/3/15.
 */
public class GirlAction {

    /**
     * 使用JDBC对数据库进行增加操作
     * @author jiangxingsong
     * @exception SQLException
     * @date 2018-3-15
     * */
    public void addGirl(Girl girl) throws SQLException {

        GirlDao girlDao = new GirlDao();
        girlDao.addGirl(girl);
    }

    /**
     * 使用JDBC对数据库进行删除操作
     * @author jiangxingsong
     * @exception SQLException
     * @date 2018-3-15
     * */
    public void delteGirl(int id) throws SQLException {

        GirlDao girlDao = new GirlDao();
        girlDao.delGirl(id);
    }

    /**
     * 使用JDBC对数据库进行修改操作
     * @author jiangxingsong
     * @exception SQLException
     * @date 2018-3-15
     * */
    public void updateGirl(Girl girl) throws SQLException {

        GirlDao girlDao = new GirlDao();
        girlDao.updateGirl(girl);
    }

    /**
     * 使用JDBC对数据库进行查询操作
     * @author jiangxingsong
     * @exception SQLException
     * @date 2018-3-15
     * */
    public List<Girl> queryGirls(List<Map<String,Object>> parameters) throws SQLException {

        GirlDao girlDao = new GirlDao();
        return girlDao.queryGirl(parameters);
    }

    /**
     * 使用JDBC对数据库进行查询某一条数据的所有信息的操作
     * @author jiangxingsong
     * @exception SQLException
     * @date 2018-3-15
     * */
    public Girl queryGirl(int id) throws SQLException {

        GirlDao girlDao = new GirlDao();
        return girlDao.getSpecifiedGirlInfo(id);
    }
}
