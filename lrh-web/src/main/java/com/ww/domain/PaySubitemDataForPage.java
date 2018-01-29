package com.ww.domain;

/**
 * Created by Administrator on 2017/11/23.
 */
public class PaySubitemDataForPage extends DataForPage {
    private String itemName;

    public PaySubitemDataForPage(int start, int end, String itemName) {
        super(start, end);
        this.itemName = itemName;
    }

    public PaySubitemDataForPage(int start, int end) {
        super(start, end);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
