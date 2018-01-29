package com.ww.dao.impl;

import com.ww.dao.PayItemMapper;
import com.ww.dao.UserMapper;
import com.ww.domain.DataForPage;
import com.ww.domain.PayItem;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */
@Service
public class PayItemMapperImp implements PayItemMapper {
    private String nameSpace=PayItemMapper.class.getName()+".";
    @Autowired
    private SqlSessionTemplate sst;
    public int deleteByPrimaryKey(Long itemId) {
        return sst.delete(nameSpace+"deleteByPrimaryKey",itemId);
    }

    public int insert(PayItem payItem) {
        return 0;
    }

    public int insertSelective(PayItem payItem) {
        return sst.insert(nameSpace+"insertSelective",payItem);
    }

    public PayItem selectByPrimaryKey(Long itemId) {
        return sst.selectOne(nameSpace+"selectByPrimaryKey",itemId);
    }

    public int updateByPrimaryKeySelective(PayItem payItem) {
        return sst.update(nameSpace+"updateByPrimaryKeySelective",payItem);
    }

    public int updateByPrimaryKey(PayItem record) {
        return 0;
    }

    public List<PayItem> getPayItemListLimit(DataForPage dataForPage) {
        return sst.selectList(nameSpace+"selectPayItemListLimit",dataForPage);
    }

    public Integer getPayItemTotalCounts() {
        return sst.selectOne(nameSpace+"selectTotalCounts");
    }

    public PayItem selectPayItemByItemName(String itemName) {
        return sst.selectOne(nameSpace+"selectPayItemByItemName",itemName);
    }

    public List<PayItem> selectPayItemListAll() {
        return sst.selectList(nameSpace+"selectPayItemListAll");
    }

}
