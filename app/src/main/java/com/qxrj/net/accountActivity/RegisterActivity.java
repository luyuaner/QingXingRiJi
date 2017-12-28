package com.qxrj.net.accountActivity;

import android.net.Uri;
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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by luyuan on 2017/5/30.
 */

public class RegisterActivity extends QXActivity {

    private String                      TAGLOT = "RegisterActivity";
    private AccountPresenter            mPresenter = null;
    private EditText                    mUserNameEdit = null;
    private EditText                    mNikeNameEdit = null;
    private EditText                    mPasswordEdit = null;
    private EditText                    mSurePasswordEdit = null;

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
        return R.layout.activity_register;
    }

    @Override
    public AccountPresenter createPresenter() {
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

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEventRegisterClickCallback();
            }
        });

        mUserNameEdit = (EditText) findViewById(R.id.userNameEdit);

        mNikeNameEdit = (EditText) findViewById(R.id.nikeNameEdit);

        mPasswordEdit = (EditText) findViewById(R.id.passwordEdit);

        mSurePasswordEdit = (EditText) findViewById(R.id.surePasswordEdit);
    }

    private void onEventRegisterClickCallback() {
        String userNameStr = mUserNameEdit.getText().toString();
        String nikeNameStr = mNikeNameEdit.getText().toString();
        String passwordStr = mPasswordEdit.getText().toString();
        String surePasswordStr = mSurePasswordEdit.getText().toString();

        if (userNameStr.equals("") || nikeNameStr.equals("")||passwordStr.equals("")||surePasswordStr.equals("")) {
            Contans.showDialogCustemButtonTitleClick(this, R.string.edit_no_none, R.string.title_cancel, 0, null, null, 1);
            return;
        }

        if (!passwordStr.equals(surePasswordStr)) {
            Contans.showDialogCustemButtonTitleClick(this, R.string.edit_password_no_identical, R.string.title_cancel, 0, null, null, 1);
            return;
        }

        try {
            Dictionary dictionary = new Dictionary();
            dictionary.add("userName", userNameStr);
            nikeNameStr = new String(nikeNameStr.getBytes("UTF-8"));
            dictionary.add("nikeName", nikeNameStr);
            dictionary.add("password", passwordStr);
            mPresenter.getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/register.php",
                    dictionary, "register");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEventQXSuccessJsonObject(JSONObject object) {
        super.onEventQXSuccessJsonObject(object);

        boolean ret = object.optBoolean("result");
        if (!ret) {
            Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"), R.string.title_cancel, 0, null, null, 1);
        } else {
            Contans.log(TAGLOT, object.toString());
            Director.getInstance().setUser(object);
        }
    }

    @Override
    public void onEventQXFailJsonObject(JSONObject object) {
        super.onEventQXFailJsonObject(object);
        boolean ret = object.optBoolean("result");
        if (!ret) {
            Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"), R.string.title_cancel, 0, null, null, 1);
        } else {
            Contans.log(TAGLOT, object.toString());
        }
    }
}
