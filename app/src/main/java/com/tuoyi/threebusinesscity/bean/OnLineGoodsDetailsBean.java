package com.tuoyi.threebusinesscity.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class OnLineGoodsDetailsBean {

    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"goods":{"goods_id":12,"model":"D20151107","sku":"套","location":"德化","quantity":143860,"sale_count":0,"image":"images/osc1/12/1.jpg","name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","price":"168.00","points":50,"pay_points":5000,"type":1,"start_seckill_time":"2018-03-08 17:35:41","seckill_pay_points":60,"seckill_price":"10.00","summary":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","description":""},"image":[{"image":"images/osc1/12/1.jpg"},{"image":"images/osc1/12/2.jpg"},{"image":"images/osc1/12/3.jpg"},{"image":"images/osc1/12/4.jpg"},{"image":"images/osc1/12/5.jpg"}],"options":[{"option_id":1,"name":"款式1","type":"radio","required":1,"goods_option_value":[{"option_value_id":5,"name":"佛手荷花","quantity":12313,"subtract":1,"price":"1313.00","price_prefix":"+","image":"","weight":"1313.00000000","weight_prefix":"+"},{"option_value_id":5,"name":"佛手荷花","quantity":131313,"subtract":1,"price":"131.00","price_prefix":"+","image":"","weight":"111.00000000","weight_prefix":"+"}]},{"option_id":3,"name":"颜色","type":"select","required":1,"goods_option_value":[{"option_value_id":7,"name":"红色","quantity":111,"subtract":1,"price":"12.00","price_prefix":"+","image":"","weight":"0.00000000","weight_prefix":"+"},{"option_value_id":7,"name":"红色","quantity":123,"subtract":1,"price":"11.00","price_prefix":"+","image":"","weight":"0.00000000","weight_prefix":"+"}]}],"mobile_description":[{"mdi_id":98,"goods_id":12,"image":"images/osc1/12/m01.jpg","description":"","sort_order":0},{"mdi_id":99,"goods_id":12,"image":"images/osc1/12/m02.jpg","description":"","sort_order":1},{"mdi_id":100,"goods_id":12,"image":"images/osc1/12/m03.jpg","description":"","sort_order":2},{"mdi_id":101,"goods_id":12,"image":"images/osc1/12/m04.jpg","description":"","sort_order":3},{"mdi_id":102,"goods_id":12,"image":"images/osc1/12/m05.jpg","description":"","sort_order":4},{"mdi_id":103,"goods_id":12,"image":"images/osc1/12/m06.jpg","description":"","sort_order":5},{"mdi_id":104,"goods_id":12,"image":"images/osc1/12/m07.jpg","description":"","sort_order":6},{"mdi_id":105,"goods_id":12,"image":"images/osc1/12/m08.jpg","description":"","sort_order":7},{"mdi_id":106,"goods_id":12,"image":"images/osc1/12/m09.jpg","description":"","sort_order":8},{"mdi_id":107,"goods_id":12,"image":"images/osc1/12/m10.jpg","description":"","sort_order":9},{"mdi_id":108,"goods_id":12,"image":"images/osc1/12/m11.jpg","description":"","sort_order":10},{"mdi_id":109,"goods_id":12,"image":"images/osc1/12/m12.jpg","description":"","sort_order":11},{"mdi_id":110,"goods_id":12,"image":"images/osc1/12/m13.jpg","description":"","sort_order":12}]}
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
         * goods : {"goods_id":12,"model":"D20151107","sku":"套","location":"德化","quantity":143860,"sale_count":0,"image":"images/osc1/12/1.jpg","name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","price":"168.00","points":50,"pay_points":5000,"type":1,"start_seckill_time":"2018-03-08 17:35:41","seckill_pay_points":60,"seckill_price":"10.00","summary":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","description":""}
         * image : [{"image":"images/osc1/12/1.jpg"},{"image":"images/osc1/12/2.jpg"},{"image":"images/osc1/12/3.jpg"},{"image":"images/osc1/12/4.jpg"},{"image":"images/osc1/12/5.jpg"}]
         * options : [{"option_id":1,"name":"款式1","type":"radio","required":1,"goods_option_value":[{"option_value_id":5,"name":"佛手荷花","quantity":12313,"subtract":1,"price":"1313.00","price_prefix":"+","image":"","weight":"1313.00000000","weight_prefix":"+"},{"option_value_id":5,"name":"佛手荷花","quantity":131313,"subtract":1,"price":"131.00","price_prefix":"+","image":"","weight":"111.00000000","weight_prefix":"+"}]},{"option_id":3,"name":"颜色","type":"select","required":1,"goods_option_value":[{"option_value_id":7,"name":"红色","quantity":111,"subtract":1,"price":"12.00","price_prefix":"+","image":"","weight":"0.00000000","weight_prefix":"+"},{"option_value_id":7,"name":"红色","quantity":123,"subtract":1,"price":"11.00","price_prefix":"+","image":"","weight":"0.00000000","weight_prefix":"+"}]}]
         * mobile_description : [{"mdi_id":98,"goods_id":12,"image":"images/osc1/12/m01.jpg","description":"","sort_order":0},{"mdi_id":99,"goods_id":12,"image":"images/osc1/12/m02.jpg","description":"","sort_order":1},{"mdi_id":100,"goods_id":12,"image":"images/osc1/12/m03.jpg","description":"","sort_order":2},{"mdi_id":101,"goods_id":12,"image":"images/osc1/12/m04.jpg","description":"","sort_order":3},{"mdi_id":102,"goods_id":12,"image":"images/osc1/12/m05.jpg","description":"","sort_order":4},{"mdi_id":103,"goods_id":12,"image":"images/osc1/12/m06.jpg","description":"","sort_order":5},{"mdi_id":104,"goods_id":12,"image":"images/osc1/12/m07.jpg","description":"","sort_order":6},{"mdi_id":105,"goods_id":12,"image":"images/osc1/12/m08.jpg","description":"","sort_order":7},{"mdi_id":106,"goods_id":12,"image":"images/osc1/12/m09.jpg","description":"","sort_order":8},{"mdi_id":107,"goods_id":12,"image":"images/osc1/12/m10.jpg","description":"","sort_order":9},{"mdi_id":108,"goods_id":12,"image":"images/osc1/12/m11.jpg","description":"","sort_order":10},{"mdi_id":109,"goods_id":12,"image":"images/osc1/12/m12.jpg","description":"","sort_order":11},{"mdi_id":110,"goods_id":12,"image":"images/osc1/12/m13.jpg","description":"","sort_order":12}]
         */

        private GoodsBean goods;
        private List<ImageBean> image;
        private List<OptionsBean> options;
        private List<MobileDescriptionBean> mobile_description;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public List<ImageBean> getImage() {
            return image;
        }

        public void setImage(List<ImageBean> image) {
            this.image = image;
        }

        public List<OptionsBean> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsBean> options) {
            this.options = options;
        }

        public List<MobileDescriptionBean> getMobile_description() {
            return mobile_description;
        }

        public void setMobile_description(List<MobileDescriptionBean> mobile_description) {
            this.mobile_description = mobile_description;
        }

        public static class GoodsBean {
            /**
             * goods_id : 12
             * model : D20151107
             * sku : 套
             * location : 德化
             * quantity : 143860
             * sale_count : 0
             * image : images/osc1/12/1.jpg
             * name : 艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒
             * price : 168.00
             * points : 50
             * pay_points : 5000
             * type : 1
             * start_seckill_time : 2018-03-08 17:35:41
             * seckill_pay_points : 60
             * seckill_price : 10.00
             * summary : 艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒
             * description :
             */

            private int goods_id;
            private String model;
            private String sku;
            private String location;
            private int quantity;
            private int sale_count;
            private String image;
            private String name;
            private String price;
            private int points;
            private int pay_points;
            private int type;
            private String start_seckill_time;
            private int seckill_pay_points;
            private String seckill_price;
            private String summary;
            private String description;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getSale_count() {
                return sale_count;
            }

            public void setSale_count(int sale_count) {
                this.sale_count = sale_count;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public int getPay_points() {
                return pay_points;
            }

            public void setPay_points(int pay_points) {
                this.pay_points = pay_points;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getStart_seckill_time() {
                return start_seckill_time;
            }

            public void setStart_seckill_time(String start_seckill_time) {
                this.start_seckill_time = start_seckill_time;
            }

            public int getSeckill_pay_points() {
                return seckill_pay_points;
            }

            public void setSeckill_pay_points(int seckill_pay_points) {
                this.seckill_pay_points = seckill_pay_points;
            }

            public String getSeckill_price() {
                return seckill_price;
            }

            public void setSeckill_price(String seckill_price) {
                this.seckill_price = seckill_price;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }

        public static class ImageBean {
            /**
             * image : images/osc1/12/1.jpg
             */

            private String image;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }
        }

        public static class OptionsBean {
            /**
             * option_id : 1
             * name : 款式1
             * type : radio
             * required : 1
             * goods_option_value : [{"option_value_id":5,"name":"佛手荷花","quantity":12313,"subtract":1,"price":"1313.00","price_prefix":"+","image":"","weight":"1313.00000000","weight_prefix":"+"},{"option_value_id":5,"name":"佛手荷花","quantity":131313,"subtract":1,"price":"131.00","price_prefix":"+","image":"","weight":"111.00000000","weight_prefix":"+"}]
             */

            private int option_id;
            private String name;
            private String type;
            private int required;
            private List<GoodsOptionValueBean> goods_option_value;

            public int getOption_id() {
                return option_id;
            }

            public void setOption_id(int option_id) {
                this.option_id = option_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getRequired() {
                return required;
            }

            public void setRequired(int required) {
                this.required = required;
            }

            public List<GoodsOptionValueBean> getGoods_option_value() {
                return goods_option_value;
            }

            public void setGoods_option_value(List<GoodsOptionValueBean> goods_option_value) {
                this.goods_option_value = goods_option_value;
            }

            public static class GoodsOptionValueBean {
                /**
                 * option_value_id : 5
                 * name : 佛手荷花
                 * quantity : 12313
                 * subtract : 1
                 * price : 1313.00
                 * price_prefix : +
                 * image :
                 * weight : 1313.00000000
                 * weight_prefix : +
                 */

                private int option_value_id;
                private String name;
                private int quantity;
                private int subtract;
                private String price;
                private String price_prefix;
                private String image;
                private String weight;
                private String weight_prefix;

                public int getOption_value_id() {
                    return option_value_id;
                }

                public void setOption_value_id(int option_value_id) {
                    this.option_value_id = option_value_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getQuantity() {
                    return quantity;
                }

                public void setQuantity(int quantity) {
                    this.quantity = quantity;
                }

                public int getSubtract() {
                    return subtract;
                }

                public void setSubtract(int subtract) {
                    this.subtract = subtract;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getPrice_prefix() {
                    return price_prefix;
                }

                public void setPrice_prefix(String price_prefix) {
                    this.price_prefix = price_prefix;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getWeight_prefix() {
                    return weight_prefix;
                }

                public void setWeight_prefix(String weight_prefix) {
                    this.weight_prefix = weight_prefix;
                }
            }
        }

        public static class MobileDescriptionBean {
            /**
             * mdi_id : 98
             * goods_id : 12
             * image : images/osc1/12/m01.jpg
             * description :
             * sort_order : 0
             */

            private int mdi_id;
            private int goods_id;
            private String image;
            private String description;
            private int sort_order;

            public int getMdi_id() {
                return mdi_id;
            }

            public void setMdi_id(int mdi_id) {
                this.mdi_id = mdi_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getSort_order() {
                return sort_order;
            }

            public void setSort_order(int sort_order) {
                this.sort_order = sort_order;
            }
        }
    }
}
