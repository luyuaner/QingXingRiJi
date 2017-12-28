package com.qxrj.net.loadingActivity.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXPresenter;

/**
 * Created by luyuan on 2017/5/26.
 */

public class LoadingPresenter extends QXPresenter implements LoadingPresenterListener {

    private LoaddingModel           mModel = null;
    private LoadingView             mView = null;
    private Context                 mContext = null;

    @Override
    public void attachView(LoadingView view) {
        super.attachView(view);
        mView = (LoadingView) view;
    }

    @Override
    public void dettachView() {
        super.dettachView();
        mView = null;
    }

    public LoadingPresenter(Context _context) {
        super(_context);
        mContext = _context;
        mModel = new LoaddingModel(_context);
    }
}
