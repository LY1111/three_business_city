package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/4/28 0028.
 */

public class ShareBean {

    /**
     * status : 0
     * code : 200
     * message : 成功
     * data : {"sharing_links":"http://sszl.tuoee.com/api/member/register/referee/13896683678"}
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
         * sharing_links : http://sszl.tuoee.com/api/member/register/referee/13896683678
         */

        private String sharing_links;

        public String getSharing_links() {
            return sharing_links;
        }

        public void setSharing_links(String sharing_links) {
            this.sharing_links = sharing_links;
        }
    }
}
