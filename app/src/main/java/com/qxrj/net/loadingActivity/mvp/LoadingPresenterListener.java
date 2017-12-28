package com.qxrj.net.loadingActivity.mvp;

import com.qxrj.net.MVP.QXPresenterListener;
import com.qxrj.net.MVP.QXView;

/**
 * Created by luyuan on 2017/5/26.
 */

public interface LoadingPresenterListener extends QXPresenterListener {

    void attachView(LoadingView view);

    void dettachView();
}
