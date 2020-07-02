package com.ssmDemo.service.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.dao.impl.PersonDaoImpl;
import com.ssmDemo.domain.Person;
import com.ssmDemo.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:yjc
 * @Date: 2019/7/6 10:43
 * @Description:
 */
@Service("personService")
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {


    @Resource(name = "personDao")
    public void setDao(BaseDao<Person> dao) {
        super.setDao(dao);
    }


    public String selectNameByPhone(String phone) {
        return ((PersonDaoImpl)getDao()).selectNameByPhone(phone);
    }
}
