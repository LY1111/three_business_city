package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class SearchGoodsListBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"goods_id":4,"name":"艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网","price":"30.00","pay_points":5000,"points":50,"seckill_price":"0.00","seckill_pay_points":0,"type":1,"image":"images/osc1/4/1.jpg"},{"goods_id":8,"name":"青瓷手绘荷花陶瓷茶叶罐 储物罐 瓷罐 收纳罐","price":"58.00","pay_points":5000,"points":50,"seckill_price":"0.00","seckill_pay_points":0,"type":1,"image":"images/osc1/8/1.jpg"},{"goods_id":12,"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","price":"168.00","pay_points":5000,"points":50,"seckill_price":"60.00","seckill_pay_points":10,"type":1,"image":"images/osc1/12/1.jpg"}]
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
         * goods_id : 4
         * name : 艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网
         * price : 30.00
         * pay_points : 5000
         * points : 50
         * seckill_price : 0.00
         * seckill_pay_points : 0
         * type : 1
         * image : images/osc1/4/1.jpg
         */

        private int goods_id;
        private String name;
        private String price;
        private int pay_points;
        private int points;
        private String seckill_price;
        private int seckill_pay_points;
        private int type;
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

        public int getPay_points() {
            return pay_points;
        }

        public void setPay_points(int pay_points) {
            this.pay_points = pay_points;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public String getSeckill_price() {
            return seckill_price;
        }

        public void setSeckill_price(String seckill_price) {
            this.seckill_price = seckill_price;
        }

        public int getSeckill_pay_points() {
            return seckill_pay_points;
        }

        public void setSeckill_pay_points(int seckill_pay_points) {
            this.seckill_pay_points = seckill_pay_points;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
