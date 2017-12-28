package com.qxrj.net.diary.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXPresenter;

/**
 * Created by luyuan on 2017/6/11.
 */

public class DiaryPresenter extends QXPresenter implements DiaryPresenterListener {

    private Context                 mContext = null;
    private DiaryModel              mDiaryModel = null;
    private DiaryView               mDiaryView = null;

    @Override
    public void attachView(DiaryView view) {
        mDiaryView = view;
    }

    @Override
    public void dettachView() {
        super.dettachView();
        mContext = null;
        mDiaryView = null;
        mDiaryModel = null;
    }

    public DiaryPresenter(Context _context) {
        super(_context);
        mDiaryModel = new DiaryModel(_context);
    }
}
