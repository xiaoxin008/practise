package com.xiaoxin008.spring.jdbc.operation.query;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;

/**
 * SqlFunction功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CarSqlFunction {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        SqlFunction<Integer> sf = new SqlFunction<>(mySqlDataSource," SELECT COUNT(id) FROM CAR ");
        sf.compile();
        int run = sf.run();
        System.out.println(run);
    }

}
