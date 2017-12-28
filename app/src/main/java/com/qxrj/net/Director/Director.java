package com.qxrj.net.Director;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by luyuan on 2017/5/30.
 */

public class Director {
    private static final Director ourInstance = new Director();
    private OkHttpClient mOkHttpClient = null;
    private User                                            mUser = null;

    public static Director getInstance() {
        return ourInstance;
    }

    private Director() {
    }

    public OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            createOkHttpClient();
        }
        return mOkHttpClient;
    }

    private void createOkHttpClient() {
        int timeout = 5000;
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS)
                .build();

    }

    private void createUser() {
        mUser = new User();
    }

    public User getUser() {
        if (mUser == null) {
            createUser();
        }
        return mUser;
    }

    public void setUser(JSONObject object) {
        if (mUser == null) {
            createUser();
        }
        mUser.setPassword(object.optString("password"));
        mUser.setNikeName(object.optString("nikeName"));
        mUser.setLoginTime(object.optString("loginTime"));
        mUser.setRegisterTime(object.optString("regisiterTime"));
        mUser.setUserName(object.optString("userName"));
        mUser.setUserAvatar(object.optString("userAvatar"));
        mUser.setUserId(Integer.parseInt(object.optString("userId")));
    }
}
