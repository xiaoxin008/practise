package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class JdbcTemplateDML {

    @Autowired
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     * 插入数据
     *
     * @return 成功条数
     */
    public int insert() {
        return mySqlJdbcTemplate.update(" INSERT INTO CAR(name) VALUES(?)", "保时捷");
    }

    /**
     * 更新数据
     *
     * @return 成功条数
     */
    public int update() {
        return mySqlJdbcTemplate.update(" UPDATE CAR SET brand = ? WHERE ID = ?", "保时捷经典款", 4L);
    }

    public int updatePs() {
        return mySqlJdbcTemplate.update(" UPDATE CAR SET brand = ? WHERE ID = ?", ps -> {
            ps.setString(1, "保时捷限量款");
            ps.setLong(2, 5L);
        });
    }

    /**
     * 删除数据
     *
     * @return 成功条数
     */
    public int delete() {
        return mySqlJdbcTemplate.update(" DELETE FROM CAR WHERE ID = ?", 4L);
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        JdbcTemplateDML jdbcTemplateDML = clx.getBean(JdbcTemplateDML.class);
//        jdbcTemplateDML.insert();
//        jdbcTemplateDML.update();
        jdbcTemplateDML.updatePs();
//        jdbcTemplateDML.delete();
    }
}
