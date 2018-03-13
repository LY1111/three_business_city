package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/3/7 0007.
 * 我的会员实体类
 */

public class MyMembersBean {
    private String memberName;

    public MyMembersBean(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
