package com.tuoyi.threebusinesscity.bean;

public class BusinessSetBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"uid":6,"reg_type":"pc","username":"斧王","password":"888888","checked":1,"address_id":0,"nickname":"","total_sales":"0.00","image":"images/business6/9-160G50U922.jpg","is_agent":0,"pid":10,"agent_level":0,"total_bonus":"0.000","points":0,"cash_points":0,"wish":0,"regdate":1520413007,"lastdate":1525987439,"regip":"","lastip":"60.216.137.144","loginnum":30,"email":"372951681@qq.com","telephone":"17605382423","groupid":6,"areaid":0,"message":0,"islock":0,"commission_id":2,"is_classification":1,"address":"山东省济南市天桥区北坦街道明湖广场","longitude":"117.00698491267042","latitude":"36.674976220606034","shop_name":"斧王小商店","province":"山东省","city":"济南市","district":"天桥区","abc":"121212121221"}
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
         * uid : 6
         * reg_type : pc
         * username : 斧王
         * password : 888888
         * checked : 1
         * address_id : 0
         * nickname :
         * total_sales : 0.00
         * image : images/business6/9-160G50U922.jpg
         * is_agent : 0
         * pid : 10
         * agent_level : 0
         * total_bonus : 0.000
         * points : 0
         * cash_points : 0
         * wish : 0
         * regdate : 1520413007
         * lastdate : 1525987439
         * regip :
         * lastip : 60.216.137.144
         * loginnum : 30
         * email : 372951681@qq.com
         * telephone : 17605382423
         * groupid : 6
         * areaid : 0
         * message : 0
         * islock : 0
         * commission_id : 2
         * is_classification : 1
         * address : 山东省济南市天桥区北坦街道明湖广场
         * longitude : 117.00698491267042
         * latitude : 36.674976220606034
         * shop_name : 斧王小商店
         * province : 山东省
         * city : 济南市
         * district : 天桥区
         * abc : 121212121221
         */

        private int uid;
        private String reg_type;
        private String username;
        private String password;
        private int checked;
        private int address_id;
        private String nickname;
        private String total_sales;
        private String image;
        private int is_agent;
        private int pid;
        private int agent_level;
        private String total_bonus;
        private int points;
        private int cash_points;
        private int wish;
        private int regdate;
        private int lastdate;
        private String regip;
        private String lastip;
        private int loginnum;
        private String email;
        private String telephone;
        private int groupid;
        private int areaid;
        private int message;
        private int islock;
        private int commission_id;
        private int is_classification;
        private String address;
        private String longitude;
        private String latitude;
        private String shop_name;
        private String province;
        private String city;
        private String district;
        private String abc;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getReg_type() {
            return reg_type;
        }

        public void setReg_type(String reg_type) {
            this.reg_type = reg_type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getChecked() {
            return checked;
        }

        public void setChecked(int checked) {
            this.checked = checked;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getTotal_sales() {
            return total_sales;
        }

        public void setTotal_sales(String total_sales) {
            this.total_sales = total_sales;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIs_agent() {
            return is_agent;
        }

        public void setIs_agent(int is_agent) {
            this.is_agent = is_agent;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public int getAgent_level() {
            return agent_level;
        }

        public void setAgent_level(int agent_level) {
            this.agent_level = agent_level;
        }

        public String getTotal_bonus() {
            return total_bonus;
        }

        public void setTotal_bonus(String total_bonus) {
            this.total_bonus = total_bonus;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public int getCash_points() {
            return cash_points;
        }

        public void setCash_points(int cash_points) {
            this.cash_points = cash_points;
        }

        public int getWish() {
            return wish;
        }

        public void setWish(int wish) {
            this.wish = wish;
        }

        public int getRegdate() {
            return regdate;
        }

        public void setRegdate(int regdate) {
            this.regdate = regdate;
        }

        public int getLastdate() {
            return lastdate;
        }

        public void setLastdate(int lastdate) {
            this.lastdate = lastdate;
        }

        public String getRegip() {
            return regip;
        }

        public void setRegip(String regip) {
            this.regip = regip;
        }

        public String getLastip() {
            return lastip;
        }

        public void setLastip(String lastip) {
            this.lastip = lastip;
        }

        public int getLoginnum() {
            return loginnum;
        }

        public void setLoginnum(int loginnum) {
            this.loginnum = loginnum;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public int getGroupid() {
            return groupid;
        }

        public void setGroupid(int groupid) {
            this.groupid = groupid;
        }

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public int getMessage() {
            return message;
        }

        public void setMessage(int message) {
            this.message = message;
        }

        public int getIslock() {
            return islock;
        }

        public void setIslock(int islock) {
            this.islock = islock;
        }

        public int getCommission_id() {
            return commission_id;
        }

        public void setCommission_id(int commission_id) {
            this.commission_id = commission_id;
        }

        public int getIs_classification() {
            return is_classification;
        }

        public void setIs_classification(int is_classification) {
            this.is_classification = is_classification;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAbc() {
            return abc;
        }

        public void setAbc(String abc) {
            this.abc = abc;
        }
    }
}
