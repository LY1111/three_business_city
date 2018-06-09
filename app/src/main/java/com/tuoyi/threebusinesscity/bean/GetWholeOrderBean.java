package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class GetWholeOrderBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"order":{"order_id":65,"order_num_alias":"2018060854565598","order_status_id":3,"return_points":100,"pay_points":10000,"total":"1784.00"},"order_goods":[{"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","quantity":1,"price":"1483.00","image":"images/osc1/12/1.jpg","total":"1483.00","points":"50.00","pay_points":"5000.00","total_pay_points":5000,"total_return_points":5000},{"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","quantity":1,"price":"301.00","image":"images/osc1/12/1.jpg","total":"301.00","points":"50.00","pay_points":"5000.00","total_pay_points":5000,"total_return_points":5000}]}]
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
         * order : {"order_id":65,"order_num_alias":"2018060854565598","order_status_id":3,"return_points":100,"pay_points":10000,"total":"1784.00"}
         * order_goods : [{"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","quantity":1,"price":"1483.00","image":"images/osc1/12/1.jpg","total":"1483.00","points":"50.00","pay_points":"5000.00","total_pay_points":5000,"total_return_points":5000},{"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","quantity":1,"price":"301.00","image":"images/osc1/12/1.jpg","total":"301.00","points":"50.00","pay_points":"5000.00","total_pay_points":5000,"total_return_points":5000}]
         */

        private OrderBean order;
        private List<OrderGoodsBean> order_goods;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<OrderGoodsBean> getOrder_goods() {
            return order_goods;
        }

        public void setOrder_goods(List<OrderGoodsBean> order_goods) {
            this.order_goods = order_goods;
        }

        public static class OrderBean {
            /**
             * order_id : 65
             * order_num_alias : 2018060854565598
             * order_status_id : 3
             * return_points : 100
             * pay_points : 10000
             * total : 1784.00
             */

            private int order_id;
            private String order_num_alias;
            private int order_status_id;
            private int return_points;
            private int pay_points;
            private String total;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_num_alias() {
                return order_num_alias;
            }

            public void setOrder_num_alias(String order_num_alias) {
                this.order_num_alias = order_num_alias;
            }

            public int getOrder_status_id() {
                return order_status_id;
            }

            public void setOrder_status_id(int order_status_id) {
                this.order_status_id = order_status_id;
            }

            public int getReturn_points() {
                return return_points;
            }

            public void setReturn_points(int return_points) {
                this.return_points = return_points;
            }

            public int getPay_points() {
                return pay_points;
            }

            public void setPay_points(int pay_points) {
                this.pay_points = pay_points;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }
        }

        public static class OrderGoodsBean {
            /**
             * name : 艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒
             * quantity : 1
             * price : 1483.00
             * image : images/osc1/12/1.jpg
             * total : 1483.00
             * points : 50.00
             * pay_points : 5000.00
             * total_pay_points : 5000
             * total_return_points : 5000
             */

            private String name;
            private int quantity;
            private String price;
            private String image;
            private String total;
            private String points;
            private String pay_points;
            private int total_pay_points;
            private int total_return_points;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getPay_points() {
                return pay_points;
            }

            public void setPay_points(String pay_points) {
                this.pay_points = pay_points;
            }

            public int getTotal_pay_points() {
                return total_pay_points;
            }

            public void setTotal_pay_points(int total_pay_points) {
                this.total_pay_points = total_pay_points;
            }

            public int getTotal_return_points() {
                return total_return_points;
            }

            public void setTotal_return_points(int total_return_points) {
                this.total_return_points = total_return_points;
            }
        }
    }
}
