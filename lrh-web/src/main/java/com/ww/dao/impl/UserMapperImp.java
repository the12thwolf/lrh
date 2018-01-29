package com.ww.dao.impl;

import com.ww.dao.UserMapper;
import com.ww.domain.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/17.
 */
@Service
public class UserMapperImp implements UserMapper {
    private String nameSpace=UserMapper.class.getName()+".";
    @Autowired
    private SqlSessionTemplate sst;
    public int deleteByPrimaryKey(Integer userId) {
        return 0;
    }

    public int insert(User record) {
        return 0;
    }

    public int insertSelective(User user) {
        int res = sst.insert(nameSpace+"insertSelective",user);
        //sst.commit(true);
        return res;
    }

    public User selectByPrimaryKey(Integer userId) {
        return sst.selectOne(nameSpace+"selectByPrimaryKey",userId);
    }

    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }

    public User getUserByUserName(String userName) {
        return sst.selectOne(nameSpace+"selectByUserName",userName);
    }
}
