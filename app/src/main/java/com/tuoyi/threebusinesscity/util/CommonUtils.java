package com.tuoyi.threebusinesscity.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class CommonUtils {
    //手机号
    public static boolean isMobilePhone(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //身份证号
    public static boolean isLegalId(String id) {

        return id.toUpperCase().matches("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
    }

    //拨打客服
    public final static void TelPhone(final Context mContext, String name, final String phone) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        builder.setMessage(name + ":" + phone);
        builder.setNegativeButton("拨号", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                android.net.Uri uri = android.net.Uri.parse("tel:" + phone);
                Intent it = new Intent(Intent.ACTION_DIAL, uri);
                mContext.startActivity(it);
            }
        });
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    //邮箱
    public static boolean isEmail(String email) {
        String strPattern = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        Pattern p = Pattern.compile(strPattern);

        Matcher m = p.matcher(email);
        return m.matches();
    }

    //密码必须为6-15位数字或字母组合
    public static boolean isPass(String pass) {
        String strPattern = "^[a-zA-Z0-9]{6,15}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    //交易密码必须为6位数字
    public static boolean isPayPass(String pass) {
        String strPattern = "^[0-9]{6}$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(pass);
        return m.matches();
    }

    //时间戳转时间
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*************************************************************
     * function: add bar to a date string , such as if the date string is
     * "20060912" , the return string is "2006-09-12" input : date string as
     * "20060912" output : date string as "2006-09-12" note : lp.cheng
     *********************************************************/
    public static String addBarToDateString(String dateStr) {
        String retDateStr = "";

        if (dateStr == null || dateStr.trim().length() < 8) {
            return dateStr;
        }

        retDateStr = dateStr.substring(0, 4) + "-" + dateStr.substring(4, 6) + "-" + dateStr.substring(6);

        return retDateStr;

    }


    /*************************************************************
     * function: 将银行卡号中间加星号处理 input : string as "9558801502203097716" output :
     * string as "955880******097716" note : lp.cheng
     *********************************************************/
    public static String addBarToBankCard(String bankAccount) {
        int length = bankAccount.length();
        return bankAccount.substring(0, 4) + "******" + bankAccount.substring(length - 4);

    }

    /*************************************************************
     * function: 手机号中间四位星号 input : string as "13621717784" output : string as
     * "136****7784" note : lp.cheng
     *********************************************************/
    public static String addBarToMobile(String mobile) {
        int length = mobile.length();
        if (length != 11)
            return mobile;
        return mobile.substring(0, 3) + "****" + mobile.substring(length - 4);
    }

    // 姓名隐藏
    public static String addBarToName(String name) {
        return name.substring(0, 1) + leftPad("", name.length() - 1, '*');
    }

    public static String leftPad(String str, int len, char ch) {
        StringBuffer nstr = new StringBuffer(len);

        int p = len - str.length();

        for (int i = 0; i < len; i++) {
            if (i < p)
                nstr.append(ch);
            else
                nstr.append(str.charAt(i - p));
        }
        return new String(nstr);
    }

    public static String moneyFormat(float amount) {
        return String.format("%.2f", amount);
    }

    public static boolean isTimeOK() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = "2017-10-26 00:00:00";
        try {
            Date beginTime = formatter.parse(date1);
            Date curTime = new Date(System.currentTimeMillis());
            if (((curTime.getTime() - beginTime.getTime()) / (24 * 60 * 60 * 1000)) >= 5)
                return true;
            else
                return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取手机宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取手机高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    //dp转px
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal,
                context.getResources().getDisplayMetrics());
    }
}
