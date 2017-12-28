package com.qxrj.net.index.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXModel;

/**
 * Created by luyuan on 2017/6/4.
 */

public class IndexModel extends QXModel {
    private Context             mContext = null;

    public IndexModel(Context _context) {
        super(_context);
        mContext = _context;
    }
}
