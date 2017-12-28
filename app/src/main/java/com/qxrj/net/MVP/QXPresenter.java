package com.qxrj.net.MVP;

import android.content.Context;

import com.qxrj.net.Director.Director;
import com.qxrj.net.Director.OKHttp.OkHttpClientListener;
import com.qxrj.net.Director.OKHttp.OkHttpClientPost;
import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;

import okhttp3.OkHttpClient;

/**
 * Created by luyuan on 2017/5/25.
 */

public abstract class QXPresenter implements QXPresenterListener {
    private QXModel                     mQXModel = null;
    private QXView                      mQXView = null;
    private Context                     mContext = null;
    private OkHttpClientPost            mOkHttpClientPost = null;
    private OkHttpClient                mOkHttpClient = null;

    @Override
    public void attachView(QXView view) {
        this.mQXView = view;
    }

    @Override
    public void dettachView() {
        this.mQXView = null;
        this.mQXModel = null;
        mContext = null;
        mOkHttpClientPost = null;
        mOkHttpClient = null;
    }

    public QXPresenter(Context _context) {
        mContext = _context;
        mQXModel = new QXModel(_context);
        mOkHttpClient = Director.getInstance().getOkHttpClient();
        mOkHttpClientPost = new OkHttpClientPost(_context);
        mOkHttpClientPost.addOkHttpClientListener(mOkListener);
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public OkHttpClientPost getOkHttpClientPost() {
        return mOkHttpClientPost;
    }

    OkHttpClientListener mOkListener = new OkHttpClientListener() {
        @Override
        public void onOkHttpClientSuccess(OkHttpDataPakege _dataPakege) {
            if (mQXView != null) {
                mQXView.onOkHttpClientSuccess(_dataPakege);
            }
        }

        @Override
        public void onOkHttpClientFail(OkHttpDataPakege _dataPakege) {
            if (mQXView != null) {
                mQXView.onOkHttpClientFail(_dataPakege);
            }
        }

        @Override
        public void onOkHttpFinsh(OkHttpDataPakege _dataPakege) {
            if (mQXView != null) {
                mQXView.onOkHttpFinsh(_dataPakege);
            }
        }
    };
}
