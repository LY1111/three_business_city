package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by wushishuo on 2018/5/26.
 */

public class OnLineShopMenuBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"id":18,"name":"建材","image":"","lower":[]},{"id":16,"name":"二手","image":"","lower":[]},{"id":15,"name":"汽车","image":"","lower":[]},{"id":14,"name":"宠物","image":"","lower":[]},{"id":13,"name":"鲜花","image":"","lower":[]},{"id":12,"name":"零食","image":"","lower":[]},{"id":11,"name":"生鲜","image":"","lower":[]},{"id":10,"name":"美食","image":"","lower":[]},{"id":9,"name":"保健品","image":"","lower":[]},{"id":8,"name":"洗护","image":"","lower":[]},{"id":7,"name":"美妆","image":"","lower":[]},{"id":6,"name":"手机","image":"","lower":[]},{"id":5,"name":"数码","image":"","lower":[]},{"id":4,"name":"家电","image":"","lower":[]},{"id":2,"name":"鞋子","image":"","lower":[{"id":22,"name":"女鞋","image":""},{"id":23,"name":"男鞋","image":""}]},{"id":1,"name":"衣服","image":"images/osc1/2/1.jpg","lower":[{"id":19,"name":"女装","image":""},{"id":20,"name":"男装","image":""},{"id":21,"name":"内衣","image":""},{"id":29,"name":"大大","image":"images/osc1/1/1.jpg"},{"id":31,"name":"ADADA","image":""}]},{"id":28,"name":"111","image":"","lower":[]},{"id":30,"name":"434343","image":"","lower":[]}]
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
         * id : 18
         * name : 建材
         * image :
         * lower : []
         */

        private int id;
        private String name;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
