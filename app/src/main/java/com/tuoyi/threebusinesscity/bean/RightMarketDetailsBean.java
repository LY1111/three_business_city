package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/3/7 0007.
 */

public class RightMarketDetailsBean {
    private String img;
    private String title;
    private int num;
    private String money;
    private boolean isSelect;

    public RightMarketDetailsBean(int num, String money) {
        this.num = num;
        this.money = money;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
