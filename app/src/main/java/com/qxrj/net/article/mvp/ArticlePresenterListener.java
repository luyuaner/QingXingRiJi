package com.qxrj.net.article.mvp;

import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.MVP.QXPresenterListener;

/**
 * Created by luyuan on 2017/6/11.
 */

public interface ArticlePresenterListener extends QXPresenterListener{

    void attachView(ArticleView view);

    void dettachView();
}
