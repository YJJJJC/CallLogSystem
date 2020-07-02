package com.ssmDemo.service.impl;

import com.ssmDemo.dao.BaseDao;
import com.ssmDemo.domain.User;
import com.ssmDemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:yjc
 * @Date: 2019/6/30 14:17
 * @Description:
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource(name = "userDao")
    public void setDao(BaseDao<User> dao) {
        super.setDao(dao);
    }

}
