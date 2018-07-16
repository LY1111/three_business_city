package com.tuoyi.threebusinesscity.bean;

public class PutForwardBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"bizOrderNo":"2018071352521014","orderNo":"1017608571895943168"}
     */

    private int status;
    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bizOrderNo : 2018071352521014
         * orderNo : 1017608571895943168
         */

        private String bizOrderNo;
        private String orderNo;

        public String getBizOrderNo() {
            return bizOrderNo;
        }

        public void setBizOrderNo(String bizOrderNo) {
            this.bizOrderNo = bizOrderNo;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
    }
}
