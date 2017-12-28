package com.qxrj.net.index.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.MVP.QXView;

/**
 * Created by luyuan on 2017/6/4.
 */

public class IndexPresenter extends QXPresenter implements IndexPresenterListener {
    private Context                 mContext = null;
    private IndexView               mIndexView = null;
    private IndexModel              mIndexModel = null;

    @Override
    public void attachView(IndexView view) {
        mIndexView = view;
    }

    @Override
    public void dettachView() {
        super.dettachView();
        mContext = null;
        mIndexModel = null;
        mIndexView = null;
    }

    public IndexPresenter(Context _context) {
        super(_context);
        mContext = _context;
    }
}
