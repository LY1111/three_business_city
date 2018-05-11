package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/4/28 0028.
 */

public class MyGridMenuBean {
    private int img;
    private String name;

    public MyGridMenuBean(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
