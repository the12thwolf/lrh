package com.ww.domain;

public class DataUnit {
    private Integer dataunitid;

    private String dataunitname;

    public Integer getDataunitid() {
        return dataunitid;
    }

    public void setDataunitid(Integer dataunitid) {
        this.dataunitid = dataunitid;
    }

    public String getDataunitname() {
        return dataunitname;
    }

    public void setDataunitname(String dataunitname) {
        this.dataunitname = dataunitname == null ? null : dataunitname.trim();
    }
}