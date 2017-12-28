package com.qxrj.net.accountActivity.mvp;

import android.content.Context;

import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.MVP.QXView;

/**
 * Created by luyuan on 2017/5/29.
 */

public class AccountPresenter extends QXPresenter implements AccountPresenterListener {

    private Context             mContext = null;
    private AccountView         mAccountView = null;
    private AccountModel        mAccountModel = null;

    @Override
    public void attachView(AccountView view) {
        super.attachView(view);
        mAccountView = (AccountView) view;
    }

    @Override
    public void dettachView() {
        super.dettachView();
        mContext = null;
        mAccountModel = null;
        mAccountView = null;
    }

    public AccountPresenter(Context _context) {
        super(_context);
        mContext = _context;
        mAccountModel = new AccountModel(_context);
    }
}
