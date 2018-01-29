package com.ww.dao;

import com.ww.domain.DataForPage;
import com.ww.domain.PayItem;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayItemMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(PayItem record);

    int insertSelective(PayItem record);

    PayItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(PayItem record);

    int updateByPrimaryKey(PayItem record);


    List<PayItem> getPayItemListLimit(DataForPage dataForPage);

    Integer getPayItemTotalCounts();

    PayItem selectPayItemByItemName(String itemName);

    List<PayItem> selectPayItemListAll();
}