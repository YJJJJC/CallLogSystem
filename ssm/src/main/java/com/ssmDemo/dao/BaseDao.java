package com.ssmDemo.dao;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 13:55
 * @Description:
 */
public interface BaseDao<T> {

    public void insert(T t);
    public void update(T t);
    public void delete(Integer id);
    public T selectOne(Integer id);
    public List<T> selectAll();
    public List<T> selectPage(Integer offset,Integer len);
    public int selectCount();
}
