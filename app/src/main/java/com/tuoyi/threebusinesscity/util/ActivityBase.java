package com.tuoyi.threebusinesscity.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by md
 * on 2018/3/2 0002.
 */

public class ActivityBase extends FragmentActivity {
    public ActivityBase mContext;

    public ActivityBase() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        RxActivityTool.addActivity(this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
