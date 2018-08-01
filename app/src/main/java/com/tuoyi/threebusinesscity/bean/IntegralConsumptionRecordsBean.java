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
     * data : [{"money":1,"order_time":1531897885,"business_shop_name":"自营平台消费","return_points":5},{"money":1,"order_time":1531899355,"business_shop_name":"自营平台消费","return_points":0},{"money":5000.33,"order_time":1531905796,"business_shop_name":"李岩测试店铺","return_points":0},{"money":1,"order_time":1531909064,"business_shop_name":"自营平台消费","return_points":0},{"money":1,"order_time":1531965229,"business_shop_name":"自营平台消费","return_points":0},{"money":1,"order_time":1531965268,"business_shop_name":"自营平台消费","return_points":0},{"money":1,"order_time":1531987012,"business_shop_name":"自营平台消费","return_points":5},{"money":1,"order_time":1531990732,"business_shop_name":"自营平台消费","return_points":5},{"money":10,"order_time":1532058023,"business_shop_name":"何敬之店铺","return_points":0.05}]
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
         * money : 1
         * order_time : 1531897885
         * business_shop_name : 自营平台消费
         * return_points : 5
         */

        private float money;
        private int order_time;
        private String business_shop_name;
        private float return_points;

        public float getMoney() {
            return money;
        }

        public void setMoney(float money) {
            this.money = money;
        }

        public int getOrder_time() {
            return order_time;
        }

        public void setOrder_time(int order_time) {
            this.order_time = order_time;
        }

        public String getBusiness_shop_name() {
            return business_shop_name;
        }

        public void setBusiness_shop_name(String business_shop_name) {
            this.business_shop_name = business_shop_name;
        }

        public float getReturn_points() {
            return return_points;
        }

        public void setReturn_points(float return_points) {
            this.return_points = return_points;
        }
    }
}
