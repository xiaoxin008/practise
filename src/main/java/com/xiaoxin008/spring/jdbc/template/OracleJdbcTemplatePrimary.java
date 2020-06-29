package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
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
public class OracleJdbcTemplatePrimary {

    @Autowired
    private JdbcTemplate oracleJdbcTemplate;

    @Autowired
    private DataFieldMaxValueIncrementer oracleIncrementer;

    public int[] primaryKey4table(){
        return oracleJdbcTemplate.batchUpdate(" INSERT INTO YH_ZENTAO_DMC_SYSTEM(ID,SYSTEM_NAME) VALUES(?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1,oracleIncrementer.nextLongValue());
                ps.setString(2,"测试模块");
            }

            @Override
            public int getBatchSize() {
                return 5;
            }
        });
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        OracleJdbcTemplatePrimary mySqlJdbcTemplatePrimary = clx.getBean(OracleJdbcTemplatePrimary.class);
        mySqlJdbcTemplatePrimary.primaryKey4table();
    }
}
