package com.tuoyi.threebusinesscity.bean;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/2 16:38
 * @描述 ${TODO}
 */

public class UserInfoBean {


    /**
     * status : 0
     * code : 200
     * message : 成功
     * data : {"email":"qwertt.com","sex":0,"autograph":"喉咙弟弟的歌啪啪啪","username":"李岩555","userpic":"cache/images/ckeditor/20180613/2018061310052534-461x461.jpeg","bank_card":"","total_bonus":"999999.99","points":"999999.99","electronic_signing":"1","is_real_name":"0"}
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
         * email : qwertt.com
         * sex : 0
         * autograph : 喉咙弟弟的歌啪啪啪
         * username : 李岩555
         * userpic : cache/images/ckeditor/20180613/2018061310052534-461x461.jpeg
         * bank_card :
         * total_bonus : 999999.99
         * points : 999999.99
         * electronic_signing : 1
         * is_real_name : 0
         */

        private String email;
        private int sex;
        private String autograph;
        private String username;
        private String userpic;
        private String bank_card;
        private String total_bonus;
        private String points;
        private String electronic_signing;
        private String is_real_name;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAutograph() {
            return autograph;
        }

        public void setAutograph(String autograph) {
            this.autograph = autograph;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserpic() {
            return userpic;
        }

        public void setUserpic(String userpic) {
            this.userpic = userpic;
        }

        public String getBank_card() {
            return bank_card;
        }

        public void setBank_card(String bank_card) {
            this.bank_card = bank_card;
        }

        public String getTotal_bonus() {
            return total_bonus;
        }

        public void setTotal_bonus(String total_bonus) {
            this.total_bonus = total_bonus;
        }

        public String getPoints() {
            return points;
        }

        public void setPoints(String points) {
            this.points = points;
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
    }
}
