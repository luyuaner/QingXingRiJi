package com.qxrj.net.loadingActivity.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXModel;

/**
 * Created by luyuan on 2017/5/26.
 */

public class LoaddingModel extends QXModel {
    private Context                 mContext = null;

    public LoaddingModel(Context _context) {
        super(_context);
        mContext = null;
    }
}
