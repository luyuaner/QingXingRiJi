package com.qxrj.net.loadingActivity;

import android.content.Context;
import android.content.Intent;

import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.accountActivity.GuideActivity;
import com.qxrj.net.loadingActivity.mvp.LoadingPresenter;

public class LoadingActivity extends QXActivity {

    private LoadingPresenter                mPresenter = null;
    private Context                         mContext = null;

    @Override
    public void QXPause() {
        super.QXPause();
    }

    @Override
    public void QXResume() {
        super.QXResume();
    }

    @Override
    public void QXDestroy() {
        super.QXDestroy();
    }

    @Override
    public int createContentViewResId() {
        return R.layout.activity_loading;
    }

    @Override
    public LoadingPresenter createPresenter() {
        mPresenter = new LoadingPresenter(this);
        return mPresenter;
    }

    @Override
    public void initView() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
    }
}
