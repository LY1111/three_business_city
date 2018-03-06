package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * 首页底部商家实体类
 * on 2018/3/3 0003.
 */

public class MainBottomShopBean {
    private int mImg;
    private String title;

    public int getmImg() {
        return mImg;
    }

    public void setmImg(int mImg) {
        this.mImg = mImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MainBottomShopBean(int mImg, String title) {
        this.mImg = mImg;
        this.title = title;
    }
}
