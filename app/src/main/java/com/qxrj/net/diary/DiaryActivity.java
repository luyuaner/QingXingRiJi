package com.qxrj.net.diary;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.Director.Director;
import com.qxrj.net.Director.OKHttp.OkHttpDataPakege;
import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.diary.mvp.DiaryPresenter;
import com.qxrj.net.index.IndexActivity;

import org.json.JSONObject;

/**
 * Created by luyuan on 2017/6/11.   发表文章
 */

public class DiaryActivity extends QXActivity {

    private String                  TAGLOT = "DiaryActivity";
    private DiaryActivity           app = null;
    private DiaryPresenter          mPresenter = null;
    private EditText                mTitleEditText = null;
    private EditText                mDiaryContentEditText = null;

    @Override
    public int createContentViewResId() {
        app = this;
        return R.layout.activity_diary;
    }

    @Override
    public QXPresenter createPresenter() {
        mPresenter = new DiaryPresenter(app);
        return mPresenter;
    }

    @Override
    public void initView() {
        ImageView backImage = (ImageView) findViewById(R.id.backButton);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                app.finish();
            }
        });

        mTitleEditText = (EditText) findViewById(R.id.title_edit_text);

        mDiaryContentEditText = (EditText) findViewById(R.id.diary_content_edit_text);

        LinearLayout diaryButton = (LinearLayout) findViewById(R.id.diaryButton);
        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEventDiaryButtonClickCallback();
            }
        });
    }

    private void onEventDiaryButtonClickCallback() {
        String titleString = mTitleEditText.getText().toString();
        String diaryContentString = mDiaryContentEditText.getText().toString();

        if (titleString.equals("") || diaryContentString.equals("")) {
            Contans.showDialogCustemButtonTitleClick(this, R.string.edit_no_none, R.string.title_cancel, 0, null, null, 1);
            return;
        }

        Dictionary dictionary = new Dictionary();
        dictionary.add("userId", Director.getInstance().getUser().getUserId());
        dictionary.add("nikeName", Director.getInstance().getUser().getNikeName());
        dictionary.add("diaryTitle", titleString);
        dictionary.add("diaryContent", diaryContentString);

        getPresenter().getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/work/diary.php",
                dictionary, "diary");
    }

    @Override
    public void onEventQXSuccessJsonObject(JSONObject object) {
        super.onEventQXSuccessJsonObject(object);
        if (object != null) {
            boolean ret = object.optBoolean("result");
            if (!ret) {
                Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"),
                        R.string.title_cancel, 0, null, null, 1);
            } else {
                Contans.log(TAGLOT, object.toString());
                Contans.showDialogCustemButtonTitleClick1(this, object.optString("data"),
                        R.string.title_sure, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                app.finish();
                            }
                        }, null, 1);
            }
        }
    }
}
