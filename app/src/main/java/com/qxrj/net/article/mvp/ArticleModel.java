package com.qxrj.net.article.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXModel;

/**
 * Created by luyuan on 2017/6/11.
 */

public class ArticleModel extends QXModel {

    private Context             mContext = null;

    public ArticleModel(Context _context) {
        super(_context);
        mContext = _context;
    }
}
