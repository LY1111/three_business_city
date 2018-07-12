package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/4/23 0023.
 */

public class BusinessLoginBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"business_token":"vm1Q4mhxxCvt6X1wNX0LUEoGTSPVV9cVgHVyhwov90bwQkoGYRAncTWc9hrq5bNyhqFdimmSKlK5NQ5dzEw757aDXkI5HbpkulySoldRIPwt2LBTOODI920VIjBye61mweP7HOSOWcuo0vR5PDbZGYxgBht1EqI4ak5dDN8B"}
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
         * business_token : vm1Q4mhxxCvt6X1wNX0LUEoGTSPVV9cVgHVyhwov90bwQkoGYRAncTWc9hrq5bNyhqFdimmSKlK5NQ5dzEw757aDXkI5HbpkulySoldRIPwt2LBTOODI920VIjBye61mweP7HOSOWcuo0vR5PDbZGYxgBht1EqI4ak5dDN8B
         */

        private String business_token;

        public String getBusiness_token() {
            return business_token;
        }

        public void setBusiness_token(String business_token) {
            this.business_token = business_token;
        }
    }
}
