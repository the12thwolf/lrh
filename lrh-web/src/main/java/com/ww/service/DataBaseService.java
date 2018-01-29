package com.ww.service;

import com.ww.dao.PayDetailMapper;
import com.ww.dao.PayItemMapper;
import com.ww.dao.PayPersonMapper;
import com.ww.dao.PaySubitemMapper;
import com.ww.domain.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */
@Service
public class DataBaseService {
    @Resource
    private PayItemMapper payItemMapper;
    @Resource
    private PaySubitemMapper paySubitemMapper;
    @Resource
    private PayPersonMapper payPersonMapper;
    @Resource
    private PayDetailMapper payDetailMapper;

    public List<PayItem> getPayItemListLimit(DataForPage dataForPage) {
        return payItemMapper.getPayItemListLimit(dataForPage);
        //调用dao的分页查询
    }

    public int getPayItemTotalCounts() {
        return payItemMapper.getPayItemTotalCounts()==null?0:payItemMapper.getPayItemTotalCounts();
    }

    public PayItem selectPayItemByItemName(String itemName) {
        return payItemMapper.selectPayItemByItemName(itemName);
    }

    public int insertPayItemSelective(PayItem payItem) {
        return payItemMapper.insertSelective(payItem);
    }

    public int deleteByPrimaryKey(Long itemId) {
        return payItemMapper.deleteByPrimaryKey(itemId);
    }

    public PayItem selectPayItemByPrimaryKey(Long itemId) {
        return payItemMapper.selectByPrimaryKey(itemId);
    }

    public int updatePayItemSelective(PayItem payItem) {
        return payItemMapper.updateByPrimaryKeySelective(payItem);
    }

    public List<PayItem> selectPayItemListAll() {
        return payItemMapper.selectPayItemListAll();
    }

    public List<PaySubitem> getPaySubitemListLimit(DataForPage dataForPage) {
        return null;
    }

    public int getPaySubitemTotalCountsByItemName(String itemName) {
        return paySubitemMapper.getPaySubitemTotalCountsByItemName(itemName);
    }

    public List<PaySubitem> selectPaySubitemListLimitByItemName(PaySubitemDataForPage paySubitemDataForPage) {
        return paySubitemMapper.selectPaySubitemListLimitByItemName(paySubitemDataForPage);
    }

    public int deletePaySubitemByPrimaryKey(Long subitemId) {
        return paySubitemMapper.deleteByPrimaryKey(subitemId);
    }

    public PaySubitem selectPaySubitemByPrimaryKey(Long subitemId) {
        return paySubitemMapper.selectByPrimaryKey(subitemId);
    }

    public int updatePaySubitemSelective(PaySubitem paySubitem) {
        return paySubitemMapper.updateByPrimaryKeySelective(paySubitem);
    }

    public PaySubitem selectPaySubitemByItemNameAndSubitemName(PaySubitem paySubitem) {
        return paySubitemMapper.selectPaySubitemByItemNameAndSubitemName(paySubitem);
    }

    public int insertPaySubitemSelective(PaySubitem paySubitem) {
        return paySubitemMapper.insertSelective(paySubitem);
    }

    public List<PaySubitem> selectPaySubitemsByItemName(String itemName) {
        return paySubitemMapper.selectPaySubitemsByItemName(itemName);
    }

    public List<PaySubitem> selectPayPersonListAll() {
        return payPersonMapper.selectPayPersonListAll();
    }

    public int insertPayDetailSelective(PayDetail payDetail) {
        return payDetailMapper.insertSelective(payDetail);
    }

    public int selectPayDetailTotalCountsByConditions(PayDetailDataForPage payDetailDataForPage) {
        return payDetailMapper.selectTotalCountsByConditions(payDetailDataForPage);
    }

    public List<PayDetail> selectPayDetailLimitByConditions(PayDetailDataForPage payDetailDataForPage) {
        return payDetailMapper.selectLimitByConditions(payDetailDataForPage);
    }

    public int deletePayDetailByPrimaryKey(long payId) {
        return payDetailMapper.deleteByPrimaryKey(payId);
    }

    public PayDetail selectPayDetailByPrimaryKey(long payId) {
        return payDetailMapper.selectByPrimaryKey(payId);
    }

    public int updatePayDetailByPrimaryKeySelective(PayDetail payDetail) {
        return payDetailMapper.updateByPrimaryKeySelective(payDetail);
    }
}
