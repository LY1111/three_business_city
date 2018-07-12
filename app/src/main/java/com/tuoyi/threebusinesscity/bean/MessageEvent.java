package com.tuoyi.threebusinesscity.bean;

public class MessageEvent {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public MessageEvent(String message){
        this.message=message;
    }
}
