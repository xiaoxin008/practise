package com.xiaoxin008.spring.jdbc.operation.query;

import com.xiaoxin008.spring.jdbc.po.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MappingSqlQueryWithParameters查询操作
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class CarMappingSqlQueryWithParameters extends MappingSqlQueryWithParameters {

    private static final String QUERY_SQL = "SELECT * FROM CAR WHERE ID = ?";

    public CarMappingSqlQueryWithParameters(DataSource ds) {
        super(ds, QUERY_SQL);
        declareParameter(new SqlParameter(Types.BIGINT));
        compile();
    }

    @Override
    protected Object mapRow(ResultSet rs, int rowNum, Object[] parameters, Map context) throws SQLException {
        Long num = (Long)context.get("num");
        Car car = new Car();
        if(num > 1L){
            car.setSn("大型号");
        }
        car.setId(rs.getLong(1));
        car.setName(rs.getString(2));
        car.setBrand(rs.getString(3));
        return car;
    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        CarMappingSqlQueryWithParameters q = new CarMappingSqlQueryWithParameters(mySqlDataSource);
        Map<String, Object> map = new HashMap<>();
        map.put("num",2L);
        List execute = q.execute(1L,map);
        for (Object o : execute) {
            Car c = (Car) o;
            System.out.println(c);
        }
    }
}
