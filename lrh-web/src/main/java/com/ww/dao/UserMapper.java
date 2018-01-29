package com.ww.dao;

import com.ww.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User user);

    int insertSelective(User user);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByUserName(String userName);
}