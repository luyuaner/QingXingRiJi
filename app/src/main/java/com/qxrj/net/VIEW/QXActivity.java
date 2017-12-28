package com.qxrj.net.VIEW;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qxrj.net.Director.OKHttp.OkHttpClientPost;
import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;
import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.MVP.QXView;

import org.json.JSONObject;

/**
 * Created by luyuan on 2017/5/25.
 */

public abstract class QXActivity extends AppCompatActivity implements QXView {

    private QXPresenter                       mQXPresnter = null;
    private View                              mRootView = null;

    @Override
    protected void onPause() {
        super.onPause();
        QXPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        QXResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mQXPresnter != null) {
            mQXPresnter.dettachView();
            mQXPresnter = null;
        }
        QXDestroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int resId = createContentViewResId();
        if (resId != 0) {
            setContentView(resId);
        }
        mQXPresnter = createPresenter();
        if (mQXPresnter != null) {
            mQXPresnter.attachView(this);
        }
        initView();
    }

    public abstract int createContentViewResId();
    public abstract QXPresenter createPresenter();
    public abstract void initView();
    public void QXDestroy() {}
    public void QXResume() {}
    public void QXPause() {}
    public void onEventQXSuccessJsonObject(JSONObject object) {}

    public void onEventQXFailJsonObject(JSONObject object) {}

    //主View
    public View getRootView(){
        if (mRootView == null) {
            mRootView = getWindow().getDecorView().getRootView();
        }
        return mRootView;
    }

    public QXPresenter getPresenter() {
        return mQXPresnter;
    }

    @Override
    public void onOkHttpClientSuccess(OkHttpDataPakege _dataPakege) {
        if (_dataPakege.OKHTTP_TYPE == OkHttpClientPost.OKHTTP_HTTP_TYPE.JSON_OBJECT) {
            onEventQXSuccessJsonObject(_dataPakege.mJsonObject);
        }
    }

    @Override
    public void onOkHttpClientFail(OkHttpDataPakege _dataPakege) {
        if (_dataPakege.OKHTTP_TYPE == OkHttpClientPost.OKHTTP_HTTP_TYPE.JSON_OBJECT) {
            onEventQXFailJsonObject(_dataPakege.mJsonObject);
        }
    }

    @Override
    public void onOkHttpFinsh(OkHttpDataPakege _dataPakege) {

    }
}
