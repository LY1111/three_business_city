package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.MyLocationActivity;
import com.tuoyi.threebusinesscity.fragment.OrderForm.WholeFragment;
import com.tuoyi.threebusinesscity.util.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OnLineMyFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tv_location)
    TextView tv_location;
    Unbinder unbinder;

    private List<Fragment> mFragments;
    private List<String> tabIndicators;
    private MyPagerAdapter contentAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_online, container, false);
        unbinder = ButterKnife.bind(this, view);

        initContent();
        initTab();

        //我的收货地址
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.newInstance().jumpRight(getActivity(), MyLocationActivity.class);
            }
        });
        return view;
    }

    private void initTab() {
        //显示Tab模式（默认、滑动）
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.gray), ContextCompat.getColor(getActivity(), R.color.colorAccent));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));
        ViewCompat.setElevation(mTabLayout, 10);
        mTabLayout.setupWithViewPager(viewpager);


        //底部导航栏
       /* for (int i = 0; i < tabIndicators.size(); i++) {
            TabLayout.Tab itemTab = mTabTl.getTabAt(i);
            if (itemTab != null) {
                itemTab.setCustomView(R.layout.item_tab_layout_custom);
                TextView itemTv = (TextView) itemTab.getCustomView().findViewById(R.id.tv_menu_item);
                itemTv.setText(tabIndicators.get(i));
            }
        }
        mTabTl.getTabAt(0).getCustomView().setSelected(true);*/
    }

    private void initContent() {
        tabIndicators = new ArrayList<>();
        tabIndicators.add("全部");
        tabIndicators.add("待付款");
        tabIndicators.add("待发货");
        tabIndicators.add("已发货");
        tabIndicators.add("已取消");
        tabIndicators.add("已完成");
        /*for (int i = 0; i < 4; i++) {
            tabIndicators.add("Tab " + i);
        }*/
        mFragments = new ArrayList<>();
        //添加同一个Fragment
        /*for (String s : tabIndicators) {
            tabFragments.add(TabContentFragment.newInstance(s));
        }*/
        mFragments = new ArrayList<>();
        mFragments.add(WholeFragment.newInstance(0));    //全部
        mFragments.add(WholeFragment.newInstance(3));  //待付款
        mFragments.add(WholeFragment.newInstance(1));  //待收货
        mFragments.add(WholeFragment.newInstance(4)); //待评价
        mFragments.add(WholeFragment.newInstance(5));    //售后
        mFragments.add(WholeFragment.newInstance(2));    //售后

        contentAdapter = new MyPagerAdapter(getChildFragmentManager());
        viewpager.setAdapter(contentAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {

            return mFragments.size();
        }

        //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return tabIndicators.get(position);
        }
    }
}
