package com.qxrj.net.index.mvp;


import com.qxrj.net.MVP.QXPresenterListener;

/**
 * Created by luyuan on 2017/6/4.
 */

public interface IndexPresenterListener extends QXPresenterListener {

    void attachView(IndexView view);

    void dettachView();
}
