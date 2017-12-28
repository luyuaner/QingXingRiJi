package com.qxrj.net.article.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXPresenter;

/**
 * Created by luyuan on 2017/6/11.
 */

public class ArticlePresenter extends QXPresenter implements ArticlePresenterListener {

    private Context                 mContext = null;
    private ArticleModel            mArticleModel = null;
    private ArticleView             mArticleView = null;

    @Override
    public void attachView(ArticleView view) {
        mArticleView = view;
    }

    @Override
    public void dettachView() {
        super.dettachView();
    }

    public ArticlePresenter(Context _context) {
        super(_context);
        mArticleModel = new ArticleModel(_context);
    }
}
