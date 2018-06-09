package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class OnLineShopAdBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"value":"5","picurl":"images/osc1/2/2.jpg"},{"value":"6","picurl":"images/osc1/3/m02.jpg"},{"value":"7","picurl":"images/osc1/4/m07.jpg"}]
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
         * value : 5
         * picurl : images/osc1/2/2.jpg
         */

        private String value;
        private String picurl;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
