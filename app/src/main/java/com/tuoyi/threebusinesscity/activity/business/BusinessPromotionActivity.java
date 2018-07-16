package com.tuoyi.threebusinesscity.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessPromotionActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesspromotion);
        RxActivityTool.addActivity(this);
        ButterKnife.bind(this);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
    }

    @OnClick({R.id.leftBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;

        }
    }
}
