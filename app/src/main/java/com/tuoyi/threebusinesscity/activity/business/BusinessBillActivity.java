package com.tuoyi.threebusinesscity.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.tuoyi.threebusinesscity.R;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxBarTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessBillActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.businessbill_list)
    RecyclerView businessbillList;
    @BindView(R.id.mainf_XRefreshView)
    XRefreshView mainfXRefreshView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessbill);
        RxActivityTool.addActivity(this);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.leftBack)
    public void onViewClicked() {
        RxActivityTool.finishActivity(this);
    }
}
