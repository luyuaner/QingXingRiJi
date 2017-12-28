package com.qxrj.net.VIEW;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qxrj.net.Director.OKHttp.OkHttpClientPost;
import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;
import com.qxrj.net.MVP.QXPresenter;

import org.json.JSONObject;

/**
 * Created by luyuan on 2017/6/10.
 */

public abstract class QXFragment extends Fragment {

    private View                mRootView = null;
    private QXPresenter         mPresenter = null;
    private QXActivity          mQXActivity = null;
    private boolean             isVisible = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPostPHP();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resId = createViewResId();
        if (resId == 0) {
            mRootView = super.onCreateView(inflater, container, savedInstanceState);
        } else {
            mRootView = inflater.inflate(resId, container, false);
            initView();
        }
        return mRootView;
    }

    public abstract int createViewResId();
    public abstract void initView();
    public void onEventQXSuccessJsonObject(JSONObject object) {}
    public void loadPostPHP() {}
    public void lazyLoadData() {}

    protected View getRootView() {
        return mRootView;
    }

    protected QXActivity getQXActivity() {
        if (mQXActivity == null) {
            mQXActivity = (QXActivity) getActivity();
        }
        return mQXActivity;
    }

    protected QXPresenter getQXPresenter() {
        if (mPresenter == null) {
            mPresenter = getQXActivity().getPresenter();
        }
        return mPresenter;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInVisible();
        }
    }

    protected void onVisible() {
        lazyLoadData();
    }

    protected void onInVisible() {}

    public void getOnOkHttpSuccess(OkHttpDataPakege pakege) {
        if (pakege.OKHTTP_TYPE == OkHttpClientPost.OKHTTP_HTTP_TYPE.JSON_OBJECT) {
            onEventQXSuccessJsonObject(pakege.mJsonObject);
        }
    }

    public void getOnOkHttpFail(OkHttpDataPakege pakege) {

    }

    public void getOkHttpFinsh(OkHttpDataPakege pakege) {

    }
}
