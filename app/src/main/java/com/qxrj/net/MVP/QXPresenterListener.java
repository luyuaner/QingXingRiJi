package com.qxrj.net.MVP;

/**
 * Created by luyuan on 2017/5/26.
 */

public interface QXPresenterListener {
    //绑定视图
    void attachView(QXView view);
    //解除绑定
    void dettachView();
}
