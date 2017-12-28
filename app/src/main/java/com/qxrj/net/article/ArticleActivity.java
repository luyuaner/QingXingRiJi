package com.qxrj.net.article;

import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.MVP.QXPresenter;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXActivity;
import com.qxrj.net.article.mvp.ArticlePresenter;

import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by luyuan on 2017/6/11.
 */

public class ArticleActivity extends QXActivity {

    private String                      TAGLOG = "ArticleActivity";
    private ArticlePresenter            mPresenter = null;
    private ArticleActivity             app = null;
    private TextView                    mTitleText = null;
    private TextView                    mTimeText = null;
    private TextView                    mNikeNameText = null;
    private TextView                    mContentText = null;

    @Override
    public QXPresenter createPresenter() {
        mPresenter = new ArticlePresenter(app);
        return mPresenter;
    }

    @Override
    public int createContentViewResId() {
        app = this;
        return R.layout.activity_article;
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

        mTitleText = (TextView) findViewById(R.id.title_text_view);

        mTimeText = (TextView) findViewById(R.id.time_text_view);

        mNikeNameText = (TextView) findViewById(R.id.nike_text_view);

        mContentText = (TextView) findViewById(R.id.content_text_view);

        Dictionary dictionary = new Dictionary();
        dictionary.add("diaryId", "27076");
        getPresenter().getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/work/selectDiary.php",
                dictionary, "selectDiary");
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
                Contans.log(TAGLOG, object.toString());
                JSONObject dataObject = object.optJSONObject("data");
                mTitleText.setText(dataObject.optString("diaryTitle"));
                mNikeNameText.setText(dataObject.optString("nikeName"));
                mContentText.setText(dataObject.optString("diaryContent"));
                mTimeText.setText(dataObject.optString("publicationTime"));
            }
        }
    }
}
