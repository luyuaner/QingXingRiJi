package com.qxrj.net.Director.OKHttp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.Director.Director;
import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Contans;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/25.
 */
public class OkHttpClientPost {

    private String                              TAGLOG = "OkHttpClientPost";
    private Context                             mContext = null;
    private OkHttpClient                        mOkHttpClient = null;
    private OkHttpClientListener                mListener = null;

    private Handler                             mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int state = msg.what;
            if (mListener != null) {
                OkHttpDataPakege pakege = (OkHttpDataPakege)msg.obj;
                if (state == OKHTTP_STATE.OKHTTP_SUCCESS) {
                    mListener.onOkHttpClientSuccess(pakege);
                } else if (state == OKHTTP_STATE.OKHTTP_FAIL) {
                    mListener.onOkHttpClientFail(pakege);
                } else if (state == OKHTTP_STATE.OKHTTP_FINSH) {
                    mListener.onOkHttpFinsh(pakege);
                } else {
                    Contans.log(TAGLOG, "handler error");
                }
            }
        }
    };

    private interface OKHTTP_STATE {
        int                                 OKHTTP_SUCCESS = 0;
        int                                 OKHTTP_FAIL = 1;
        int                                 OKHTTP_FINSH = 2;
        int                                 OKHTTP_NONE = 100;
    }


    public interface OKHTTP_HTTP_TYPE {
        int                               JSON_ARRAY = 0;
        int                               JSON_OBJECT = 1;
        int                               STRING = 2;
        int                               IMAGE_ARRAY = 3;
        int                               IMAGE_ARRAY_FINSH = 4;
        int                               BYTE = 5;
        int                               IMAGE_ONE_DOWN = 6;
        int                               IMAGE_ARRAY_ONE_DOWN = 7;
        int                               IMAGE_ARRAY_ONE_DOWN_FINSH = 8;
        int                               BELT_PARAMETER = 9;
    }

    public void onDestory() {
        mContext = null;
    }

    public OkHttpClientPost(Context _context) {
        mContext = _context;
        mOkHttpClient = Director.getInstance().getOkHttpClient();
    }

    //异步获取Json数据
    public void sendJsonObject(String url, Dictionary dictionary, final String state) {
        Contans.log(TAGLOG, "sendJsonObject    "+url);

        Request request = null;
        if (dictionary.size() != 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for (int i = 0; i < dictionary.size(); i++) {
                String key = dictionary.getKey(i);
                String value = dictionary.getString(key);
                builder.add(key, value);
                Contans.log(TAGLOG, key+"     "+value);
            }
            request = new Request.Builder().url(url).method("POST", builder.build()).build();
        } else {
            request = new Request.Builder().url(url).build();
        }

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            String mState = state;
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (mHandler != null) {
                    Contans.log(TAGLOG, "onFailure === "+e.toString());
                    OkHttpDataPakege pakege = new OkHttpDataPakege();
                    pakege.OKHTTP_TYPE = OKHTTP_STATE.OKHTTP_FAIL;
                    pakege.OKHTTP_TYPE = OKHTTP_HTTP_TYPE.JSON_OBJECT;
                    pakege.mState = mState;
                    String str = "{\"result\":false,\"data\":\"网络连接失败，请查看您的网络是否连接\"}";
                    pakege.mJsonObject = Contans.stringToJSONObject(str);
                    sendMessageHandler(pakege, OKHTTP_STATE.OKHTTP_FAIL);
                }
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                if (mHandler != null) {
                    String json = response.body().string();
                    Contans.log(TAGLOG, json);
                    OkHttpDataPakege pakege = new OkHttpDataPakege();
                    pakege.OKHTTP_TYPE = OKHTTP_HTTP_TYPE.JSON_OBJECT;
                    pakege.mJsonObject = Contans.stringToJSONObject(json);
                    pakege.mState = mState;
                    sendMessageHandler(pakege, OKHTTP_STATE.OKHTTP_SUCCESS);
                }
            }
        });
    }

    private void sendMessageHandler(OkHttpDataPakege pakege, int state) {
        Message msg = new Message();
        msg.obj = pakege;
        msg.what = state;
        mHandler.sendMessage(msg);
    }

    public void addOkHttpClientListener(OkHttpClientListener _listener) {
        mListener = _listener;
    }
}
