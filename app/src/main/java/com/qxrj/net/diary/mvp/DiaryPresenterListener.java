package com.qxrj.net.diary.mvp;

import com.qxrj.net.MVP.QXPresenterListener;

/**
 * Created by luyuan on 2017/6/11.
 */

public interface DiaryPresenterListener extends QXPresenterListener {
    void attachView(DiaryView view);

    void dettachView();
}
