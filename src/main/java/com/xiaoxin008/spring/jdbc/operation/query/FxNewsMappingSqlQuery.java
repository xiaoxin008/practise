package com.xiaoxin008.spring.jdbc.operation.query;

import lombok.Cleanup;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MappingSqlQuery对对象中LOB的查询
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class FxNewsMappingSqlQuery extends MappingSqlQuery<Map<String,Object>> {

    private static final String QUERY = " SELECT * FROM fx_news WHERE news_id = ? ";

    private LobHandler lobHandler = new DefaultLobHandler();

    public FxNewsMappingSqlQuery(DataSource ds) {
        super(ds, QUERY);
        declareParameter(new SqlParameter(Types.BIGINT));
        compile();
    }

    @Override
    protected Map<String,Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        Map<String, Object> map = new HashMap<>();
        map.put("news_id",rs.getLong("news_id"));
        map.put("news_title",rs.getString("news_title"));
        map.put("news_body",lobHandler.getBlobAsBytes(rs,"news_body"));
        return map;
    }

    public LobHandler getLobHandler() {
        return lobHandler;
    }

    public void setLobHandler(LobHandler lobHandler) {
        this.lobHandler = lobHandler;
    }

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        FxNewsMappingSqlQuery fx = new FxNewsMappingSqlQuery(mySqlDataSource);
        List<Map<String, Object>> execute = fx.execute(96L);
        Map<String, Object> map = execute.get(0);
        byte[] news_body = (byte[])map.get("news_body");
        @Cleanup FileOutputStream outputStreamWriter = new FileOutputStream("C:\\Users\\WFX\\Desktop\\test.xml");
        outputStreamWriter.write(news_body);
    }
}
