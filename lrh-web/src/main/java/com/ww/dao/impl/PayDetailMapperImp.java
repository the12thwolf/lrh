package com.ww.dao.impl;

import com.ww.dao.PayDetailMapper;
import com.ww.domain.PayDetail;
import com.ww.domain.PayDetailDataForPage;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28.
 */
@Service
public class PayDetailMapperImp implements PayDetailMapper {
    private String nameSpace=PayDetailMapper.class.getName()+".";
    @Autowired
    private SqlSessionTemplate sst;

    public int deleteByPrimaryKey(Long payId) {
        return sst.delete(nameSpace+"deleteByPrimaryKey",payId);
    }

    public int insert(PayDetail record) {
        return 0;
    }

    public int insertSelective(PayDetail payDetail) {
        return sst.insert(nameSpace+"insertSelective",payDetail);
    }

    public PayDetail selectByPrimaryKey(Long payId) {
        return sst.selectOne(nameSpace+"selectByPrimaryKey",payId);
    }

    public int updateByPrimaryKeySelective(PayDetail payDetail) {
        return sst.update(nameSpace+"updateByPrimaryKeySelective",payDetail);
    }

    public int updateByPrimaryKey(PayDetail record) {
        return 0;
    }

    public int selectTotalCountsByConditions(PayDetailDataForPage payDetailDataForPage) {
        return sst.selectOne(nameSpace+"selectTotalCountsByConditions",payDetailDataForPage);
    }

    public List<PayDetail> selectLimitByConditions(PayDetailDataForPage payDetailDataForPage) {
        return sst.selectList(nameSpace+"selectLimitByConditions",payDetailDataForPage);
    }
}
