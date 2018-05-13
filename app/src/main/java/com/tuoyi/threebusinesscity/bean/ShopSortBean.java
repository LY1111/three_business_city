package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13 0013.
 */

public class ShopSortBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"id":30,"name":"美食餐饮"},{"id":33,"name":"美容美发"},{"id":34,"name":"休闲娱乐"},{"id":35,"name":"酒店住宿"},{"id":36,"name":"商场购物"},{"id":41,"name":"生活服务"},{"id":42,"name":"汽车服务"},{"id":43,"name":"家居建材"},{"id":44,"name":"教育培训"},{"id":45,"name":"综合服务"}]
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
         */

        private int id;
        private String name;

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
    }
}
