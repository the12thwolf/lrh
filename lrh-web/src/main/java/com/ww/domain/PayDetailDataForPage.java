package com.ww.domain;

/**
 * Created by Administrator on 2017/11/28.
 */
public class PayDetailDataForPage {
    private int crruntPage;
    private int startPage;
    private int itemsPerPage;
    private String itemName;
    private String subitemName;
    private String payPerson;
    private String payDateStart;
    private String payDateEnd;

    @Override
    public String toString() {
        return "PayDetailDataForPage{" +
                "crruntPage=" + crruntPage +
                ", startPage=" + startPage +
                ", itemsPerPage=" + itemsPerPage +
                ", itemName='" + itemName + '\'' +
                ", subitemName='" + subitemName + '\'' +
                ", payPerson='" + payPerson + '\'' +
                ", payDateStart='" + payDateStart + '\'' +
                ", payDateEnd='" + payDateEnd + '\'' +
                '}';
    }

    public int getCrruntPage() {
        return crruntPage;
    }

    public void setCrruntPage(int crruntPage) {
        this.crruntPage = crruntPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSubitemName() {
        return subitemName;
    }

    public void setSubitemName(String subitemName) {
        this.subitemName = subitemName;
    }

    public String getPayPerson() {
        return payPerson;
    }

    public void setPayPerson(String payPerson) {
        this.payPerson = payPerson;
    }

    public String getPayDateStart() {
        return payDateStart;
    }

    public void setPayDateStart(String payDateStart) {
        this.payDateStart = payDateStart;
    }

    public String getPayDateEnd() {
        return payDateEnd;
    }

    public void setPayDateEnd(String payDateEnd) {
        this.payDateEnd = payDateEnd;
    }
}
