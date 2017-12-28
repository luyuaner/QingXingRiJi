package com.qxrj.net.accountActivity.mvp;

import com.qxrj.net.MVP.QXPresenterListener;

/**
 * Created by luyuan on 2017/5/29.
 */

public interface AccountPresenterListener extends QXPresenterListener {

    //绑定视图
    void attachView(AccountView view);
    //解除绑定
    void dettachView();
}
