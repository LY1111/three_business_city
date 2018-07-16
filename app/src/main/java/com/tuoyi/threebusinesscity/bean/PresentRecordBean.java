package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class PresentRecordBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"order_num":"2018071357989910","add_time":1531451418,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071352521014","add_time":1531451764,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071357571025","add_time":1531451802,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071351981025","add_time":1531452500,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071399525151","add_time":1531452668,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071349101535","add_time":1531452834,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071349985510","add_time":1531452914,"money":"1.00","payFailMessage":"","state":1,"card_number":""},{"order_num":"2018071399539999","add_time":1531453181,"money":"1.00","payFailMessage":"","state":1,"card_number":"6228480286927636469"},{"order_num":"2018071353559856","add_time":1531453494,"money":"1.00","payFailMessage":"","state":1,"card_number":"6228480286927636469"},{"order_num":"2018071351559999","add_time":1531463844,"money":"1.00","payFailMessage":"","state":1,"card_number":"6228480286927636469"}]
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
         * order_num : 2018071357989910
         * add_time : 1531451418
         * money : 1.00
         * payFailMessage :
         * state : 1
         * card_number :
         */

        private String order_num;
        private int add_time;
        private String money;
        private String payFailMessage;
        private int state;
        private String card_number;

        public String getOrder_num() {
            return order_num;
        }

        public void setOrder_num(String order_num) {
            this.order_num = order_num;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPayFailMessage() {
            return payFailMessage;
        }

        public void setPayFailMessage(String payFailMessage) {
            this.payFailMessage = payFailMessage;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }
    }
}
