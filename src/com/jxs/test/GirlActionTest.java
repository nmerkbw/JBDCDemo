package com.jxs.test;

import com.jxs.action.GirlAction;
import com.jxs.model.Girl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by jiangxs on 2018/3/16.
 */
public class GirlActionTest {

    @Test
    public void testAddGirl() throws SQLException {

        Girl girl = new Girl();
        GirlAction girlAction = new GirlAction();
        girl.setUserName("5号");
        girl.setAge(19);
        girl.setSex(1);
        girl.setBirthday(new Date());
        girl.setEmail("698846963@qq.com");
        girl.setMobile("13745487821");
        girl.setCreateDate(new Date());
        girl.setCreateUser("SuperAdmin");
        girl.setUpdateUser("SuperAdmin");
        girl.setUpdateDate(new Date());
        girl.setIsdel(1);

        girlAction.addGirl(girl);
    }

    @Test
    public void testDeleteGirl() throws SQLException {

        GirlAction girlAction = new GirlAction();
        girlAction.delteGirl(7);
    }

    @Test
    public void testUpdateGirl() throws SQLException {

        GirlAction girlAction = new GirlAction();
        Girl girl = new Girl();
        girl.setSex(1);
        girl.setBirthday(new Date());
        girl.setMobile("13655551515");
        girl.setEmail("123456@qq.com");
        girl.setUpdateUser("SuperAdmin");
        girl.setUpdateDate(new Date());
        girl.setIsdel(1);
        girl.setId(1);

        girlAction.updateGirl(girl);
    }

    @Test
    public void testQueryGirls() throws SQLException {

        List<Map<String,Object>> parameters = new ArrayList<>();
        Map<String,Object> parameter = new HashMap<>();
        parameter.put("name","user_name");
        parameter.put("relation","=");
        parameter.put("value","'酱萌烦'");
        parameters.add(parameter);
        GirlAction girlAction = new GirlAction();
        List<Girl> girls = girlAction.queryGirls(parameters);
        for (Girl girl : girls) {
            System.out.println(girl);
        }
    }

    @Test
    public void testQueryGirl() throws SQLException {

        GirlAction girlAction = new GirlAction();
        System.out.println(girlAction.queryGirl(6));
    }
}
