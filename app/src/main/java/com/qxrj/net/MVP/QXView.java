package com.qxrj.net.MVP;

import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;

/**
 * Created by luyuan on 2017/5/25.
 */

public interface QXView {
    void onOkHttpClientSuccess(OkHttpDataPakege _dataPakege);
    void onOkHttpClientFail(OkHttpDataPakege _dataPakege);
    void onOkHttpFinsh(OkHttpDataPakege _dataPakege);
}
