package com.ssmDemo.dao.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.User;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:01
 * @Description:
 */
@Repository("userDao")
public class UserDaoImpl extends SqlSessionDaoSupport implements BaseDao<User> {

    public void insert(User user) {
        getSqlSession().insert("users.insert",user);
    }

    public void update(User user) {
        getSqlSession().update("users.update",user);
    }

    public void delete(Integer id) {
        getSqlSession().delete("users.delete",id);
    }

    public User selectOne(Integer id) {
        return getSqlSession().selectOne("users.selectOne",id);
    }

    public List<User> selectAll() {
        return getSqlSession().selectList("users.selectAll");
    }

    public List<User> selectPage(Integer offset, Integer len) {
        return getSqlSession().selectList("users.selectPage",new RowBounds(offset,len));
    }

    public int selectCount() {
        return getSqlSession().selectOne("users.selectCount");
    }
}
