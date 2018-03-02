package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.fragment.MainFragment;
import com.tuoyi.threebusinesscity.fragment.MessageFragment;
import com.tuoyi.threebusinesscity.fragment.MyFragment;
import com.tuoyi.threebusinesscity.fragment.ShopFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //    @BindView(R.id.main_bottom_tab_bar)
//    BottomTabBar mainBottomTabBar;
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
    private MainFragment mMainFragment;
    private ShopFragment mShopFragment;
    private MessageFragment mMessageFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mainBottomTabBar.init(getSupportFragmentManager())
//                .setImgSize(90, 90)
//                .setFontSize(13)
//                .setTabPadding(4, 6, 10)
//                .setChangeColor(Color.GREEN, Color.RED)
//                .addTabItem("第一项", R.mipmap.ic_launcher, MainFragment.class)
//                .addTabItem("第二项", R.mipmap.ic_launcher, ShopFragment.class)
//                .addTabItem("第三项", R.mipmap.ic_launcher, MessageFragment.class)
//                .addTabItem("第四项", R.mipmap.ic_launcher, MyFragment.class)
//                //设置底部导航栏的背景图片。
////                .setTabBarBackgroundResource(R.mipmap.ic_launcher)
//                // 设置BottomTabBar的整体背景。
//                .setTabBarBackgroundColor(0xffffffff)
////                是否显示分割线
//                .isShowDivider(false)
//                //添加选项卡切换监听
//                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
//                    @Override
//                    public void onTabChange(int position, String name, View view) {
//                        Log.i("TGA", "位置：" + position + "      选项卡的文字内容：" + name);
//
//                    }
//                });
        /**/
        //                        FragmentManager fm = getSupportFragmentManager();
//                        FragmentTransaction beginTransaction = fm.beginTransaction();
//
//                        switch (position) {
//                            case 1:
//                                if (mMainFragment == null) {
//                                    mMainFragment = MainFragment.newInstance();
//                                }
//                                beginTransaction.replace(R.id.main_fl, mMainFragment);
//                                break;
//                            case 2:
//                                if (mShopFragment == null) {
//                                    mShopFragment = ShopFragment.newInstance();
//                                }
//                                beginTransaction.replace(R.id.main_fl, mShopFragment);
//                                break;
//                            case 3:
//                                if (mMessageFragment == null) {
//                                    mMessageFragment = MessageFragment.newInstance();
//                                }
//                                beginTransaction.replace(R.id.main_fl, mMessageFragment);
//                                break;
//                            case 4:
//                                if (mMyFragment == null) {
//                                    mMyFragment = MyFragment.newInstance();
//                                }
//                                beginTransaction.replace(R.id.main_fl, mMyFragment);
//                                break;
//                            default:
//                                if (mMainFragment == null) {
//                                    mMainFragment = MainFragment.newInstance();
//                                }
//                                beginTransaction.replace(R.id.main_fl, mMainFragment);
//                                break;
//                        }
//                        beginTransaction.commit();

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction beginTransaction = fm.beginTransaction();
                switch (checkedId) {
                    case R.id.main_rb_main:
                        if (mMainFragment == null) {
                            mMainFragment = MainFragment.newInstance();
                        }
                        beginTransaction.replace(R.id.main_fl, mMainFragment);
                        break;
                    case R.id.main_rb_shop:
                        if (mShopFragment == null) {
                            mShopFragment = ShopFragment.newInstance();
                        }
                        beginTransaction.replace(R.id.main_fl, mShopFragment);
                        break;
                    case R.id.main_rb_message:
                        if (mMessageFragment == null) {
                            mMessageFragment = MessageFragment.newInstance();
                        }
                        beginTransaction.replace(R.id.main_fl, mMessageFragment);
                        break;
                    case R.id.main_rb_my:
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
        });
        mainRg.check(R.id.main_rb_main);
    }

    @OnClick(R.id.main_rb_sao)
    public void onViewClicked() {
    }
}
