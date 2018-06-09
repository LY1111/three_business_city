package com.tuoyi.threebusinesscity.bean;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/4 17:56
 * @描述 ${TODO}
 */

public class BaseBean {

    /**
     * status : 1
     * code : 200
     * message : 修改成功
     */

    private int status;
    private int    code;
    private String message;

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

    public  String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
