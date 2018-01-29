package com.ww.domain;

/**
 * Created by Administrator on 2017/11/28.
 */
public class PayDetailDataForPageOld {
    private int crruntPage;
    private int startPage;
    private int itemsPerPage;
    private String itemNameOld;
    private String subitemNameOld;
    private String payPersonOld;
    private String payDateStartOld;
    private String payDateEndOld;

    @Override
    public String toString() {
        return "PayDetailDataForPage{" +
                "crruntPage=" + crruntPage +
                ", startPage=" + startPage +
                ", itemsPerPage=" + itemsPerPage +
                ", itemNameOld='" + itemNameOld + '\'' +
                ", subitemNameOld='" + subitemNameOld + '\'' +
                ", payPersonOld='" + payPersonOld + '\'' +
                ", payDateStartOld='" + payDateStartOld + '\'' +
                ", payDateEndOld='" + payDateEndOld + '\'' +
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

    public String getItemNameOld() {
        return itemNameOld;
    }

    public void setItemNameOld(String itemNameOld) {
        this.itemNameOld = itemNameOld;
    }

    public String getSubitemNameOld() {
        return subitemNameOld;
    }

    public void setSubitemNameOld(String subitemNameOld) {
        this.subitemNameOld = subitemNameOld;
    }

    public String getPayPersonOld() {
        return payPersonOld;
    }

    public void setPayPersonOld(String payPersonOld) {
        this.payPersonOld = payPersonOld;
    }

    public String getPayDateStartOld() {
        return payDateStartOld;
    }

    public void setPayDateStartOld(String payDateStartOld) {
        this.payDateStartOld = payDateStartOld;
    }

    public String getPayDateEndOld() {
        return payDateEndOld;
    }

    public void setPayDateEndOld(String payDateEndOld) {
        this.payDateEndOld = payDateEndOld;
    }
}
