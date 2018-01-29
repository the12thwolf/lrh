package com.ww.dao;

import com.ww.domain.DataUnit;

public interface DataUnitMapper {
    int deleteByPrimaryKey(Integer dataunitid);

    int insert(DataUnit record);

    int insertSelective(DataUnit record);

    DataUnit selectByPrimaryKey(Integer dataunitid);

    int updateByPrimaryKeySelective(DataUnit record);

    int updateByPrimaryKey(DataUnit record);
}