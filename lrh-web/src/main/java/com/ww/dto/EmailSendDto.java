package com.ww.dto;

/**
 * Created by Administrator on 2017/9/21.
 */
public class EmailSendDto {
    private String fajianren;
    private String shoujianren;
    private String chaosong;
    private String mailtitle;
    private String mailtext;

    public String getFajianren() {
        return fajianren;
    }

    public void setFajianren(String fajianren) {
        this.fajianren = fajianren;
    }

    public String getShoujianren() {
        return shoujianren;
    }

    public void setShoujianren(String shoujianren) {
        this.shoujianren = shoujianren;
    }

    public String getChaosong() {
        return chaosong;
    }

    public void setChaosong(String chaosong) {
        this.chaosong = chaosong;
    }

    public String getMailtitle() {
        return mailtitle;
    }

    public void setMailtitle(String mailtitle) {
        this.mailtitle = mailtitle;
    }

    public String getMailtext() {
        return mailtext;
    }

    public void setMailtext(String mailtext) {
        this.mailtext = mailtext;
    }
}
