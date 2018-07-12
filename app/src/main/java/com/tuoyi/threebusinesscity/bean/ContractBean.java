package com.tuoyi.threebusinesscity.bean;

public class ContractBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"url":"http://122.227.225.142:23661/yungateway/member/signContract.html?req=%7B%22service%22%3A%22MemberService%22%2C%22method%22%3A%22signContract%22%2C%22param%22%3A%7B%22bizUserId%22%3A%22HHHHH39%22%2C%22memberType%22%3A3%2C%22jumpUrl%22%3A%22http%3A%5C%2F%5C%2Fsszl.tuoee.com%5C%2Findex.php%5C%2Fapi%5C%2FMember%5C%2Fsign_a_jump_member%22%2C%22backUrl%22%3A%22http%3A%5C%2F%5C%2Fsszl.tuoee.com%5C%2Findex.php%5C%2Fapi%5C%2FMember%5C%2Fsign_a_callback_member%22%2C%22source%22%3A1%7D%7D&amp;ssoid=ime_public_ssoid&amp;sysid=100009001000&amp;timestamp=2018-07-09+11%3A18%3A08&amp;sign=b9NaUQjc9PoYILbqUTAByjV7%2BMEhPD2IqGZY%2FvoP5a3x8bfwY3QiIhpZznaVHURd8mbqznryN8%2F%2FeX2ckaqli9yQKYdtu1Jbiq2J4U9c%2FyrCNQqF8bSI8baLjWPHJSER8A2iN0dcJ9HWxQRS%2FGIf99vdj69Aj7Ce6A%2Fn35KEF6Q%3D&amp;v=1.0&amp;"}
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
         * url : http://122.227.225.142:23661/yungateway/member/signContract.html?req=%7B%22service%22%3A%22MemberService%22%2C%22method%22%3A%22signContract%22%2C%22param%22%3A%7B%22bizUserId%22%3A%22HHHHH39%22%2C%22memberType%22%3A3%2C%22jumpUrl%22%3A%22http%3A%5C%2F%5C%2Fsszl.tuoee.com%5C%2Findex.php%5C%2Fapi%5C%2FMember%5C%2Fsign_a_jump_member%22%2C%22backUrl%22%3A%22http%3A%5C%2F%5C%2Fsszl.tuoee.com%5C%2Findex.php%5C%2Fapi%5C%2FMember%5C%2Fsign_a_callback_member%22%2C%22source%22%3A1%7D%7D&amp;ssoid=ime_public_ssoid&amp;sysid=100009001000&amp;timestamp=2018-07-09+11%3A18%3A08&amp;sign=b9NaUQjc9PoYILbqUTAByjV7%2BMEhPD2IqGZY%2FvoP5a3x8bfwY3QiIhpZznaVHURd8mbqznryN8%2F%2FeX2ckaqli9yQKYdtu1Jbiq2J4U9c%2FyrCNQqF8bSI8baLjWPHJSER8A2iN0dcJ9HWxQRS%2FGIf99vdj69Aj7Ce6A%2Fn35KEF6Q%3D&amp;v=1.0&amp;
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
