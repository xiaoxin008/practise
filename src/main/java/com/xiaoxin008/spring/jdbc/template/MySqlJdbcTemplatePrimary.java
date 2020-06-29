package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class MySqlJdbcTemplatePrimary {

    @Autowired
    private JdbcTemplate mySqlJdbcTemplate;

    @Autowired
    private MySQLMaxValueIncrementer mySqlIncrementer;

    public int[] primaryKey4table(){
        return mySqlJdbcTemplate.batchUpdate(" INSERT INTO fx_news(news_id,news_title,news_body) VALUES(?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,mySqlIncrementer.nextLongValue());
                ps.setString(2,"title");
                ps.setString(3,"body");
            }

            @Override
            public int getBatchSize() {
                return 5;
            }
        });
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        MySqlJdbcTemplatePrimary mySqlJdbcTemplatePrimary = clx.getBean(MySqlJdbcTemplatePrimary.class);
        mySqlJdbcTemplatePrimary.primaryKey4table();
    }
}
