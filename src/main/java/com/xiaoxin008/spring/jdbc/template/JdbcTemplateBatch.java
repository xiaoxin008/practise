package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class JdbcTemplateBatch {

    @Autowired
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     * 批量更新数据
     *
     * @return 成功条数数组
     */
    public int[] batchUpdateSql() {
        String sql1 = " UPDATE CAR SET brand = '法拉利限定款' WHERE id = 6";
        String sql2 = " UPDATE CAR2 SET brand = '本田经典款' WHERE id = 'xxx'";
        return mySqlJdbcTemplate.batchUpdate(new String[]{sql1, sql2});
    }

    /**
     * 批量更新数据
     *
     * @return 成功条数数组
     */
    public int[] batchUpdatePs() {
        return mySqlJdbcTemplate.batchUpdate(" INSERT INTO CAR(name) VALUES(?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                if (i == 0) {
                    ps.setString(1, "丰田");
                } else if (i == 1) {
                    ps.setString(1, "宝马");
                }
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        JdbcTemplateBatch jdbcTemplateBatch = clx.getBean(JdbcTemplateBatch.class);
        jdbcTemplateBatch.batchUpdatePs();
    }
}
