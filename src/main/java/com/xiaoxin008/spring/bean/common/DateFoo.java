package com.xiaoxin008.spring.bean.common;

import java.util.Date;

/**
 * 时间对象
 *
 * @author xiaoxin008(313595055 @ qq.com)
 * @since 1.0.0
 */
public class DateFoo {

    private Date date;

    public DateFoo() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DateFoo{" +
                "date=" + date +
                '}';
    }
}
