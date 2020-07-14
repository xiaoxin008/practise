package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class MySqlJdbcTemplateLob {

    @Autowired
    private JdbcTemplate mySqlJdbcTemplate;

    @Autowired
    private DataFieldMaxValueIncrementer mySqlIncrementer;

    public int insertLob(){
       return mySqlJdbcTemplate.update(" INSERT INTO fx_news(news_id,news_title,news_body) VALUES(?,?,?)",ps -> {
           try {
               Resource fileSystemResource = new ClassPathResource("datasource.properties");
               ps.setLong(1,mySqlIncrementer.nextLongValue());
               ps.setString(2,"测试新闻");
               ps.setBinaryStream(3,fileSystemResource.getInputStream());
           } catch (Exception e) {
               e.printStackTrace();
           }
       });
    }

    public Map<String, Object> queryLob(){
        Map<String, Object> stringObjectMap = mySqlJdbcTemplate.queryForMap(" SELECT * FROM fx_news WHERE news_id = ?", 76);
        System.out.println(stringObjectMap);
        return stringObjectMap;
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        MySqlJdbcTemplateLob mySqlJdbcTemplateLob = clx.getBean(MySqlJdbcTemplateLob.class);
        mySqlJdbcTemplateLob.insertLob();
        Map<String, Object> stringObjectMap = mySqlJdbcTemplateLob.queryLob();
        byte[] news_body = (byte[])stringObjectMap.get("news_body");
        try {
            @Cleanup OutputStream outputStream = new FileOutputStream("C:\\Users\\WFX\\Desktop\\test.txt");
            outputStream.write(news_body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
