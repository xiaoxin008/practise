package com.xiaoxin008.spring.jdbc.template;

import com.xiaoxin008.spring.jdbc.po.Car;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * JdbcTemplate的使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class JdbcTemplateDQL {

    @Autowired
    private JdbcTemplate mySqlJdbcTemplate;

    /**
     * 普通查询
     *
     * @return 结果集
     */
    public List<Map<String, Object>> ordinary() {
        List<Map<String, Object>> results = mySqlJdbcTemplate.queryForList("SELECT * FROM CAR");
        return results;
    }

    /**
     * ResultSetExtractor
     *
     * @return 结果集
     */
    public List<Car> resultSetExtractor() {
        List<Car> results = mySqlJdbcTemplate.query("SELECT * FROM CAR", r -> {
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
        return results;
    }

    /**
     * RowMapper
     *
     * @return 结果集
     */
    public List<Car> rowMapper() {
        List<Car> results = mySqlJdbcTemplate.query("SELECT * FROM CAR", (r, index) -> {
            Car car = new Car();
            car.setId(r.getLong(1));
            car.setName(r.getString(2));
            car.setBrand(r.getString(3));
            car.setVolume(r.getInt(4));
            car.setBusload(r.getInt(5));
            car.setSn(r.getString(6));
            car.setFamily(r.getInt(7));
            car.setPrice(r.getBigDecimal(8));
            return car;
        });
        return results;
    }

    /**
     * RowCallbackHandler
     *
     * @return 结果集
     */
    public List<Car> rowCallbackHandler() {
        List<Car> cars = new ArrayList<>();
        mySqlJdbcTemplate.query("SELECT * FROM CAR", (r) -> {
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
        });
        return cars;
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        JdbcTemplateDQL jdbcTemplateQuery = clx.getBean(JdbcTemplateDQL.class);
//        List<Map<String, Object>> ordinary = myJdbcTemplate.ordinary();
//        List<Car> cars = myJdbcTemplate.resultSetExtractor();
//        List<Car> cars = myJdbcTemplate.rowMapper();
        List<Car> cars = jdbcTemplateQuery.rowCallbackHandler();
        System.out.println(cars);
    }
}
