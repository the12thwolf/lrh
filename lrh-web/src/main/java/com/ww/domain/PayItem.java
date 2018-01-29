package com.ww.domain;

public class PayItem {
    private Long itemId;

    private String itemName;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public PayItem() {
    }

    public PayItem(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String toString() {
        return "PayItem{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}