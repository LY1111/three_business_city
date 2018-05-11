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

    //遗忘会员Token
    public static void removeToken(Context context) {
        RxSPTool.remove(context, "Token");
    }

    //获取会员Token
    public static String getToken(Context context) {
        return RxSPTool.getString(context, "Token");
    }

    //设置会员Token
    public static void setToken(Context context, String Token) {
        RxSPTool.putString(context, "Token", Token);
    }

    //获取会员Lat
    public static String getLat(Context context) {
        return RxSPTool.getString(context, "Lat");
    }

    //设置会员Lat
    public static void setLat(Context context, String Token) {
        RxSPTool.putString(context, "Lat", Token);
    }

    //获取会员Log
    public static String getLng(Context context) {
        return RxSPTool.getString(context, "Lng");
    }

    //设置会员Log
    public static void setLng(Context context, String Token) {
        RxSPTool.putString(context, "Lng", Token);
    }

    //获取pos
    public static String getPosID(Context context) {
        return RxSPTool.getString(context, "PosID");
    }
    //获取pos
    public static void setPosID(Context context, String PosID) {
        RxSPTool.putString(context, "PosID", PosID);
    }

}
