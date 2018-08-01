package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.andview.refreshview.utils.LogUtils;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ViewPagerAdapter;
import com.tuoyi.threebusinesscity.bean.MessageEvent;
import com.tuoyi.threebusinesscity.fragment.OnLineCarFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineMyFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineShopFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineSortFragment;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.RxSPTool;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnLineShopActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.view_pager)
    FrameLayout viewPager;
    @BindView(R.id.mMain)
    RadioButton mMain;
    @BindView(R.id.mSort)
    RadioButton mSort;
    @BindView(R.id.mCar)
    RadioButton mCar;
    @BindView(R.id.mMine)
    RadioButton mMine;
    @BindView(R.id.tab_bottom)
    RadioGroup tabBottom;
    private ViewPagerAdapter mAdapter;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String where;       //哪里来？

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_shop);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (RxSPTool.getString(this, "where") != null) {
            where = RxSPTool.getString(this, "where");
            if ("GoodDetailsActivity".equals(where)) {
                tabBottom.check(R.id.mCar);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    /**
     * back键监听
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.e("111111111111");
        RxActivityTool.finishActivity(this);
    }


    /* 初始化界面 */
    private void initView() {
        /* 底部导航 */
        tabBottom.setOnCheckedChangeListener(this);
        fragmentList = getFragments();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fragment = fragmentList.get(0);
        transaction.replace(R.id.view_pager, fragment);
        transaction.commit();
    }

    /* 添加viewpager */
    public List<Fragment> getFragments() {
        fragmentList.add(OnLineShopFragment.newInstance());
        fragmentList.add(OnLineSortFragment.newInstance());
        fragmentList.add(OnLineCarFragment.newInstance());
        fragmentList.add(OnLineMyFragment.newInstance());
        return fragmentList;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case R.id.mMain:
                fragment = fragmentList.get(0);
                break;
            case R.id.mSort:
                fragment = fragmentList.get(1);
                break;
            case R.id.mCar:
                fragment = fragmentList.get(2);
                break;
            case R.id.mMine:
                fragment = fragmentList.get(3);
                break;
        }
        transaction.replace(R.id.view_pager, fragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if(EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }
}
