package com.ww.domain;

public class PayPerson {
    private Integer payPersonId;

    private String payPersonName;

    public Integer getPayPersonId() {
        return payPersonId;
    }

    public void setPayPersonId(Integer payPersonId) {
        this.payPersonId = payPersonId;
    }

    public String getPayPersonName() {
        return payPersonName;
    }

    public void setPayPersonName(String payPersonName) {
        this.payPersonName = payPersonName == null ? null : payPersonName.trim();
    }
}