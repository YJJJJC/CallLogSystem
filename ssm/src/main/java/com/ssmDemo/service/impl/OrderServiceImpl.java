package com.ssmDemo.service.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.Order;
import com.ssmDemo.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:29
 * @Description:
 */

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {

    @Resource(name = "orderDao")
    public void setDao(BaseDao<Order> dao) {
        super.setDao(dao);
    }
}
