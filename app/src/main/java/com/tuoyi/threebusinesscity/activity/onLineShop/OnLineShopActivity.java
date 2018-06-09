package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.andview.refreshview.utils.LogUtils;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ViewPagerAdapter;
import com.tuoyi.threebusinesscity.fragment.OnLineCarFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineMyFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineShopFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineSortFragment;

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
        where = getIntent().getStringExtra("where");

        initView();
    }

    /**
     * back键监听
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtils.e("111111111111");
        setResult(RESULT_OK);
        finish();
    }


    /* 初始化界面 */
    private void initView() {
        /* 底部导航 */
        tabBottom.setOnCheckedChangeListener(this);
        fragmentList = getFragments();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        if (!TextUtils.isEmpty(where)) {
            if ("detail".equals(where)) {
                fragment = fragmentList.get(2);
                mCar.setChecked(true);
            }
        } else {
            fragment = fragmentList.get(0);
        }

        transaction.replace(R.id.view_pager, fragment);
        transaction.commit();
    }

    /* 添加viewpager */
    public List<Fragment> getFragments() {
        fragmentList.add(new OnLineShopFragment());
        fragmentList.add(new OnLineSortFragment());
        fragmentList.add(new OnLineCarFragment());
        fragmentList.add(new OnLineMyFragment());
        return fragmentList;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (i) {
            case R.id.mMain:
                fragment = fragmentList.get(0);
                transaction.replace(R.id.view_pager, fragment);
                break;
            case R.id.mSort:
                fragment = fragmentList.get(1);
                transaction.replace(R.id.view_pager, fragment);
                break;
            case R.id.mCar:
                fragment = fragmentList.get(2);
                transaction.replace(R.id.view_pager, fragment);
                break;
            case R.id.mMine:
                fragment = fragmentList.get(3);
                transaction.replace(R.id.view_pager, fragment);
                break;
        }
        transaction.commit();
    }
}
