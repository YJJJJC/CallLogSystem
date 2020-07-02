package com.ssmDemo.service.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.service.BaseService;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:22
 * @Description:
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> dao;

    public BaseDao<T> getDao() {
        return dao;
    }

    public void setDao(BaseDao<T> dao) {
        this.dao = dao;
    }

    public void insert(T t) {
        dao.insert(t);
    }

    public void update(T t) {
        dao.update(t);
    }

    public void delete(Integer id) {
        dao.delete(id);
    }

    public T selectOne(Integer id) {
        return dao.selectOne(id);
    }

    public List<T> selectAll() {
        return dao.selectAll();
    }

    public List<T> selectPage(Integer offset,Integer len){ return dao.selectPage(offset,len);}

    public int selectCount(){
        return dao.selectCount();
    }
}
