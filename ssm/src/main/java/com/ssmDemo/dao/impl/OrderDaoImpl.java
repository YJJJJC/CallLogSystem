package com.ssmDemo.dao.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.Order;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:06
 * @Description:
 */
@Repository("orderDao")
public class OrderDaoImpl extends SqlSessionDaoSupport implements BaseDao<Order> {
    public void insert(Order order) {
        getSqlSession().insert("orders.insert",order);
    }

    public void update(Order order) {
        getSqlSession().update("orders.update",order);
    }

    public void delete(Integer id) {
        getSqlSession().delete("orders.delete",id);
    }

    public Order selectOne(Integer id) {
        return getSqlSession().selectOne("orders.selectOne",id);
    }

    public List<Order> selectAll() {
        return getSqlSession().selectList("orders.selectAll");
    }

    public List<Order> selectPage(Integer offset, Integer len) {
        return null;
    }

    public int selectCount() {
        return 0;
    }
}
