package com.qxrj.net.accountActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.Director.Director;
import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.accountActivity.mvp.AccountPresenter;
import com.qxrj.net.index.IndexActivity;

import org.json.JSONObject;

/**
 * Created by luyuan on 2017/5/29.
 */

public class LoginActivity extends QXActivity {

    private String                          TAGLOT = "LoginActivity";
    private AccountPresenter                mPresenter = null;
    private EditText                        mUserNameEdit = null;
    private EditText                        mPasswordEdit = null;

    @Override
    public void QXDestroy() {
        super.QXDestroy();
    }

    @Override
    public void QXResume() {
        super.QXResume();
    }

    @Override
    public void QXPause() {
        super.QXPause();
    }

    @Override
    public int createContentViewResId() {
        return R.layout.activity_login;
    }

    @Override
    public QXPresenter createPresenter() {
        mPresenter = new AccountPresenter(this);
        return mPresenter;
    }

    @Override
    public void initView() {
        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView loginButton = (TextView) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEventLoginClickCallback();
            }
        });

        mUserNameEdit = (EditText) findViewById(R.id.userNameEdit);

        mPasswordEdit = (EditText) findViewById(R.id.passwordEdit);
    }

    private void onEventLoginClickCallback() {

        String userNameStr = mUserNameEdit.getText().toString();
        String passwordStr = mPasswordEdit.getText().toString();

        if (userNameStr.equals("") || passwordStr.equals("")) {
            Contans.showDialogCustemButtonTitleClick(this, R.string.edit_no_none, R.string.title_cancel, 0, null, null, 1);
            return;
        }

        Dictionary dictionary = new Dictionary();
        dictionary.add("userName", userNameStr);
        dictionary.add("password", passwordStr);
        mPresenter.getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/work/login.php",
                dictionary, "login");
    }

    @Override
    public void onEventQXSuccessJsonObject(JSONObject object) {
        super.onEventQXSuccessJsonObject(object);
        if (object != null) {
            boolean ret = object.optBoolean("result");
            if (!ret) {
                Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"), R.string.title_cancel, 0, null, null, 1);
            } else {
                Contans.log(TAGLOT, object.toString());
                Director.getInstance().setUser(object.optJSONObject("data"));

                Intent intent = new Intent(this, IndexActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onEventQXFailJsonObject(JSONObject object) {
        super.onEventQXFailJsonObject(object);
        if (object != null) {
            boolean ret = object.optBoolean("result");
            if (!ret) {
                Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"), R.string.title_cancel, 0, null, null, 1);
            } else {
                Contans.log(TAGLOT, object.toString());
            }
        }
    }
}
