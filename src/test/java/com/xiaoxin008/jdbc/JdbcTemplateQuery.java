package com.xiaoxin008.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Cleanup;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class JdbcTemplateQuery {

    private DruidDataSource druidDataSource;

    @PostConstruct
    public void init(){
        druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/crm");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("admin");
    }

    /**
     * 普通查询
     *
     */
    public List<Map<String,Object>> ordinary(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(druidDataSource);
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM CAR");
        return results;
    }

    @Test
    public void test() {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("application-context-test.xml");
        JdbcTemplateQuery myJdbcTemplate = clx.getBean(JdbcTemplateQuery.class);
        List<Map<String, Object>> ordinary = myJdbcTemplate.ordinary();
        System.out.println(ordinary);
    }


    @PreDestroy
    public void destroy(){
        druidDataSource.close();
    }
}
