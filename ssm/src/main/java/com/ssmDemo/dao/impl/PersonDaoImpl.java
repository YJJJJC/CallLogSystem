package com.ssmDemo.dao.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.Person;
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
@Repository("personDao")
public class PersonDaoImpl extends SqlSessionDaoSupport implements BaseDao<Person> {

    public void insert(Person person) {
        getSqlSession().insert("persons.insert",person);
    }

    public void update(Person person) {
        getSqlSession().update("persons.update",person);
    }

    public void delete(Integer id) {
        getSqlSession().delete("persons.delete",id);
    }

    public Person selectOne(Integer id) {
        return getSqlSession().selectOne("persons.selectOne",id);
    }

    public List<Person> selectAll() {
        return getSqlSession().selectList("persons.selectAll");
    }

    public List<Person> selectPage(Integer offset, Integer len) {
        return getSqlSession().selectList("persons.selectPage",new RowBounds(offset,len));
    }

    public int selectCount() {
        return getSqlSession().selectOne("persons.selectCount");
    }

    //按照号码查询用户
    public String selectNameByPhone(String phone){
        return getSqlSession().selectOne("persons.selectNameByPhone",phone);
    }
}
