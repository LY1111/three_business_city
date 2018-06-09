package com.tuoyi.threebusinesscity.bean;

public class SubmitOrderBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"order_id":"65","subject":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒等商品","name":"李岩","pay_order_no":"2018060854565598","pay_total":1784,"uid":19}
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
         * order_id : 65
         * subject : 艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒等商品
         * name : 李岩
         * pay_order_no : 2018060854565598
         * pay_total : 1784
         * uid : 19
         */

        private String order_id;
        private String subject;
        private String name;
        private String pay_order_no;
        private int pay_total;
        private int uid;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay_order_no() {
            return pay_order_no;
        }

        public void setPay_order_no(String pay_order_no) {
            this.pay_order_no = pay_order_no;
        }

        public int getPay_total() {
            return pay_total;
        }

        public void setPay_total(int pay_total) {
            this.pay_total = pay_total;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
