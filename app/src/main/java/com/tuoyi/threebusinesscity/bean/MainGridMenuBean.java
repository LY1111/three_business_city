package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * on 2018/3/3 0003.
 * 网格菜单实体类
 */

public class MainGridMenuBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"id":30,"name":"美食餐饮","image":"images/business6/9-160G50U922.jpg"},{"id":33,"name":"美容美发","image":"images/osc1/9-160G50U922.jpg"},{"id":34,"name":"休闲娱乐","image":"images/osc1/9-160G50U922.jpg"},{"id":35,"name":"酒店住宿","image":"images/osc1/9-160G50U922.jpg"},{"id":36,"name":"商场购物","image":"images/osc1/9-160G50U922.jpg"},{"id":41,"name":"生活服务","image":"images/osc1/9-160G50U922.jpg"},{"id":42,"name":"汽车服务","image":"images/osc1/9-160G50U922.jpg"},{"id":43,"name":"家居建材","image":"images/osc1/9-160G50U922.jpg"},{"id":44,"name":"教育培训","image":"images/osc1/9-160G50U922.jpg"},{"id":45,"name":"综合服务","image":"images/osc1/9-160G50U922.jpg"}]
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
         * id : 30
         * name : 美食餐饮
         * image : images/business6/9-160G50U922.jpg
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
