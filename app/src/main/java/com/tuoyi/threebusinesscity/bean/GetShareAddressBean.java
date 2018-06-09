package com.tuoyi.threebusinesscity.bean;

public class GetShareAddressBean {


    /**
     * status : 0
     * code : 200
     * message : 成功
     * data : {"sharing_links_member":"http://sszl.tuoee.com/api/member/register/referee/17661044588","sharing_links_business":"http://sszl.tuoee.com/api/member/business_register/referee/17661044588"}
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
         * sharing_links_member : http://sszl.tuoee.com/api/member/register/referee/17661044588
         * sharing_links_business : http://sszl.tuoee.com/api/member/business_register/referee/17661044588
         */

        private String sharing_links_member;
        private String sharing_links_business;

        public String getSharing_links_member() {
            return sharing_links_member;
        }

        public void setSharing_links_member(String sharing_links_member) {
            this.sharing_links_member = sharing_links_member;
        }

        public String getSharing_links_business() {
            return sharing_links_business;
        }

        public void setSharing_links_business(String sharing_links_business) {
            this.sharing_links_business = sharing_links_business;
        }
    }
}
