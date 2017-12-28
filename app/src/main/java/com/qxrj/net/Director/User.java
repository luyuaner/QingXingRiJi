package com.qxrj.net.Director;

/**
 * Created by luyuan on 2017/6/8.
 */

public class User {

    private String                  UserName = "";
    private int                     UserId = 0;
    private String                  NikeName = "";
    private String                  Password = "";
    private String                  UserAvatar = "";
    private String                  RegisterTime = "";
    private String                  LoginTime = "";

    public User() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getNikeName() {
        return NikeName;
    }

    public void setNikeName(String nikeName) {
        NikeName = nikeName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserAvatar() {
        return UserAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        UserAvatar = userAvatar;
    }

    public String getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(String registerTime) {
        RegisterTime = registerTime;
    }

    public String getLoginTime() {
        return LoginTime;
    }

    public void setLoginTime(String loginTime) {
        LoginTime = loginTime;
    }
}
