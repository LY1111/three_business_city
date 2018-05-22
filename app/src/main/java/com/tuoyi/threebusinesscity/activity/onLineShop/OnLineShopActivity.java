package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ViewPagerAdapter;
import com.tuoyi.threebusinesscity.fragment.OnLineShopFragment;

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
    private FragmentManager fm;
    private Fragment fragment;
    private FragmentTransaction transaction;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_shop);
        ButterKnife.bind(this);
        initView();
    }

    /* 初始化界面 */
    private void initView() {
        /* 底部导航 */
        tabBottom.setOnCheckedChangeListener(this);
        fragmentList = getFragments();
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        fragment = fragmentList.get(0);
        transaction.replace(R.id.view_pager, fragment);
        transaction.commit();
    }

    /* 添加viewpager */
    public List<Fragment> getFragments() {
        fragmentList.add(new OnLineShopFragment());
//        fragmentList.add(new ExpressFragment());
//        fragmentList.add(new RecruitFragment());
//        fragmentList.add(new BidFragment());
//        fragmentList.add(new CompanyFragment());
        return fragmentList;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        switch (i) {
            case R.id.mMain:
                fragment = fragmentList.get(0);
                transaction.replace(R.id.view_pager, fragment);
                break;
//            case R.id.mSort:
//                fragment = fragmentList.get(1);
//                transaction.replace(R.id.view_pager, fragment);
//                break;
//            case R.id.mCar:
//                fragment = fragmentList.get(2);
//                transaction.replace(R.id.view_pager, fragment);
//                break;
//            case R.id.mMine:
//                fragment = fragmentList.get(3);
//                transaction.replace(R.id.view_pager, fragment);
//                break;
        }
        transaction.commit();
    }
}
