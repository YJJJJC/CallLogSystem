package com.ssmDemo.service;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:12
 * @Description:
 */
public interface BaseService<T> {

    public void insert(T t);
    public void update(T t);
    public void delete(Integer id);
    public T selectOne(Integer id);
    public List<T> selectAll();
    public List<T> selectPage(Integer offset,Integer len);
    public int selectCount();
}
