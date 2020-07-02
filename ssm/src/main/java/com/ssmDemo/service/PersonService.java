package com.ssmDemo.service;

import com.ssmDemo.domain.Person;

/**
 * @author:yjc
 * @Date: 2019/7/6 10:41
 * @Description:
 */
public interface PersonService extends BaseService<Person> {

    public String selectNameByPhone(String phone);
}
