package com.ww.dao.impl;

import com.ww.dao.PayItemMapper;
import com.ww.dao.PayPersonMapper;
import com.ww.domain.PayPerson;
import com.ww.domain.PaySubitem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 */
@Service
public class PayPersonMapperImp implements PayPersonMapper {
    private String nameSpace=PayPersonMapper.class.getName()+".";
    @Autowired
    private SqlSessionTemplate sst;
    public int deleteByPrimaryKey(Integer payPersonId) {
        return 0;
    }

    public int insert(PayPerson record) {
        return 0;
    }

    public int insertSelective(PayPerson record) {
        return 0;
    }

    public PayPerson selectByPrimaryKey(Integer payPersonId) {
        return null;
    }

    public int updateByPrimaryKeySelective(PayPerson record) {
        return 0;
    }

    public int updateByPrimaryKey(PayPerson record) {
        return 0;
    }

    public List<PaySubitem> selectPayPersonListAll() {
        return sst.selectList(nameSpace+"selectPayPersonListAll");
    }
}
