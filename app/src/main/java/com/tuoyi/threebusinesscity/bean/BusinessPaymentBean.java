package com.tuoyi.threebusinesscity.bean;

public class BusinessPaymentBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"bizOrderNo":"2018071452979999","orderNo":"1018080885691387904","payInfo":"https://qr.alipay.com/bax04045nzpmsyidtwho4083","bizUserId":"HHHHH42"}
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
         * bizOrderNo : 2018071452979999
         * orderNo : 1018080885691387904
         * payInfo : https://qr.alipay.com/bax04045nzpmsyidtwho4083
         * bizUserId : HHHHH42
         */

        private String bizOrderNo;
        private String orderNo;
        private String payInfo;
        private String bizUserId;

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

        public String getPayInfo() {
            return payInfo;
        }

        public void setPayInfo(String payInfo) {
            this.payInfo = payInfo;
        }

        public String getBizUserId() {
            return bizUserId;
        }

        public void setBizUserId(String bizUserId) {
            this.bizUserId = bizUserId;
        }
    }
}
