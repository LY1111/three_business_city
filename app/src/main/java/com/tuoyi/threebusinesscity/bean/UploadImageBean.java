package com.tuoyi.threebusinesscity.bean;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/2 17:58
 * @描述 ${TODO}
 */

public class UploadImageBean {

    /**
     * status : 1
     * code : 200
     * message : 上传成功
     * data : {"image_url":"public/uploads/cache/images/ckeditor/20180531/2018053152100545-630x841.jpg"}
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
         * image_url : public/uploads/cache/images/ckeditor/20180531/2018053152100545-630x841.jpg
         */

        private String image_url;

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }
    }
}
