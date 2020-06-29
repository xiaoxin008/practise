package com.xiaoxin008.spring.jdbc.template.name;

import com.xiaoxin008.spring.jdbc.po.Car;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * NameParameterJdbcTemplate的基本使用
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class NameParameterJdbcTemplateQuery {

    @Autowired
    private NamedParameterJdbcTemplate mySqlNameParameterJdbcTemplate;

    public Car queryObject(){
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("name","法拉利");
        return mySqlNameParameterJdbcTemplate.queryForObject(" SELECT * FROM CAR WHERE NAME = :name ", mapSqlParameterSource, (r, rownum) -> {
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
    }

    public List<String> queryList(){
        Car car = new Car();
        car.setFamily(0);
        SqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(car);
        //注意这里只对基本类型有效
        return mySqlNameParameterJdbcTemplate.queryForList(" SELECT NAME FROM CAR WHERE FAMILY = :family ", beanPropertySqlParameterSource, String.class);
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        NameParameterJdbcTemplateQuery nameParameterJdbcTemplateQuery = clx.getBean(NameParameterJdbcTemplateQuery.class);
//        Car c = nameParameterJdbcTemplateQuery.queryObject();
        List<String> cars = nameParameterJdbcTemplateQuery.queryList();
//        System.out.println(c);
        cars.stream().map(ca -> ca.toString()).forEach(System.out::println);
    }
}
