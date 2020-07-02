package com.ssmDemo.domain;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/26 16:47
 * @Description:
 * 编写Mapper映射文件 User类与表mybatis_user对应
 */
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
