package com.qxrj.net.index;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.Director.Director;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXFragment;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by luyuan on 2017/6/10.
 */

public class MyFragment extends QXFragment {

    private String              TAGLOG = "MyFragment";
    private TextView            mDiaryContentText = null;
    private TextView            mTitleDiaryText = null;
    private boolean             mIsPost = false;

    @Override
    public int createViewResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        TextView nikeNameText = (TextView) getRootView().findViewById(R.id.nike_text_view);
        nikeNameText.setText(Director.getInstance().getUser().getNikeName());

        mTitleDiaryText = (TextView) getRootView().findViewById(R.id.title_text_view);
        mDiaryContentText = (TextView) getRootView().findViewById(R.id.content_text_view);

        TextView timeNameText = (TextView) getRootView().findViewById(R.id.time_text_view);
        timeNameText.setText(Director.getInstance().getUser().getRegisterTime());
    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
        if (mIsPost) {
            return;
        }
    }

    @Override
    public void loadPostPHP() {
        super.loadPostPHP();
        if (mIsPost) {
            return;
        }
        loadPhp();
    }

    @Override
    public void onEventQXSuccessJsonObject(JSONObject object) {
        super.onEventQXSuccessJsonObject(object);
        if (object != null) {
            boolean ret = object.optBoolean("result");
            if (!ret) {
                Contans.showDialogCustemButtonTitleClick1(getContext(), object.optString("data"), R.string.title_cancel, 0, null, null, 1);
            } else {
                Contans.log(TAGLOG, object.toString());
                JSONArray dataArray = object.optJSONArray("data");
                JSONObject dataObject = dataArray.optJSONObject(0);
                mTitleDiaryText.setText(dataObject.optString("diaryTitle"));
                mDiaryContentText.setText(dataObject.optString("diaryContent"));
            }
        }
    }

    private void loadPhp() {
        Dictionary dictionary = new Dictionary();
        dictionary.add("userId", Director.getInstance().getUser().getUserId());
        dictionary.add("page", "0");
        dictionary.add("diaryRet", "true");

        getQXPresenter().getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/work/mydiary.php",
                dictionary, "myDirary");
    }
}
