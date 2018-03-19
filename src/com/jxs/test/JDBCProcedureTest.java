package com.jxs.test;

import com.jxs.dao.ProcedureDao;
import com.jxs.model.Girl;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by jiangxs on 2018/3/18.
 */
public class JDBCProcedureTest {

    @Test
    public void testSelectProcedure() throws SQLException {
        ProcedureDao pd = new ProcedureDao();
        pd.selectProcedure();
    }

    @Test
    public void testSpSelectFilter() throws SQLException {

        ProcedureDao pd = new ProcedureDao();
        List<Girl> girls = pd.spSelectFilter("酱");
        showResult(girls);
    }

    @Test
    public void testSpSelectFilterOut() throws SQLException {
        ProcedureDao pd = new ProcedureDao();
        System.out.println(pd.spSelectFilterOut());
    }

    private void showResult(List<Girl> result) {

        for (Girl girl : result) {
            System.out.println(girl.getId()
                    +","
                    +girl.getUserName()
                    +","
                    +girl.getMobile());
        }
    }
}
