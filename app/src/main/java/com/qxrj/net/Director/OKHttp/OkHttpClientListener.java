package com.qxrj.net.Director.OKHttp;

/**
 * Created by Administrator on 2016/8/25.
 */
public interface OkHttpClientListener {
    void onOkHttpClientSuccess(OkHttpDataPakege _dataPakege);
    void onOkHttpClientFail(OkHttpDataPakege _dataPakege);
    void onOkHttpFinsh(OkHttpDataPakege _dataPakege);
}
