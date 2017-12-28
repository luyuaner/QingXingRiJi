package com.qxrj.net.accountActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.accountActivity.mvp.AccountPresenter;

/**
 * Created by luyuan on 2017/5/30.
 */

public class GuideActivity extends QXActivity {

    private String                          TAGLOT = "GuideActivity";
    private AccountPresenter                mPresenter = null;
    private GuideActivity                   app = null;

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
        return R.layout.activity_guide;
    }

    @Override
    public AccountPresenter createPresenter() {
        mPresenter = new AccountPresenter(this);
        return mPresenter;
    }

    @Override
    public void initView() {
        app = this;

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(app, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        TextView loginButton = (TextView) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(app, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
