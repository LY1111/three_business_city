package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/3/3 0003.
 * 网格菜单实体类
 */

public class MainGridMenuBean {
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

    public MainGridMenuBean(int mImg, String title) {
        this.mImg = mImg;
        this.title = title;
    }
}
