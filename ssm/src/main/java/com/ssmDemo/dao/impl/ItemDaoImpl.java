package com.ssmDemo.dao.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.Item;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:09
 * @Description:
 */
@Repository("itemDao")
public class ItemDaoImpl extends SqlSessionDaoSupport implements BaseDao<Item> {
    public void insert(Item item) {
        getSqlSession().insert("items.insert",item);
    }

    public void update(Item item) {
        getSqlSession().update("items.update",item);
    }

    public void delete(Integer id) {
        getSqlSession().delete("items.delete",id);
    }

    public Item selectOne(Integer id) {
        return getSqlSession().selectOne("items.selectOne",id);
    }

    public List<Item> selectAll() {
        return getSqlSession().selectList("items.selectAll");
    }

    public List<Item> selectPage(Integer offset, Integer len) {
        return null;
    }

    public int selectCount() {
        return 0;
    }
}
