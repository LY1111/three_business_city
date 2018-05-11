package com.tuoyi.threebusinesscity.base;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
//import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by md
 * on 2018-4-17 11:15:15.
 */

public class MyApplication extends Application {
    private static MyApplication appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
//        JPushInterface.setDebugMode(true);//极光推送测试
//        JPushInterface.init(this);//极光推送
//        RxTool.init(this);//工具包
        MultiDex.install(this);//兼容包
//        PgyCrashManager.register(this);//蒲公英
        Logger.addLogAdapter(new AndroidLogAdapter() {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });//json日志
    }

    /**
     * 自定义通知栏样式
     */
    private void customizeNotification() {
//        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);//class
//      builder.statusBarDrawable = R.drawable.ic_ico;
//
//      builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
//              | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
//      builder.notificationDefaults = Notification.DEFAULT_SOUND
//              | Notification.DEFAULT_VIBRATE
//              | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
////        JPushInterface.setPushNotificationBuilder(1, builder);
//        JPushInterface.setDefaultPushNotificationBuilder(builder);


//        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this,
//                R.layout.customer_notitfication_layout_one,
//                R.id.m_icon,
//                R.id.m_title,
//                R.id.m_text);
//        // 指定定制的 Notification Layout
//        builder.statusBarDrawable = R.mipmap.ic_theme_logo;
//        // 指定最顶层状态栏小图标
//        builder.layoutIconDrawable = R.mipmap.ic_theme_logo;
//        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
//                | Notification.FLAG_SHOW_LIGHTS;
//        builder.notificationDefaults = Notification.DEFAULT_SOUND//声音
//                | Notification.DEFAULT_VIBRATE//振动
//                | Notification.DEFAULT_LIGHTS;//闪光灯
//        // 指定下拉状态栏时显示的通知图标
////        JPushInterface.setPushNotificationBuilder(2, builder);
////        Toast.makeText(this, "初始化了", Toast.LENGTH_SHORT).show();
//        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }
}
