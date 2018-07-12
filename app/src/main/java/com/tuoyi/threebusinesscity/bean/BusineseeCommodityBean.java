package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class BusineseeCommodityBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"goods_id":39,"name":"斧头","price":"10.00","image":"images/business6/9-160G50U922.jpg"},{"goods_id":40,"name":"头盔","price":"30.00","image":"images/business6/9-160G50U922.jpg"},{"goods_id":42,"name":"裤衩（微红）","price":"50.00","image":"images/business6/78088fd6f9874b008cee0375dd5b42a9.jpeg"}]
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
         * goods_id : 39
         * name : 斧头
         * price : 10.00
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
