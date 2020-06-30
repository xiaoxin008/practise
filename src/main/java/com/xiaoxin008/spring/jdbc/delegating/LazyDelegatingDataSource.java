package com.xiaoxin008.spring.jdbc.delegating;

import com.xiaoxin008.spring.jdbc.po.Car;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试LazyConnectionDataSourceProxy功能
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class LazyDelegatingDataSource {

    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        DataSource mySqlDataSource = (DataSource)classPathXmlApplicationContext.getBean("myDataSource");
        LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy = new LazyConnectionDataSourceProxy(mySqlDataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(lazyConnectionDataSourceProxy);
        List<Car> results = jdbcTemplate.query("SELECT * FROM CAR", r -> {
            List<Car> cars = new ArrayList<>();
            while (r.next()) {
                Car car = new Car();
                car.setId(r.getLong(1));
                car.setName(r.getString(2));
                car.setBrand(r.getString(3));
                car.setVolume(r.getInt(4));
                car.setBusload(r.getInt(5));
                car.setSn(r.getString(6));
                car.setFamily(r.getInt(7));
                car.setPrice(r.getBigDecimal(8));
                cars.add(car);
            }
            return cars;
        });
        System.out.println(results);
    }
}
