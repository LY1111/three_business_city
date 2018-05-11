package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * on 2018/4/27 0027.
 */

public class GeneralDetailsBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"business":{"uid":6,"telephone":"17605382423","total_sales":"0.00","address":"山东省济南市天桥区北坦街道明湖广场","is_classification":1,"shop_name":"小转转","image":"images/business6/9-160G50U922.jpg","member":"75.00","distance":0},"goods":[{"goods_id":40,"name":"测试新的","price":"30.00","image":"images/business6/9-160G50U922.jpg"},{"goods_id":39,"name":"测试商品一号","price":"10.00","image":"images/business6/9-160G50U922.jpg"}]}
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
         * business : {"uid":6,"telephone":"17605382423","total_sales":"0.00","address":"山东省济南市天桥区北坦街道明湖广场","is_classification":1,"shop_name":"小转转","image":"images/business6/9-160G50U922.jpg","member":"75.00","distance":0}
         * goods : [{"goods_id":40,"name":"测试新的","price":"30.00","image":"images/business6/9-160G50U922.jpg"},{"goods_id":39,"name":"测试商品一号","price":"10.00","image":"images/business6/9-160G50U922.jpg"}]
         */

        private BusinessBean business;
        private List<GoodsBean> goods;

        public BusinessBean getBusiness() {
            return business;
        }

        public void setBusiness(BusinessBean business) {
            this.business = business;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class BusinessBean {
            /**
             * uid : 6
             * telephone : 17605382423
             * total_sales : 0.00
             * address : 山东省济南市天桥区北坦街道明湖广场
             * is_classification : 1
             * shop_name : 小转转
             * image : images/business6/9-160G50U922.jpg
             * member : 75.00
             * distance : 0
             */

            private int uid;
            private String telephone;
            private String total_sales;
            private String address;
            private int is_classification;
            private String shop_name;
            private String image;
            private String member;
            private double distance;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getTotal_sales() {
                return total_sales;
            }

            public void setTotal_sales(String total_sales) {
                this.total_sales = total_sales;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getIs_classification() {
                return is_classification;
            }

            public void setIs_classification(int is_classification) {
                this.is_classification = is_classification;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getMember() {
                return member;
            }

            public void setMember(String member) {
                this.member = member;
            }

            public double getDistance() {
                return distance;
            }

            public void setDistance(double distance) {
                this.distance = distance;
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 40
             * name : 测试新的
             * price : 30.00
             * image : images/business6/9-160G50U922.jpg
             */

            private int goods_id;
            private String name;
            private String price;
            private String image;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }
    }
}
