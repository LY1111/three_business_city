package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * 首页底部商家实体类
 * on 2018/3/3 0003.
 */

public class MainBottomShopBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"uid":7,"address":"山东省济南市天桥区北坦街道明湖广场","is_classification":1,"shop_name":"锤子","member":"50.00","distance":1870.48},{"uid":10,"address":"山东省济南市天桥区北坦街道明湖广场","is_classification":0,"shop_name":"冲锋枪","member":"125.00","distance":1871.68},{"uid":8,"address":"山东省济南市天桥区北坦街道明湖广场","is_classification":0,"shop_name":"大铁棒","member":"100.00","distance":1872.12},{"uid":6,"address":"山东省济南市天桥区北坦街道明湖广场","is_classification":1,"shop_name":"小转转","member":"75.00","distance":1885.05}]
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
         * uid : 7
         * address : 山东省济南市天桥区北坦街道明湖广场
         * is_classification : 1
         * shop_name : 锤子
         * member : 50.00
         * distance : 1870.48
         */

        private int uid;
        private String address;
        private int is_classification;
        private String shop_name;
        private String member;
        private double distance;
        private String image;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
