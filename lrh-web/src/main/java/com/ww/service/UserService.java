package com.ww.service;

import com.ww.dao.UserMapper;
import com.ww.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/9/14.
 */
@Service("userService")
public class UserService {
    // 注入dao接口实现类实例
    // @Resource、@Autowired两种注入方式都可以
    @Resource
    private UserMapper userMapper;

    public User getUserByID(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }
}
