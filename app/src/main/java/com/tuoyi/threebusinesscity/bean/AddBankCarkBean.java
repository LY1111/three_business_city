package com.tuoyi.threebusinesscity.bean;

public class AddBankCarkBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"tranceNum":"D2018072642755","transDate":"20180726","phone":"17661044588","unionBank":"123456789369","bankName":"农业银行槐荫支行","cardNo":"6228480286927636469"}
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
         * tranceNum : D2018072642755
         * transDate : 20180726
         * phone : 17661044588
         * unionBank : 123456789369
         * bankName : 农业银行槐荫支行
         * cardNo : 6228480286927636469
         */

        private String tranceNum;
        private String transDate;
        private String phone;
        private String unionBank;
        private String bankName;
        private String cardNo;

        public String getTranceNum() {
            return tranceNum;
        }

        public void setTranceNum(String tranceNum) {
            this.tranceNum = tranceNum;
        }

        public String getTransDate() {
            return transDate;
        }

        public void setTransDate(String transDate) {
            this.transDate = transDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUnionBank() {
            return unionBank;
        }

        public void setUnionBank(String unionBank) {
            this.unionBank = unionBank;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }
}
