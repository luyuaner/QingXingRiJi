package com.qxrj.net.Director.OKHttp;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by luyuan on 2017/5/31.
 */

public class OkHttpDataPakege {

    public int                              otherInt = 0;
    public int                              imagePosition = 0;
    public Bitmap                           mBitmap = null;
    public int                              imageArraySize = 0;
    public JSONArray                        mJsonArray = null;
    public JSONObject                       mJsonObject = null;
    public String                           mDataString = null;
    public String                           mState = null;
    public byte[]                           mImageByte = null;
    public int                              OKHTTP_TYPE = 0;

    //public int                              mStringResID = R.string.network_jsonObject_fail;

    public OkHttpDataPakege() {}
}
