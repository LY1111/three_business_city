package com.tuoyi.threebusinesscity.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.vondear.rxtools.RxBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessReceivableActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessreceivable);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
    }

    @OnClick(R.id.leftBack)
    public void onViewClicked() {
        RxActivityTool.finishActivity(this);
    }
}
