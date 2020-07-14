package com.xiaoxin008.spring.jdbc.template;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class OracleJdbcTemplateLob {

    @Autowired
    private JdbcTemplate oracleJdbcTemplate;

    @Autowired
    private DataFieldMaxValueIncrementer oracleIncrementer;

    @Autowired
    private LobHandler oracleLobHandler;

    private Resource fileSystemResource = new ClassPathResource("datasource.properties");

    public int insertLob(){

        return oracleJdbcTemplate.execute(" INSERT INTO YH_ZENTAO_DMC_SYSTEM(ID,SYSTEM_NAME,SYSTEM_FILE) VALUES(?,?,?)", new AbstractLobCreatingPreparedStatementCallback(oracleLobHandler) {
            @Override
            protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException, DataAccessException {
                ps.setLong(1,oracleIncrementer.nextLongValue());
                ps.setString(2,"测试模块");
                try {
                    lobCreator.setBlobAsBinaryStream(ps,3,fileSystemResource.getInputStream(),(int)fileSystemResource.getFile().length());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void queryLob(){
        byte[] b = oracleJdbcTemplate.queryForObject(" SELECT * FROM YH_ZENTAO_DMC_SYSTEM WHERE ID = ?",new Object[]{52L} ,(rs, i) -> oracleLobHandler.getBlobAsBytes(rs,9));
        try {
            @Cleanup OutputStream outputStream = new FileOutputStream("C:\\Users\\WFX\\Desktop\\test.txt");
            outputStream.write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        OracleJdbcTemplateLob oracleJdbcTemplateLob = clx.getBean(OracleJdbcTemplateLob.class);
//        oracleJdbcTemplateLob.insertLob();
        oracleJdbcTemplateLob.queryLob();
    }
}
