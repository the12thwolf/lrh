package com.ww.domain;

public class User {
    private Integer userId;

    private String userName;

    private String realName;

    private String password;

    private String rolPriv;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRolPriv() {
        return rolPriv;
    }

    public void setRolPriv(String rolPriv) {
        this.rolPriv = rolPriv == null ? null : rolPriv.trim();
    }

    @Override
    public String toString() {
        return "User={" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", rolPriv='" + rolPriv + '\'' +
                '}';
    }
}