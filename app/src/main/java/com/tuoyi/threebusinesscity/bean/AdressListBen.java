package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class AdressListBen {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"address_id":19,"uid":42,"name":"李岩","telephone":"17661044588","address":"明湖广场1009","city_id":190,"country_id":2254,"province_id":12,"province":"安徽省","city":"马鞍山市","country":"雨山区"},{"address_id":20,"uid":42,"name":"李岩测试","telephone":"17661044588","address":"明湖广场1009","city_id":36,"country_id":37,"province_id":1,"province":"北京","city":"北京市","country":"东城区"}]
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
         * address_id : 19
         * uid : 42
         * name : 李岩
         * telephone : 17661044588
         * address : 明湖广场1009
         * city_id : 190
         * country_id : 2254
         * province_id : 12
         * province : 安徽省
         * city : 马鞍山市
         * country : 雨山区
         */

        private int address_id;
        private int uid;
        private String name;
        private String telephone;
        private String address;
        private int city_id;
        private int country_id;
        private int province_id;
        private String province;
        private String city;
        private String country;

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public int getCountry_id() {
            return country_id;
        }

        public void setCountry_id(int country_id) {
            this.country_id = country_id;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
