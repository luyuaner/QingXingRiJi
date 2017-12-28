package com.qxrj.net.accountActivity.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXModel;

/**
 * Created by luyuan on 2017/5/29.
 */

public class AccountModel extends QXModel {
    private Context             mContext = null;

    public AccountModel(Context _context) {
        super(_context);
        mContext = _context;
    }
}
