package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.OrderPagerAdapter;
import com.tuoyi.threebusinesscity.adapter.ViewPagerAdapter;
import com.tuoyi.threebusinesscity.fragment.OnLineCarFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineMyFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineShopFragment;
import com.tuoyi.threebusinesscity.fragment.OnLineSortFragment;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OnLineShopActivity1 extends AppCompatActivity {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    NoScrollViewPager viewPager;
    private int[] tab_imgs;
    private String[] tab_titles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_line_shop1);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);

        initViewPager();
    }


    private void initViewPager() {
        // 创建一个集合,装填Fragment
        List<Fragment> fragments = new ArrayList<>();
        // 装填

        fragments.add(OnLineShopFragment.newInstance());
        fragments.add(OnLineSortFragment.newInstance());
        fragments.add(OnLineCarFragment.newInstance());
        fragments.add(OnLineMyFragment.newInstance());

        tab_imgs = new int[]{R.drawable.select_main_menu_a, R.drawable.selector_sort, R.drawable.selector_shop_car, R.drawable.select_main_menu_d};
        tab_titles=new String[]{"首页","分类","购物车","我的订单"};
        setTabs(tab_titles, tab_imgs);

        // 创建ViewPager适配器
        OrderPagerAdapter myPagerAdapter = new OrderPagerAdapter(this, getSupportFragmentManager(), fragments);

        // 给ViewPager设置适配器
        viewPager.setAdapter(myPagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }

    /**
     * 设置添加Tab * @param tab_titles tab条目名字 * @param tab_imgs tab上条目上的图片
     */
    private void setTabs(String[] tab_titles, int[] tab_imgs) {

        for (int i = 0; i < tab_imgs.length; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            View view = getLayoutInflater().inflate(R.layout.item_pager, null);
            tab.setCustomView(view);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_des);
            tvTitle.setText(tab_titles[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.iv_top);
            imgTab.setImageResource(tab_imgs[i]);
            mTabLayout.addTab(tab);
        }

    }


}

