package com.xiaoxin008.spring.jdbc.delegating;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.UserCredentialsDataSourceAdapter;

import javax.sql.DataSource;

/**
 * 测试UserCredentialsDataSourceAdapter功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class UserDelegatingDataSource {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        UserCredentialsDataSourceAdapter delegatingDataSource = new UserCredentialsDataSourceAdapter();
        delegatingDataSource.setTargetDataSource(mySqlDataSource);
        delegatingDataSource.setCredentialsForCurrentThread("root","admin");
        delegatingDataSource.getConnection();
    }
}
