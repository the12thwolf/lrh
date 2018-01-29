package com.ww.dao;

import com.ww.domain.PayPerson;
import com.ww.domain.PaySubitem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayPersonMapper {
    int deleteByPrimaryKey(Integer payPersonId);

    int insert(PayPerson record);

    int insertSelective(PayPerson record);

    PayPerson selectByPrimaryKey(Integer payPersonId);

    int updateByPrimaryKeySelective(PayPerson record);

    int updateByPrimaryKey(PayPerson record);

    List<PaySubitem> selectPayPersonListAll();
}