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
     * data : [{"goods_id":1,"name":"艺创 青花甜白三才大盖碗功夫茶具敬茶陶瓷泡茶器","price":"49.00","image":"images/osc1/1/1.jpg"},{"goods_id":2,"name":"艺创 青花甜白功夫茶具小品茗茶杯陶瓷茶盏瓷杯主人杯6个","price":"79.00","image":"images/osc1/2/2.jpg"},{"goods_id":3,"name":"艺创 青花甜白功夫茶具配件 公道杯茶海分茶器陶瓷","price":"39.00","image":"images/osc1/3/2.jpg"},{"goods_id":4,"name":"艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网","price":"30.00","image":"images/osc1/4/1.jpg"},{"goods_id":6,"name":"艺创 品茗杯陶瓷功夫茶具 定窑白荷花大号茶个人主人杯","price":"39.00","image":"images/osc1/6/1.jpg"},{"goods_id":7,"name":"艺创 个人杯 品茗杯 功夫茶具茶杯 手拉坯柴烧","price":"30.00","image":"images/osc1/7/1.jpg"},{"goods_id":9,"name":"艺创 定窑白瓷手绘胭红整套功夫茶具陶瓷 盖碗 茶杯套组","price":"198.00","image":"images/osc1/9/1.jpg"},{"goods_id":10,"name":"艺创 定窑白瓷手绘山水整套功夫茶具 盖碗 茶杯套组","price":"158.00","image":"images/osc1/10/1.jpg"},{"goods_id":11,"name":"艺创 手工制作粗陶柴烧整套功夫木柄茶具 陶瓷 侧把 壶承礼盒","price":"450.00","image":"images/osc1/11/1.jpg"},{"goods_id":12,"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","price":"168.00","image":"images/osc1/12/1.jpg"}]
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
         * goods_id : 1
         * name : 艺创 青花甜白三才大盖碗功夫茶具敬茶陶瓷泡茶器
         * price : 49.00
         * image : images/osc1/1/1.jpg
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
