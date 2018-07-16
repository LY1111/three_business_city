package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class MyIncomeBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"total_bonus":"3.00","order_time":1531536323},{"total_bonus":"3.00","order_time":1531536434},{"total_bonus":"0.00","order_time":1531557580}]
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
         * total_bonus : 3.00
         * order_time : 1531536323
         */

        private String total_bonus;
        private int order_time;

        public String getTotal_bonus() {
            return total_bonus;
        }

        public void setTotal_bonus(String total_bonus) {
            this.total_bonus = total_bonus;
        }

        public int getOrder_time() {
            return order_time;
        }

        public void setOrder_time(int order_time) {
            this.order_time = order_time;
        }
    }
}
