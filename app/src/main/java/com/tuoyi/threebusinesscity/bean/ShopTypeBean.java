package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/12 0012.
 */

public class ShopTypeBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"id":1,"name":"A型","platform":"10.00","business":"5.00","member":"50.00"},{"id":2,"name":"B型","platform":"15.00","business":"7.50","member":"75.00"},{"id":4,"name":"C型","platform":"20.00","business":"10.00","member":"100.00"},{"id":5,"name":"D型","platform":"25.00","business":"12.50","member":"125.00"},{"id":6,"name":"E型","platform":"30.00","business":"15.00","member":"150.00"},{"id":7,"name":"E型","platform":"17.50","business":"17.50","member":"175.00"},{"id":8,"name":"G型","platform":"40.00","business":"20.00","member":"200.00"}]
     */

    private int status;
    private int code;
    private String message;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : A型
         * platform : 10.00
         * business : 5.00
         * member : 50.00
         */

        private int id;
        private String name;
        private String platform;
        private String business;
        private String member;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getBusiness() {
            return business;
        }

        public void setBusiness(String business) {
            this.business = business;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }
    }
}
