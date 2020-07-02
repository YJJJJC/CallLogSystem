package com.ssmDemo.domain;

/**
 * @author:yjc
 * @Date: 2019/6/26 19:18
 * @Description:
 */
public class Item {

    private Integer id;
    private String itemname;

    //订单项和订单之间的关联关系
    private Order order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
