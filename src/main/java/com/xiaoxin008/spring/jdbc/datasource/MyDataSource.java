package com.xiaoxin008.spring.jdbc.datasource;

import com.xiaoxin008.spring.jdbc.po.Car;
import lombok.Cleanup;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 自定义数据源
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
@Component
public class MyDataSource extends AbstractDataSource {

    private Driver driver;

    private String url;

    private Properties connectionProperties;

    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("我的数据源！");
        return driver.connect(url,connectionProperties);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        connectionProperties.setProperty("user",username);
        connectionProperties.setProperty("password",password);
        return driver.connect(url,connectionProperties);
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Properties getConnectionProperties() {
        return connectionProperties;
    }

    public void setConnectionProperties(Properties connectionProperties) {
        this.connectionProperties = connectionProperties;
    }

    public static void main(String[] args) {
        @Cleanup ClassPathXmlApplicationContext clx = new ClassPathXmlApplicationContext("classpath:application-context.xml");
        MyDataSource myDataSource = clx.getBean(MyDataSource.class);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(myDataSource);
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
