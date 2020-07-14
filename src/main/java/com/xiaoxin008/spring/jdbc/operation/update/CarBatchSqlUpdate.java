package com.xiaoxin008.spring.jdbc.operation.update;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * SqlUpdate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CarBatchSqlUpdate {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        BatchSqlUpdate batchSqlUpdate = new BatchSqlUpdate(mySqlDataSource,
                "INSERT INTO car(name) values(?) ");
        batchSqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        batchSqlUpdate.compile();
        batchSqlUpdate.setBatchSize(10);
        for (int i = 0;i<10;i++) {
            batchSqlUpdate.update(new Object[]{"Jeep"});
        }
//        int[] flush = batchSqlUpdate.flush();
//        System.out.println(flush);
    }

}
