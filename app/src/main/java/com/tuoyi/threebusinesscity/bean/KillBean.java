package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class KillBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"goods_id":1,"name":"艺创 青花甜白三才大盖碗功夫茶具敬茶陶瓷泡茶器","price":"49.00","image":"images/osc1/1/1.jpg","start_seckill_time":"2018-03-07 10:00:55"},{"goods_id":3,"name":"艺创 青花甜白功夫茶具配件 公道杯茶海分茶器陶瓷","price":"39.00","image":"images/osc1/3/2.jpg","start_seckill_time":"2018-08-08 17:35:41"},{"goods_id":5,"name":"旅行茶具套装便携式功夫茶具 车载 户外 手绘茶具","price":"0.20","image":"images/osc1/5/1.jpg","start_seckill_time":"2018-10-08 17:35:41"},{"goods_id":9,"name":"艺创 定窑白瓷手绘胭红整套功夫茶具陶瓷 盖碗 茶杯套组","price":"198.00","image":"images/osc1/9/1.jpg","start_seckill_time":"2018-09-08 17:35:41"}]
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
         * start_seckill_time : 2018-03-07 10:00:55
         */

        private int goods_id;
        private String name;
        private String price;
        private String image;
        private String start_seckill_time;

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

        public String getStart_seckill_time() {
            return start_seckill_time;
        }

        public void setStart_seckill_time(String start_seckill_time) {
            this.start_seckill_time = start_seckill_time;
        }
    }
}
