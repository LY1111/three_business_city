package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.fragment.MainFragment;
import com.tuoyi.threebusinesscity.fragment.MessageFragment;
import com.tuoyi.threebusinesscity.fragment.MyFragment;
import com.tuoyi.threebusinesscity.fragment.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_fl)
    FrameLayout mainFl;
    @BindView(R.id.main_rb_main)
    RadioButton mainRbMain;
    @BindView(R.id.main_rb_shop)
    RadioButton mainRbShop;
    @BindView(R.id.main_rb_sao)
    LinearLayout mainRbSao;
    @BindView(R.id.main_rb_message)
    RadioButton mainRbMessage;
    @BindView(R.id.main_rb_my)
    RadioButton mainRbMy;
    @BindView(R.id.main_rg)
    RadioGroup mainRg;
    @BindView(R.id.main_rb_sao_img)
    ImageView mainRbSaoImg;
    @BindView(R.id.main_rb_sao_tv)
    TextView mainRbSaoTv;
    private MainFragment mMainFragment;
    private ShopFragment mShopFragment;
    private MessageFragment mMessageFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListener();
//        initFragment(changIndexListener.changIndex());

    }

    /**
     * 底部导航栏点击事件
     */
    private void initListener() {
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.main_rb_main:
                        initFragment(1);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                    case R.id.main_rb_shop:
                        initFragment(2);
                        mainRbSaoTv.setText("购物车");
                        break;
                    case R.id.main_rb_message:
                        initFragment(3);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                    case R.id.main_rb_my:
                        initFragment(4);
                        mainRbSaoTv.setText("扫一扫");
                        break;
                }

            }
        });
        mainRg.check(R.id.main_rb_main);


    }

    /**
     * 加载Fragment方法
     */
    public void initFragment(int index) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        switch (index) {
            case 1:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMainFragment);
                break;
            case 2:
                if (mShopFragment == null) {
                    mShopFragment = ShopFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mShopFragment);
                break;
            case 3:
                if (mMessageFragment == null) {
                    mMessageFragment = MessageFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMessageFragment);
                break;
            case 4:
                if (mMyFragment == null) {
                    mMyFragment = MyFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMyFragment);
                break;
            default:
                if (mMainFragment == null) {
                    mMainFragment = MainFragment.newInstance();
                }
                beginTransaction.replace(R.id.main_fl, mMainFragment);
                break;
        }
        beginTransaction.commit();
    }
    @OnClick(R.id.main_rb_sao)//二维码扫描
    public void onViewClicked() {
        Toast.makeText(this, mainRbSaoTv.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}