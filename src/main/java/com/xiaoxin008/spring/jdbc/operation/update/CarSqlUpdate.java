package com.xiaoxin008.spring.jdbc.operation.update;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * SqlUpdate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CarSqlUpdate {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        SqlUpdate sqlUpdate = new SqlUpdate(mySqlDataSource,"UPDATE car SET brand = ?,volume = ? where id = ?");
        sqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter(Types.BIGINT));
        sqlUpdate.declareParameter(new SqlParameter(Types.BIGINT));
        sqlUpdate.compile();
        sqlUpdate.update(new Object[]{"大牌",20,7});
    }

}
