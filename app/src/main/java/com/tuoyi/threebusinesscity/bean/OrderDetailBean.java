package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class OrderDetailBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"order":{"order_num_alias":"2018060854565598","order_id":65,"shipping_name":"李岩","shipping_tel":"17661044588","address":"我家住在黄土高坡~","shipping_city_id":177,"shipping_country_id":2163,"shipping_province_id":11},"order_product":[{"order_goods_id":101,"price":"1483.00","pay_points":"5000.00","points":"50.00","quantity":1,"image":"images/osc1/12/1.jpg","option_list":[{"name":"颜色","value":"红色"},{"name":"爱的选项","value":"露珠"},{"name":"款式1","value":"佛手荷花"}]},{"order_goods_id":102,"price":"301.00","pay_points":"5000.00","points":"50.00","quantity":1,"image":"images/osc1/12/1.jpg","option_list":[{"name":"颜色","value":"蓝色"},{"name":"爱的选项","value":"玫瑰"},{"name":"款式1","value":"荷花鱼"}]}]}
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
         * order : {"order_num_alias":"2018060854565598","order_id":65,"shipping_name":"李岩","shipping_tel":"17661044588","address":"我家住在黄土高坡~","shipping_city_id":177,"shipping_country_id":2163,"shipping_province_id":11}
         * order_product : [{"order_goods_id":101,"price":"1483.00","pay_points":"5000.00","points":"50.00","quantity":1,"image":"images/osc1/12/1.jpg","option_list":[{"name":"颜色","value":"红色"},{"name":"爱的选项","value":"露珠"},{"name":"款式1","value":"佛手荷花"}]},{"order_goods_id":102,"price":"301.00","pay_points":"5000.00","points":"50.00","quantity":1,"image":"images/osc1/12/1.jpg","option_list":[{"name":"颜色","value":"蓝色"},{"name":"爱的选项","value":"玫瑰"},{"name":"款式1","value":"荷花鱼"}]}]
         */

        private OrderBean order;
        private List<OrderProductBean> order_product;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<OrderProductBean> getOrder_product() {
            return order_product;
        }

        public void setOrder_product(List<OrderProductBean> order_product) {
            this.order_product = order_product;
        }

        public static class OrderBean {
            /**
             * order_num_alias : 2018060854565598
             * order_id : 65
             * shipping_name : 李岩
             * shipping_tel : 17661044588
             * address : 我家住在黄土高坡~
             * shipping_city_id : 177
             * shipping_country_id : 2163
             * shipping_province_id : 11
             */

            private String order_num_alias;
            private int order_id;
            private String shipping_name;
            private String shipping_tel;
            private String address;
            private int shipping_city_id;
            private int shipping_country_id;
            private int shipping_province_id;

            public String getOrder_num_alias() {
                return order_num_alias;
            }

            public void setOrder_num_alias(String order_num_alias) {
                this.order_num_alias = order_num_alias;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getShipping_name() {
                return shipping_name;
            }

            public void setShipping_name(String shipping_name) {
                this.shipping_name = shipping_name;
            }

            public String getShipping_tel() {
                return shipping_tel;
            }

            public void setShipping_tel(String shipping_tel) {
                this.shipping_tel = shipping_tel;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getShipping_city_id() {
                return shipping_city_id;
            }

            public void setShipping_city_id(int shipping_city_id) {
                this.shipping_city_id = shipping_city_id;
            }

            public int getShipping_country_id() {
                return shipping_country_id;
            }

            public void setShipping_country_id(int shipping_country_id) {
                this.shipping_country_id = shipping_country_id;
            }

            public int getShipping_province_id() {
                return shipping_province_id;
            }

            public void setShipping_province_id(int shipping_province_id) {
                this.shipping_province_id = shipping_province_id;
            }
        }

        public static class OrderProductBean {
            /**
             * order_goods_id : 101
             * price : 1483.00
             * pay_points : 5000.00
             * points : 50.00
             * quantity : 1
             * image : images/osc1/12/1.jpg
             * option_list : [{"name":"颜色","value":"红色"},{"name":"爱的选项","value":"露珠"},{"name":"款式1","value":"佛手荷花"}]
             */

            private int order_goods_id;
            private String price;
            private String pay_points;
            private String points;
            private int quantity;
            private String image;
            private List<OptionListBean> option_list;

            public int getOrder_goods_id() {
                return order_goods_id;
            }

            public void setOrder_goods_id(int order_goods_id) {
                this.order_goods_id = order_goods_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getPay_points() {
                return pay_points;
            }

            public void setPay_points(String pay_points) {
                this.pay_points = pay_points;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<OptionListBean> getOption_list() {
                return option_list;
            }

            public void setOption_list(List<OptionListBean> option_list) {
                this.option_list = option_list;
            }

            public static class OptionListBean {
                /**
                 * name : 颜色
                 * value : 红色
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
