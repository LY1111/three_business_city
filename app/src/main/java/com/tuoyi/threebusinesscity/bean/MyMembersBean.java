package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by md
 * on 2018/3/7 0007.
 * 我的会员实体类
 */

public class MyMembersBean {

    /**
     * status : 0
     * code : 200
     * message : 成功
     * data : {"count":1,"upper_member":[{"username":"李岩","userpic":""}]}
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
         * count : 1
         * upper_member : [{"username":"李岩","userpic":""}]
         */

        private int count;
        private List<UpperMemberBean> upper_member;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<UpperMemberBean> getUpper_member() {
            return upper_member;
        }

        public void setUpper_member(List<UpperMemberBean> upper_member) {
            this.upper_member = upper_member;
        }

        public static class UpperMemberBean {
            /**
             * username : 李岩
             * userpic :
             */

            private String username;
            private String userpic;

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getUserpic() {
                return userpic;
            }

            public void setUserpic(String userpic) {
                this.userpic = userpic;
            }
        }
    }
}
