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
     * data : {"email":"","sex":0,"birthday":"0000-00-00 00:00:00","autograph":"","username":"","userpic":"","total_bonus":"0.00","points":"0.00"}
     */

    private int status;
    private int      code;
    private String   message;
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
         * email :
         * sex : 0
         * birthday : 0000-00-00 00:00:00
         * autograph :
         * username :
         * userpic :
         * total_bonus : 0.00
         * points : 0.00
         */

        private String email;
        private int    sex;
        private String birthday;
        private String autograph;
        private String username;
        private String userpic;
        private String total_bonus;
        private String points;

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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
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
    }
}
