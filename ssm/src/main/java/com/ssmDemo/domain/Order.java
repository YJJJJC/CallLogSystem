package com.ssmDemo.domain;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/26 19:15
 * @Description:
 */
public class Order {

    private Integer id;
    private String orderno;
    private float price ;

    //建立关联关系
    private User user;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
