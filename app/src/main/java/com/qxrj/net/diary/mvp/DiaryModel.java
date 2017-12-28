package com.qxrj.net.diary.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXModel;

/**
 * Created by luyuan on 2017/6/11.
 */

public class DiaryModel extends QXModel {

    private Context             mContext = null;

    public DiaryModel(Context _context) {
        super(_context);
        mContext = _context;
    }
}
