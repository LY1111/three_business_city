package com.tuoyi.threebusinesscity.bean;

import java.util.List;

public class BankCarkListBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"isQUICKPAYCard":false,"isVerifyPayChecked":false,"phone":"17661044588","bankName":"农业银行","bindState":1,"isSafeCard":false,"cardType":1,"bankCardNo":"6228480286923636469","bindTime":"2018-07-10 10:17:38","bindMethod":2}]
     */

    private int status;
    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * isQUICKPAYCard : false
         * isVerifyPayChecked : false
         * phone : 17661044588
         * bankName : 农业银行
         * bindState : 1
         * isSafeCard : false
         * cardType : 1
         * bankCardNo : 6228480286923636469
         * bindTime : 2018-07-10 10:17:38
         * bindMethod : 2
         */

        private boolean isQUICKPAYCard;
        private boolean isVerifyPayChecked;
        private String phone;
        private String bankName;
        private int bindState;
        private boolean isSafeCard;
        private int cardType;
        private String bankCardNo;
        private String bindTime;
        private int bindMethod;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private boolean isChecked;

        public boolean isIsQUICKPAYCard() {
            return isQUICKPAYCard;
        }

        public void setIsQUICKPAYCard(boolean isQUICKPAYCard) {
            this.isQUICKPAYCard = isQUICKPAYCard;
        }

        public boolean isIsVerifyPayChecked() {
            return isVerifyPayChecked;
        }

        public void setIsVerifyPayChecked(boolean isVerifyPayChecked) {
            this.isVerifyPayChecked = isVerifyPayChecked;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public int getBindState() {
            return bindState;
        }

        public void setBindState(int bindState) {
            this.bindState = bindState;
        }

        public boolean isIsSafeCard() {
            return isSafeCard;
        }

        public void setIsSafeCard(boolean isSafeCard) {
            this.isSafeCard = isSafeCard;
        }

        public int getCardType() {
            return cardType;
        }

        public void setCardType(int cardType) {
            this.cardType = cardType;
        }

        public String getBankCardNo() {
            return bankCardNo;
        }

        public void setBankCardNo(String bankCardNo) {
            this.bankCardNo = bankCardNo;
        }

        public String getBindTime() {
            return bindTime;
        }

        public void setBindTime(String bindTime) {
            this.bindTime = bindTime;
        }

        public int getBindMethod() {
            return bindMethod;
        }

        public void setBindMethod(int bindMethod) {
            this.bindMethod = bindMethod;
        }
    }
}
