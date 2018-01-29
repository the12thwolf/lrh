package com.ww.dao;

import com.ww.domain.PaySubitem;
import com.ww.domain.PaySubitemDataForPage;

import java.util.List;

public interface PaySubitemMapper {
    int deleteByPrimaryKey(Long subitemId);

    int insert(PaySubitem subitemId);

    int insertSelective(PaySubitem subitemId);

    PaySubitem selectByPrimaryKey(Long subitemId);

    int updateByPrimaryKeySelective(PaySubitem subitemId);

    int updateByPrimaryKey(PaySubitem subitemId);
    public Integer getPaySubitemTotalCountsByItemName(String itemName);

    List<PaySubitem> selectPaySubitemListLimitByItemName(PaySubitemDataForPage paySubitemDataForPage);

    PaySubitem selectPaySubitemByItemNameAndSubitemName(PaySubitem paySubitem);

    List<PaySubitem> selectPaySubitemsByItemName(String itemName);
}