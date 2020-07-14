package com.xiaoxin008.spring.jdbc.operation.update;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.jdbc.support.lob.DefaultLobHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Types;

/**
 * SqlUpdate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class FxNewsSqlUpdate {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        MySQLMaxValueIncrementer incrementer = (MySQLMaxValueIncrementer)classPathXmlApplicationContext.getBean(MySQLMaxValueIncrementer.class);
        DefaultLobHandler defaultLobHandler = new DefaultLobHandler();
        SqlUpdate sqlUpdate = new SqlUpdate(mySqlDataSource,"INSERT INTO fx_news(news_id,news_title,news_body) VALUES(?,?,?)");
        sqlUpdate.declareParameter(new SqlParameter(Types.BIGINT));
        sqlUpdate.declareParameter(new SqlParameter(Types.VARCHAR));
        sqlUpdate.declareParameter(new SqlParameter(Types.BLOB));
        sqlUpdate.compile();
        Resource resource = new ClassPathResource("application-context.xml");
        InputStream inputStream = resource.getInputStream();
        SqlLobValue sqlLobValue = new SqlLobValue(inputStream,Integer.MAX_VALUE,defaultLobHandler);
        sqlUpdate.update(new Object[]{incrementer.nextLongValue(),"头条",sqlLobValue});
    }

}
