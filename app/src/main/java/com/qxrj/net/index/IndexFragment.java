package com.qxrj.net.index;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qxrj.net.Director.Contans;
import com.qxrj.net.Director.Dictionary;
import com.qxrj.net.Director.Director;
import com.qxrj.net.R;
import com.qxrj.net.VIEW.QXFragment;
import com.qxrj.net.article.ArticleActivity;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by luyuan on 2017/6/10.
 */

public class IndexFragment extends QXFragment {

    private String                      TAGLOG = "IndexFragment";
    private LinearLayout                mContentLayout = null;

    @Override
    public int createViewResId() {
        return R.layout.fragment_index;
    }

    @Override
    public void initView() {

        mContentLayout = (LinearLayout) getRootView().findViewById(R.id.indexContentLayout);

        Dictionary dictionary = new Dictionary();
        dictionary.add("page", 0);

        getQXPresenter().getOkHttpClientPost().sendJsonObject("http://192.168.2.100/PHPProject/work/otherdiary.php",
                dictionary, "otherDirary");

    }

    @Override
    public void lazyLoadData() {
        super.lazyLoadData();
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
                JSONArray jsonArray = object.optJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject dataObject = jsonArray.optJSONObject(i);
                    View v = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, null);

                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), ArticleActivity.class);
                            getActivity().startActivity(intent);
                        }
                    });
                    TextView titleTextView = (TextView) v.findViewById(R.id.title_text_view);
                    titleTextView.setText(dataObject.optString("diaryTitle"));

                    TextView timeTextView = (TextView) v.findViewById(R.id.time_text_view);
                    timeTextView.setText(dataObject.optString("publicationTime"));

                    TextView nikeTextView = (TextView) v.findViewById(R.id.nike_text_view);
                    nikeTextView.setText(dataObject.optString("nikeName"));

                    mContentLayout.addView(v);
                }
            }
        }
    }
}
