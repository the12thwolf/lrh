package com.ww.domain;

import java.util.Date;

public class PayDetail {
    private Long payId;

    private String itemName;

    private String payAmount;

    private String subitemName;

    private String payDate;

    private String payPerson;

    private String contant;

    private Date addTime;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount == null ? null : payAmount.trim();
    }

    public String getSubitemName() {
        return subitemName;
    }

    public void setSubitemName(String subitemName) {
        this.subitemName = subitemName == null ? null : subitemName.trim();
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate == null ? null : payDate.trim();
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson == null ? null : payPerson.trim();
    }

    public String getContant() {
        return contant;
    }

    public void setContant(String contant) {
        this.contant = contant == null ? null : contant.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}