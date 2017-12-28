package com.qxrj.net.Director;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by luyuan on 2017/5/30.
 */

public class Contans {

    public static void log(String tag, String _value) {
        Log.e(tag, _value);
    }

    //弹出框
    public static void showDialogCustemButtonTitleClick(Context _context, int message,
                                                        int cancelResId, int sureResId,
                                                        DialogInterface.OnClickListener onCancelListener,
                                                        DialogInterface.OnClickListener onSureClickListener,
                                                        int num) {
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setMessage(message);
        if (num == 1) {
            builder.setPositiveButton(cancelResId, onCancelListener);
        } else if (num == 2) {
            builder.setPositiveButton(cancelResId, onCancelListener);
            builder.setNegativeButton(sureResId, onSureClickListener);
        }

        builder.show();
    }

    //弹出框
    public static void showDialogCustemButtonTitleClick1(Context _context, String message,
                                                        int cancelResId, int sureResId,
                                                        DialogInterface.OnClickListener onCancelListener,
                                                        DialogInterface.OnClickListener onSureClickListener,
                                                        int num) {
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setMessage(message);
        if (num == 1) {
            builder.setPositiveButton(cancelResId, onCancelListener);
        } else if (num == 2) {
            builder.setPositiveButton(cancelResId, onCancelListener);
            builder.setNegativeButton(sureResId, onSureClickListener);
        }

        builder.show();
    }

    /*
     * 直接\u9999表示的很少，基本都会用转义字符即\\u9999,这样一来打印出来的就不是我们需要的字符，而是Unicode码\u9999
     * 因此必须解析字符串得到想要的字符。
     */
    public static String unicodeToString(String s) {
        ArrayList<String> list =new ArrayList<>();
        String zz="\\\\u[0-9,a-z,A-Z]{4}";

        //正则表达式用法参考API
        Pattern pattern = Pattern.compile(zz);
        Matcher m = pattern.matcher(s);
        while(m.find()){
            list.add(m.group());
        }
        for(int i=0,j=2;i<list.size();i++){
            String st = list.get(i).substring(j, j+4);

            //将得到的数值按照16进制解析为十进制整数，再強转为字符
            char ch = (char) Integer.parseInt(st, 16);
            //用得到的字符替换编码表达式
            s = s.replace(list.get(i), String.valueOf(ch));
        }
        return s;
    }

    //将汉字转为unicode
    public static String stringToUnicode(String s){
        int in;
        String st = "";
        for(int i=0;i<s.length();i++){
            in = s.codePointAt(i);
            st = st+"\\u"+Integer.toHexString(in).toUpperCase();
        }
        return st;
    }

    //encode数据
    public static String stringToEncode(String str) {
        if (str == null || str == "" || str.length() == 0) {
            return "";
        }
        String encodeStr = Uri.encode(str);
        return encodeStr;
    }
    //encode转回字符串
    public static String encodeToString(String str) {
        if (str == null || str == "" || str.length() == 0) {
            return "";
        }
        String string = Uri.decode(str);
        return string;
    }

    public static JSONObject stringToJSONObject(String str) {
        char start = str.charAt(0);
        char end = str.charAt(str.length()-1);
        JSONObject object = null;
        if (start == '{' && end == '}') {
            try {
                object = new JSONObject(str);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
