package com.tuoyi.threebusinesscity.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

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
                    Thread.sleep(3000);
                    toMain();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void toMain() {
        RxActivityTool.skipActivityAndFinish(this, LoginActivity.class);
    }
}
