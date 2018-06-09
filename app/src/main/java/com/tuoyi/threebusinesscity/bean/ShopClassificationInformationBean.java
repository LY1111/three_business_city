package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/1 15:22
 * @描述 ${TODO}
 */

public class ShopClassificationInformationBean {


    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : [{"id":32,"name":"粮油食品","image":"images/osc1/bizfenlei/63424234.png","lower":[{"id":42,"name":"粮油调味","image":"images/osc1/bizfenlei/a5.jpg"},{"id":51,"name":"休闲食品","image":"images/osc1/bizfenlei/jiajujiancai.png"},{"id":52,"name":"生鲜水果","image":"images/osc1/bizfenlei/a5.jpg"}]},{"id":41,"name":"汽车用品","image":"images/osc1/bizfenlei/5.png","lower":[{"id":50,"name":"汽车装饰","image":"images/osc1/bizfenlei/a6.jpg"},{"id":80,"name":"车载电器","image":"images/osc1/bizfenlei/a9.jpg"},{"id":81,"name":"美容清洗","image":"images/osc1/bizfenlei/a9.jpg"},{"id":82,"name":"汽车服务","image":"images/osc1/bizfenlei/a5.jpg"}]},{"id":40,"name":"母婴用品","image":"images/osc1/bizfenlei/65434543324.png","lower":[{"id":49,"name":"喂养洗护","image":"images/osc1/bizfenlei/a6.jpg"},{"id":76,"name":"孕妈专区","image":"images/osc1/bizfenlei/a9.jpg"},{"id":77,"name":"婴幼专区","image":"images/osc1/bizfenlei/a9.jpg"},{"id":78,"name":"奶粉专区","image":"images/osc1/bizfenlei/a9.jpg"},{"id":79,"name":"儿童玩具","image":"images/osc1/bizfenlei/a9.jpg"}]},{"id":39,"name":"箱包皮具","image":"images/osc1/bizfenlei/3445566555.png","lower":[{"id":48,"name":"精品男包","image":"images/osc1/bizfenlei/a6.jpg"},{"id":73,"name":"潮流女包","image":"images/osc1/bizfenlei/a5.jpg"},{"id":74,"name":"儿童书包","image":"images/osc1/bizfenlei/a9.jpg"},{"id":75,"name":"旅游箱包","image":"images/osc1/bizfenlei/a9.jpg"}]},{"id":38,"name":"数码手机","image":"images/osc1/bizfenlei/643221213243.png","lower":[{"id":47,"name":"手机通讯","image":"images/osc1/bizfenlei/a9.jpg"},{"id":70,"name":"摄影摄像","image":"images/osc1/bizfenlei/a5.jpg"},{"id":71,"name":"数码配件","image":"images/osc1/bizfenlei/a9.jpg"},{"id":72,"name":"智能设备","image":"images/osc1/bizfenlei/a5.jpg"}]},{"id":37,"name":"美妆个护","image":"images/osc1/bizfenlei/65544345654.png","lower":[{"id":46,"name":"面部护肤","image":"images/osc1/bizfenlei/a5.jpg"},{"id":67,"name":"精致彩妆","image":"images/osc1/bizfenlei/a9.jpg"},{"id":68,"name":"女性护理","image":"images/osc1/bizfenlei/a5.jpg"},{"id":69,"name":"口腔护理","image":"images/osc1/bizfenlei/a5.jpg"}]},{"id":36,"name":"服装鞋帽","image":"images/osc1/bizfenlei/23456789098765.png","lower":[{"id":45,"name":"时尚男装","image":"images/osc1/bizfenlei/jiudianzhusu.png"},{"id":60,"name":"时尚女装","image":"images/osc1/bizfenlei/a4.jpg"},{"id":61,"name":"精品鞋帽","image":"images/osc1/bizfenlei/a5.jpg"},{"id":62,"name":"时尚配饰","image":"images/osc1/bizfenlei/a9.jpg"},{"id":63,"name":"精品内衣","image":"images/osc1/bizfenlei/a8.jpg"}]},{"id":35,"name":"家具家电","image":"images/osc1/bizfenlei/454555666.png","lower":[{"id":44,"name":"生活日用","image":"images/osc1/bizfenlei/a5.jpg"},{"id":64,"name":"厨房家电","image":"images/osc1/bizfenlei/a9.jpg"},{"id":65,"name":"家装家纺","image":"images/osc1/bizfenlei/2.png"},{"id":66,"name":"精品家具","image":"images/osc1/2/2.jpg"}]},{"id":34,"name":"酒水饮料","image":"images/osc1/bizfenlei/1.png","lower":[{"id":43,"name":"品质白酒","image":"images/osc1/bizfenlei/a5.jpg"},{"id":53,"name":"品质红酒","image":"images/osc1/bizfenlei/a6.jpg"},{"id":54,"name":"牛奶饮料","image":"images/osc1/bizfenlei/a7.jpg"},{"id":59,"name":"冲调饮品","image":"images/osc1/bizfenlei/a5.jpg"}]},{"id":55,"name":"运动户外","image":"images/osc1/bizfenlei/_20180601094142.png","lower":[{"id":56,"name":"户外鞋服","image":"images/osc1/bizfenlei/a5.jpg"},{"id":57,"name":"健身训练","image":"images/osc1/bizfenlei/a6.jpg"},{"id":83,"name":"户外装备","image":"images/osc1/bizfenlei/a9.jpg"},{"id":84,"name":"垂钓用品","image":""}]}]
     */

    private int status;
    private int            code;
    private String         message;
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
         * id : 32
         * name : 粮油食品
         * image : images/osc1/bizfenlei/63424234.png
         * lower : [{"id":42,"name":"粮油调味","image":"images/osc1/bizfenlei/a5.jpg"},{"id":51,"name":"休闲食品","image":"images/osc1/bizfenlei/jiajujiancai.png"},{"id":52,"name":"生鲜水果","image":"images/osc1/bizfenlei/a5.jpg"}]
         */

        private int id;
        private String          name;
        private String          image;
        private List<LowerBean> lower;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<LowerBean> getLower() {
            return lower;
        }

        public void setLower(List<LowerBean> lower) {
            this.lower = lower;
        }

        public static class LowerBean {
            /**
             * id : 42
             * name : 粮油调味
             * image : images/osc1/bizfenlei/a5.jpg
             */

            private int id;
            private String name;
            private String image;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }
    }
}
