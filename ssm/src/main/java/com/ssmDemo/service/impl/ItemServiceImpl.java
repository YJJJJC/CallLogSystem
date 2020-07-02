package com.ssmDemo.service.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.Item;
import com.ssmDemo.service.ItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:31
 * @Description:
 */

@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {

    @Resource(name = "itemDao")
    public void setDao(BaseDao<Item> dao) {
        super.setDao(dao);
    }
}
