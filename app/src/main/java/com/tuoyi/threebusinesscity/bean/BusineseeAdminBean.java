package com.tuoyi.threebusinesscity.bean;

import java.io.Serializable;
import java.util.List;

public class BusineseeAdminBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"goods_id":46,"name":"asdasd","price":"0.00","image":"cache/images/ckeditor/20180614/2018061498994999-630x840.jpg","summary":"哦婆婆婆婆说","status":0,"sort_order":76767},{"goods_id":50,"name":"测量","price":"123.00","image":"cache/images/ckeditor/20180613/2018061349975454-630x420.jpg","summary":"路路通","status":127,"sort_order":369},{"goods_id":51,"name":"了解一下？","price":"6.66","image":"cache/images/ckeditor/20180614/2018061451521025-630x849.jpg","summary":"美女一位","status":127,"sort_order":3},{"goods_id":52,"name":"郭采洁，了解一下！！！","price":"9999999.00","image":"cache/images/ckeditor/20180614/2018061457975253-300x300.jpg","summary":"无法言语表达zzzzz","status":127,"sort_order":1},{"goods_id":53,"name":"花盆","price":"1.00","image":"cache/images/ckeditor/20180614/2018061497575350-630x840.jpg","summary":"！！！！","status":127,"sort_order":1},{"goods_id":54,"name":"桥洞子","price":"222.00","image":"cache/images/ckeditor/20180614/2018061499509751-300x300.jpg","summary":"，，，，，，","status":0,"sort_order":2},{"goods_id":55,"name":"？？？","price":"0.00","image":"cache/images/ckeditor/20180614/2018061453101555-300x300.jpg","summary":"！！！","status":0,"sort_order":333},{"goods_id":56,"name":"伦敦呢莫","price":"99.00","image":"cache/images/ckeditor/20180614/2018061410110110-300x300.jpg","summary":"？？？？","status":127,"sort_order":3},{"goods_id":57,"name":"模具known","price":"966.00","image":"cache/images/ckeditor/20180614/2018061410198575-630x840.jpg","summary":"OL苦闷9页","status":127,"sort_order":556}]
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

    public static class DataBean implements Serializable{
        /**
         * goods_id : 46
         * name : asdasd
         * price : 0.00
         * image : cache/images/ckeditor/20180614/2018061498994999-630x840.jpg
         * summary : 哦婆婆婆婆说
         * status : 0
         * sort_order : 76767
         */

        private int goods_id;
        private String name;
        private String price;
        private String image;
        private String summary;
        private int status;
        private int sort_order;

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

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSort_order() {
            return sort_order;
        }

        public void setSort_order(int sort_order) {
            this.sort_order = sort_order;
        }
    }
}
