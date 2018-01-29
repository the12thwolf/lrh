package com.ww.dto;

/**
 * Created by Administrator on 2017/9/24.
 */
public class MailBackSendResult {
    private String errorCode;
    private String errorMsg;

    public MailBackSendResult() {
    }

    public MailBackSendResult(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
