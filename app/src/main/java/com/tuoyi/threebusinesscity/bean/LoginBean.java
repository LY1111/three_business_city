package com.tuoyi.threebusinesscity.bean;

/**
 * Created by md
 * on 2018/4/23 0023.
 */

public class LoginBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"token":"oAkyFBlfEFMfOkTGOx73wnuWGFfL5LKI0E8YDfyWGn2j4LZVf4PecWIbfHiiynLKf35vxybNjK2GxM4S6dK9ZTtaTq79zOKX2Ch6tDW1qJn9jkRwCeXCNwTBFfJDxDqk3gMOC5Bp2ZVmFM87Q68ZbuCHCzkSDbu2R4Nc62wG"}
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
         * token : oAkyFBlfEFMfOkTGOx73wnuWGFfL5LKI0E8YDfyWGn2j4LZVf4PecWIbfHiiynLKf35vxybNjK2GxM4S6dK9ZTtaTq79zOKX2Ch6tDW1qJn9jkRwCeXCNwTBFfJDxDqk3gMOC5Bp2ZVmFM87Q68ZbuCHCzkSDbu2R4Nc62wG
         */

        private String token;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
