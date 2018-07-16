package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * on 2018/3/7 0007.
 * 我的推广商家实体类
 */

public class MyPromoterBean {

    /**
     * status : 0
     * code : 200
     * message : 成功
     * data : {"count":3,"business_list":[{"shop_name":"荆新民的店铺","image":"cache/images/ckeditor/20180714/2018071451999752-630x630.jpeg"},{"shop_name":"随便填写","image":""},{"shop_name":"何敬之","image":""}]}
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
         * count : 3
         * business_list : [{"shop_name":"荆新民的店铺","image":"cache/images/ckeditor/20180714/2018071451999752-630x630.jpeg"},{"shop_name":"随便填写","image":""},{"shop_name":"何敬之","image":""}]
         */

        private int count;
        private List<BusinessListBean> business_list;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<BusinessListBean> getBusiness_list() {
            return business_list;
        }

        public void setBusiness_list(List<BusinessListBean> business_list) {
            this.business_list = business_list;
        }

        public static class BusinessListBean {
            /**
             * shop_name : 荆新民的店铺
             * image : cache/images/ckeditor/20180714/2018071451999752-630x630.jpeg
             */

            private String shop_name;
            private String image;

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
        }
    }
}
