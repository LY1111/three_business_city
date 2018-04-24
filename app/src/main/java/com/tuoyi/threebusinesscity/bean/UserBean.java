package com.tuoyi.threebusinesscity.bean;

import android.content.Context;

import com.tuoyi.threebusinesscity.util.RxSPTool;

/**
 * Created by md
 * on 2018/4/23 0023.
 */

public class UserBean {
    //获取会员Token
    public static boolean getFirstTime(Context context) {
        return RxSPTool.getBoolean(context, "FirstTime");
    }

    //设置会员Token
    public static void setFirstTime(Context context, boolean FirstTime) {
        RxSPTool.putBoolean(context, "FirstTime", FirstTime);
    }


    //获取会员Token
    public static String getToken(Context context) {
        return RxSPTool.getString(context, "Token");
    }

    //设置会员Token
    public static void setToken(Context context, String Token) {
        RxSPTool.putString(context, "Token", Token);
    }

}
