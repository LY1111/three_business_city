package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.RxIntentTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* 我是商家 */
public class MyBusinessActivity extends AppCompatActivity {

    @BindView(R.id.mPhone)
    EditText mPhone;
    @BindView(R.id.mCode)
    EditText mCode;
    @BindView(R.id.mGetCode)
    TextView mGetCode;
    @BindView(R.id.mPasswordA)
    EditText mPasswordA;
    @BindView(R.id.mPasswordB)
    EditText mPasswordB;
    @BindView(R.id.mInvite)
    EditText mInvite;
    @BindView(R.id.mType)
    TextView mType;
    @BindView(R.id.mSort)
    TextView mSort;
    @BindView(R.id.mCardNum)
    EditText mCardNum;
    @BindView(R.id.mCardName)
    EditText mCardName;
    @BindView(R.id.mShopName)
    EditText mShopName;
    @BindView(R.id.mShopAddress)
    TextView mShopAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mGetCode, R.id.mType, R.id.mSort, R.id.mShopAddress, R.id.mBtn, R.id.mLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mGetCode:
                //获取验证码
                break;
            case R.id.mType:
                //商家类型
                RxActivityTool.skipActivityForResult(this,ShopTypeActivity.class,001);
                break;
            case R.id.mSort:
                //商家分类
                break;
            case R.id.mShopAddress:
                //商家地址
                break;
            case R.id.mBtn:
                //注册
                break;
            case R.id.mLogin:
                //去往登录
                break;
        }
    }
}
