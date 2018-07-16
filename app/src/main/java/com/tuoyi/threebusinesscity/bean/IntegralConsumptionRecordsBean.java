package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * on 2018/3/7 0007.
 * 消费积分详情实体类
 */

public class IntegralConsumptionRecordsBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"money":"10000.00","order_time":1531470735,"order_status":0,"business_shop_name":""},{"money":"10000.00","order_time":1531470794,"order_status":0,"business_shop_name":""},{"money":"10000.00","order_time":1531470808,"order_status":0,"business_shop_name":""},{"money":"10000.00","order_time":1531470840,"order_status":0,"business_shop_name":""},{"money":"10095.00","order_time":1531470877,"order_status":0,"business_shop_name":""},{"money":"10095.00","order_time":1531471153,"order_status":0,"business_shop_name":""},{"money":"10095.00","order_time":1531471158,"order_status":0,"business_shop_name":""},{"money":"1000.00","order_time":1531472806,"order_status":0,"business_shop_name":""},{"money":"1000.00","order_time":1531472969,"order_status":0,"business_shop_name":"真实测试一"},{"money":"500.00","order_time":1531535144,"order_status":0,"business_shop_name":"真实测试一"}]
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
         * money : 10000.00
         * order_time : 1531470735
         * order_status : 0
         * business_shop_name :
         */

        private String money;
        private int order_time;
        private int order_status;
        private String business_shop_name;

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getOrder_time() {
            return order_time;
        }

        public void setOrder_time(int order_time) {
            this.order_time = order_time;
        }

        public int getOrder_status() {
            return order_status;
        }

        public void setOrder_status(int order_status) {
            this.order_status = order_status;
        }

        public String getBusiness_shop_name() {
            return business_shop_name;
        }

        public void setBusiness_shop_name(String business_shop_name) {
            this.business_shop_name = business_shop_name;
        }
    }
}
