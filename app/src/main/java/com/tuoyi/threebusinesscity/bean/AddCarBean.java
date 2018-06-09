package com.tuoyi.threebusinesscity.bean;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/4 16:33
 * @描述 ${TODO}
 */

public class AddCarBean {

    /**
     * status : 1
     * code : 200
     * message : 加入成功
     * data : {"total":13}
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
         * total : 13
         */

        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
