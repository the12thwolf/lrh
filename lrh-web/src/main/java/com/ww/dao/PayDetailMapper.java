package com.ww.dao;

import com.ww.domain.PayDetail;
import com.ww.domain.PayDetailDataForPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PayDetailMapper {
    int deleteByPrimaryKey(Long payId);

    int insert(PayDetail record);

    int insertSelective(PayDetail record);

    PayDetail selectByPrimaryKey(Long payId);

    int updateByPrimaryKeySelective(PayDetail record);

    int updateByPrimaryKey(PayDetail record);

    int selectTotalCountsByConditions(PayDetailDataForPage payDetailDataForPage);

    List<PayDetail> selectLimitByConditions(PayDetailDataForPage payDetailDataForPage);
}