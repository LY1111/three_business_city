package com.tuoyi.threebusinesscity.bean;

public class AddBankCarkBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"tranceNum":"D2018070941810","transDate":"20180709","phone":"17661044588"}
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
         * tranceNum : D2018070941810
         * transDate : 20180709
         * phone : 17661044588
         */

        private String tranceNum;
        private String transDate;
        private String phone;

        public String getTranceNum() {
            return tranceNum;
        }

        public void setTranceNum(String tranceNum) {
            this.tranceNum = tranceNum;
        }

        public String getTransDate() {
            return transDate;
        }

        public void setTransDate(String transDate) {
            this.transDate = transDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
