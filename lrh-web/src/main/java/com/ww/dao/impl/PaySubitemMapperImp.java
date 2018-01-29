package com.ww.dao.impl;

import com.ww.dao.PayItemMapper;
import com.ww.dao.PaySubitemMapper;
import com.ww.domain.PaySubitem;
import com.ww.domain.PaySubitemDataForPage;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 */
@Service
public class PaySubitemMapperImp implements PaySubitemMapper {
    private String nameSpace=PaySubitemMapper.class.getName()+".";
    @Autowired
    private SqlSessionTemplate sst;
    public int deleteByPrimaryKey(Long subitemId) {
        return sst.delete(nameSpace+"deleteByPrimaryKey",subitemId);
    }

    public int insert(PaySubitem record) {
        return 0;
    }

    public int insertSelective(PaySubitem subitemId) {
        return sst.insert(nameSpace+"insertSelective",subitemId);
    }

    public PaySubitem selectByPrimaryKey(Long subitemId) {
        return sst.selectOne(nameSpace+"selectByPrimaryKey",subitemId);
    }

    public int updateByPrimaryKeySelective(PaySubitem paySubitem) {
        return sst.update(nameSpace+"updateByPrimaryKeySelective",paySubitem);
    }

    public int updateByPrimaryKey(PaySubitem record) {
        return 0;
    }

    public Integer getPaySubitemTotalCountsByItemName(String itemName) {
        return sst.selectOne(nameSpace+"selectPaySubitemTotalCountsByItemName",itemName);
    }

    public List<PaySubitem> selectPaySubitemListLimitByItemName(PaySubitemDataForPage paySubitemDataForPage) {
        return sst.selectList(nameSpace+"selectPaySubitemListLimitByItemName",paySubitemDataForPage);
    }

    public PaySubitem selectPaySubitemByItemNameAndSubitemName(PaySubitem paySubitem) {
        return sst.selectOne(nameSpace+"selectByItemNameAndSubitemName",paySubitem);
    }

    public List<PaySubitem> selectPaySubitemsByItemName(String itemName) {
        return sst.selectList(nameSpace+"selectPaySubitemsByItemName",itemName);
    }
}
