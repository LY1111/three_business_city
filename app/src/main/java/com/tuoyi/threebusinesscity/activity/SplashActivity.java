package com.tuoyi.threebusinesscity.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import cn.qqtheme.framework.util.LogUtils;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread() {
            public void run() {
                try {
//                    Thread.sleep(300);
//                    Message msg = checkhandler.obtainMessage();
//                    checkhandler.sendMessage(msg);
                    Thread.sleep(2000);
                    toMain();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void toMain() {
        if (!TextUtils.isEmpty( UserBean.getToken(this))){
            RxActivityTool.skipActivityAndFinish(this, MainActivity.class);
        }else {
            RxActivityTool.skipActivityAndFinish(this, LoginActivity.class);
        }

    }
}
