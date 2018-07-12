package com.tuoyi.threebusinesscity.bean;

public class BusinessMsgBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"shop_name":"李岩测试","province":"山东省","autograph":"济南市","city":"天桥区","username":"李岩","longitude":"117.00904","latitude":"36.67548","image":"","background_image":"","commission_id":1,"commission_name":"A型","classification_id":30,"classification_name":"美食餐饮","total_bonus":0,"business_type":"3","electronic_signing":"0","is_real_name":"0","enterprise_audit":"0"}
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
         * shop_name : 李岩测试
         * province : 山东省
         * autograph : 济南市
         * city : 天桥区
         * username : 李岩
         * longitude : 117.00904
         * latitude : 36.67548
         * image :
         * background_image :
         * commission_id : 1
         * commission_name : A型
         * classification_id : 30
         * classification_name : 美食餐饮
         * total_bonus : 0
         * business_type : 3
         * electronic_signing : 0
         * is_real_name : 0
         * enterprise_audit : 0
         */

        private String shop_name;
        private String province;
        private String autograph;
        private String city;
        private String username;
        private String longitude;
        private String latitude;
        private String image;
        private String background_image;
        private int commission_id;
        private String commission_name;
        private int classification_id;
        private String classification_name;
        private int total_bonus;
        private String business_type;
        private String electronic_signing;
        private String is_real_name;
        private String enterprise_audit;

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBackground_image() {
            return background_image;
        }

        public void setBackground_image(String background_image) {
            this.background_image = background_image;
        }

        public int getCommission_id() {
            return commission_id;
        }

        public void setCommission_id(int commission_id) {
            this.commission_id = commission_id;
        }

        public String getCommission_name() {
            return commission_name;
        }

        public void setCommission_name(String commission_name) {
            this.commission_name = commission_name;
        }

        public int getClassification_id() {
            return classification_id;
        }

        public void setClassification_id(int classification_id) {
            this.classification_id = classification_id;
        }

        public String getClassification_name() {
            return classification_name;
        }

        public void setClassification_name(String classification_name) {
            this.classification_name = classification_name;
        }

        public int getTotal_bonus() {
            return total_bonus;
        }

        public void setTotal_bonus(int total_bonus) {
            this.total_bonus = total_bonus;
        }

        public String getBusiness_type() {
            return business_type;
        }

        public void setBusiness_type(String business_type) {
            this.business_type = business_type;
        }

        public String getElectronic_signing() {
            return electronic_signing;
        }

        public void setElectronic_signing(String electronic_signing) {
            this.electronic_signing = electronic_signing;
        }

        public String getIs_real_name() {
            return is_real_name;
        }

        public void setIs_real_name(String is_real_name) {
            this.is_real_name = is_real_name;
        }

        public String getEnterprise_audit() {
            return enterprise_audit;
        }

        public void setEnterprise_audit(String enterprise_audit) {
            this.enterprise_audit = enterprise_audit;
        }
    }
}
