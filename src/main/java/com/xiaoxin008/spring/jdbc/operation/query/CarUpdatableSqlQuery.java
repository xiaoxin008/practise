package com.xiaoxin008.spring.jdbc.operation.query;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * UpdatableSqlQuery的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CarUpdatableSqlQuery extends UpdatableSqlQuery {

    public CarUpdatableSqlQuery(DataSource ds, String sql) {
        super(ds, sql);
        compile();
    }

    @Override
    protected Object updateRow(ResultSet rs, int rowNum, Map context) throws SQLException {
        String sn = (String)context.get("sn");
        rs.updateString("sn",sn);
        return null;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        CarUpdatableSqlQuery usq = new CarUpdatableSqlQuery(mySqlDataSource," SELECT * FROM CAR WHERE sn IS NULL ");
        Map<String, Object> map = new HashMap<>();
        map.put("sn","89757");
        usq.execute(new Object[]{},map);
    }
}
