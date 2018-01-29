package com.ww.domain;

public class PaySubitem {
    private Long subitemId;

    private String itemName;

    private String subitemName;

    public Long getSubitemId() {
        return subitemId;
    }

    public void setSubitemId(Long subitemId) {
        this.subitemId = subitemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getSubitemName() {
        return subitemName;
    }

    public void setSubitemName(String subitemName) {
        this.subitemName = subitemName == null ? null : subitemName.trim();
    }

    public PaySubitem() {
    }

    public PaySubitem(String itemName) {
        this.itemName = itemName;
    }

    public PaySubitem(Long subitemId) {
        this.subitemId = subitemId;
    }

    public PaySubitem(Long subitemId, String itemName) {
        this.subitemId = subitemId;
        this.itemName = itemName;
    }

    public PaySubitem(Long subitemId, String itemName, String subitemName) {
        this.subitemId = subitemId;
        this.itemName = itemName;
        this.subitemName = subitemName;
    }

    public PaySubitem(String itemName, String subitemName) {
        this.itemName = itemName;
        this.subitemName = subitemName;
    }

    @Override
    public String toString() {
        return "PaySubitem{" +
                "subitemId=" + subitemId +
                ", itemName='" + itemName + '\'' +
                ", subitemName='" + subitemName + '\'' +
                '}';
    }
}