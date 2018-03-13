package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/3/7 0007.
 * 我的推广商家实体类
 */

public class MyPromoterBean {
    private String promoterName;

    public MyPromoterBean(String promoterName) {
        this.promoterName = promoterName;
    }

    public String getPromoterName() {
        return promoterName;
    }

    public void setPromoterName(String promoterName) {
        this.promoterName = promoterName;
    }
}
