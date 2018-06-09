package com.tuoyi.threebusinesscity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by AYD on 2016/11/22.
 * <p>
 * 购物车
 */
public class ShoppingCartBean {
    /**
     * status : 1
     * code : 200
     * message : 成功
     * data : {"goods":[{"cart_id":32,"goods_id":4,"name":"艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网","model":"D20151107","shipping":0,"image":"public/uploads/cache/images/osc1/4/1-80x80.jpg","quantity":1,"minimum":1,"subtract":1,"price":30,"total":30,"pay_points":5000,"total_pay_points":5000,"total_return_points":50,"points":50,"weight":200,"weight_class_id":2,"length":"20.00000000","width":"20.00000000","height":"20.00000000","length_class_id":1,"stock":true,"option":[{"goods_option_id":1,"goods_option_value_id":2,"option_id":2,"option_value_id":4,"name":"款式","value":"陀罗边","type":"radio","quantity":250,"subtract":1,"price":"0.00","price_prefix":"+","weight":"0.00000000","weight_prefix":"+"}],"supplier_id":4,"type":1},{"cart_id":31,"goods_id":12,"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","model":"D20151107","shipping":1,"image":"public/uploads/cache/images/osc1/12/1-80x80.jpg","quantity":3,"minimum":1,"subtract":1,"price":-1143,"total":-3429,"pay_points":5000,"total_pay_points":15000,"total_return_points":150,"points":50,"weight":-3927,"weight_class_id":1,"length":"20.00000000","width":"20.00000000","height":"20.00000000","length_class_id":1,"stock":true,"option":[{"goods_option_id":67,"goods_option_value_id":188,"option_id":1,"option_value_id":5,"name":"款式1","value":"佛手荷花","type":"radio","quantity":12313,"subtract":1,"price":"1313.00","price_prefix":"-","weight":"1313.00000000","weight_prefix":"-"},{"goods_option_id":68,"goods_option_value_id":190,"option_id":5,"option_value_id":16,"name":"爱的选项","value":"小草","type":"radio","quantity":10,"subtract":1,"price":"1.00","price_prefix":"+","weight":"1.00000000","weight_prefix":"+"},{"goods_option_id":69,"goods_option_value_id":195,"option_id":3,"option_value_id":7,"name":"颜色","value":"红色","type":"select","quantity":10,"subtract":1,"price":"1.00","price_prefix":"+","weight":"1.00000000","weight_prefix":"+"}],"supplier_id":2,"type":1}],"total_all_price":-3399,"total_all_pay_points":20000,"total_all_points":200}
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
         * goods : [{"cart_id":32,"goods_id":4,"name":"艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网","model":"D20151107","shipping":0,"image":"public/uploads/cache/images/osc1/4/1-80x80.jpg","quantity":1,"minimum":1,"subtract":1,"price":30,"total":30,"pay_points":5000,"total_pay_points":5000,"total_return_points":50,"points":50,"weight":200,"weight_class_id":2,"length":"20.00000000","width":"20.00000000","height":"20.00000000","length_class_id":1,"stock":true,"option":[{"goods_option_id":1,"goods_option_value_id":2,"option_id":2,"option_value_id":4,"name":"款式","value":"陀罗边","type":"radio","quantity":250,"subtract":1,"price":"0.00","price_prefix":"+","weight":"0.00000000","weight_prefix":"+"}],"supplier_id":4,"type":1},{"cart_id":31,"goods_id":12,"name":"艺创 青花白瓷手绘荷花整套功夫茶具陶瓷 盖碗 茶杯创意礼盒","model":"D20151107","shipping":1,"image":"public/uploads/cache/images/osc1/12/1-80x80.jpg","quantity":3,"minimum":1,"subtract":1,"price":-1143,"total":-3429,"pay_points":5000,"total_pay_points":15000,"total_return_points":150,"points":50,"weight":-3927,"weight_class_id":1,"length":"20.00000000","width":"20.00000000","height":"20.00000000","length_class_id":1,"stock":true,"option":[{"goods_option_id":67,"goods_option_value_id":188,"option_id":1,"option_value_id":5,"name":"款式1","value":"佛手荷花","type":"radio","quantity":12313,"subtract":1,"price":"1313.00","price_prefix":"-","weight":"1313.00000000","weight_prefix":"-"},{"goods_option_id":68,"goods_option_value_id":190,"option_id":5,"option_value_id":16,"name":"爱的选项","value":"小草","type":"radio","quantity":10,"subtract":1,"price":"1.00","price_prefix":"+","weight":"1.00000000","weight_prefix":"+"},{"goods_option_id":69,"goods_option_value_id":195,"option_id":3,"option_value_id":7,"name":"颜色","value":"红色","type":"select","quantity":10,"subtract":1,"price":"1.00","price_prefix":"+","weight":"1.00000000","weight_prefix":"+"}],"supplier_id":2,"type":1}]
         * total_all_price : -3399
         * total_all_pay_points : 20000
         * total_all_points : 200
         */

        private int total_all_price;
        private int total_all_pay_points;
        private int total_all_points;
        private List<GoodsBean> goods;

        public int getTotal_all_price() {
            return total_all_price;
        }

        public void setTotal_all_price(int total_all_price) {
            this.total_all_price = total_all_price;
        }

        public int getTotal_all_pay_points() {
            return total_all_pay_points;
        }

        public void setTotal_all_pay_points(int total_all_pay_points) {
            this.total_all_pay_points = total_all_pay_points;
        }

        public int getTotal_all_points() {
            return total_all_points;
        }

        public void setTotal_all_points(int total_all_points) {
            this.total_all_points = total_all_points;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean implements Parcelable {
            @Override
            public String toString() {
                return "GoodsBean{" +
                        "cart_id=" + cart_id +
                        ", goods_id=" + goods_id +
                        ", name='" + name + '\'' +
                        ", model='" + model + '\'' +
                        ", shipping=" + shipping +
                        ", image='" + image + '\'' +
                        ", quantity=" + quantity +
                        ", minimum=" + minimum +
                        ", subtract=" + subtract +
                        ", price=" + price +
                        ", total=" + total +
                        ", pay_points=" + pay_points +
                        ", total_pay_points=" + total_pay_points +
                        ", total_return_points=" + total_return_points +
                        ", points=" + points +
                        ", weight=" + weight +
                        ", weight_class_id=" + weight_class_id +
                        ", length='" + length + '\'' +
                        ", width='" + width + '\'' +
                        ", height='" + height + '\'' +
                        ", length_class_id=" + length_class_id +
                        ", stock=" + stock +
                        ", supplier_id=" + supplier_id +
                        ", type=" + type +
                        ", option=" + option +
                        ", isChoosed=" + isChoosed +
                        ", isCheck=" + isCheck +
                        '}';
            }

            /**
             * cart_id : 32
             * goods_id : 4
             * name : 艺创 青花甜白功夫茶具陶瓷配件 茶叶过滤网隔茶渣网
             * model : D20151107
             * shipping : 0
             * image : public/uploads/cache/images/osc1/4/1-80x80.jpg
             * quantity : 1
             * minimum : 1
             * subtract : 1
             * price : 30
             * total : 30
             * pay_points : 5000
             * total_pay_points : 5000
             * total_return_points : 50
             * points : 50
             * weight : 200
             * weight_class_id : 2
             * length : 20.00000000
             * width : 20.00000000
             * height : 20.00000000
             * length_class_id : 1
             * stock : true
             * option : [{"goods_option_id":1,"goods_option_value_id":2,"option_id":2,"option_value_id":4,"name":"款式","value":"陀罗边","type":"radio","quantity":250,"subtract":1,"price":"0.00","price_prefix":"+","weight":"0.00000000","weight_prefix":"+"}]
             * supplier_id : 4
             * type : 1
             */


            private int cart_id;
            private int goods_id;
            private String name;
            private String model;
            private int shipping;
            private String image;
            private int quantity;
            private int minimum;
            private int subtract;
            private int price;
            private int total;
            private int pay_points;
            private int total_pay_points;
            private int total_return_points;
            private int points;
            private int weight;
            private int weight_class_id;
            private String length;
            private String width;
            private String height;
            private int length_class_id;
            private boolean stock;
            private int supplier_id;
            private int type;
            private List<OptionBean> option;

            public boolean isChoosed;
            public boolean isCheck = false;
           /* private double price;

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }*/

            protected GoodsBean(Parcel in) {
                cart_id = in.readInt();
                goods_id = in.readInt();
                name = in.readString();
                model = in.readString();
                shipping = in.readInt();
                image = in.readString();
                quantity = in.readInt();
                minimum = in.readInt();
                subtract = in.readInt();
                price = in.readInt();
                total = in.readInt();
                pay_points = in.readInt();
                total_pay_points = in.readInt();
                total_return_points = in.readInt();
                points = in.readInt();
                weight = in.readInt();
                weight_class_id = in.readInt();
                length = in.readString();
                width = in.readString();
                height = in.readString();
                length_class_id = in.readInt();
                stock = in.readByte() != 0;
                supplier_id = in.readInt();
                type = in.readInt();
                isChoosed = in.readByte() != 0;
                isCheck = in.readByte() != 0;
            }

            public static final Creator<GoodsBean> CREATOR = new Creator<GoodsBean>() {
                @Override
                public GoodsBean createFromParcel(Parcel in) {
                    return new GoodsBean(in);
                }

                @Override
                public GoodsBean[] newArray(int size) {
                    return new GoodsBean[size];
                }
            };

            public boolean isChoosed() {
                return isChoosed;
            }

            public void setChoosed(boolean choosed) {
                isChoosed = choosed;
            }

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public int getShipping() {
                return shipping;
            }

            public void setShipping(int shipping) {
                this.shipping = shipping;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getMinimum() {
                return minimum;
            }

            public void setMinimum(int minimum) {
                this.minimum = minimum;
            }

            public int getSubtract() {
                return subtract;
            }

            public void setSubtract(int subtract) {
                this.subtract = subtract;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPay_points() {
                return pay_points;
            }

            public void setPay_points(int pay_points) {
                this.pay_points = pay_points;
            }

            public int getTotal_pay_points() {
                return total_pay_points;
            }

            public void setTotal_pay_points(int total_pay_points) {
                this.total_pay_points = total_pay_points;
            }

            public int getTotal_return_points() {
                return total_return_points;
            }

            public void setTotal_return_points(int total_return_points) {
                this.total_return_points = total_return_points;
            }

            public int getPoints() {
                return points;
            }

            public void setPoints(int points) {
                this.points = points;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getWeight_class_id() {
                return weight_class_id;
            }

            public void setWeight_class_id(int weight_class_id) {
                this.weight_class_id = weight_class_id;
            }

            public String getLength() {
                return length;
            }

            public void setLength(String length) {
                this.length = length;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public int getLength_class_id() {
                return length_class_id;
            }

            public void setLength_class_id(int length_class_id) {
                this.length_class_id = length_class_id;
            }

            public boolean isStock() {
                return stock;
            }

            public void setStock(boolean stock) {
                this.stock = stock;
            }

            public int getSupplier_id() {
                return supplier_id;
            }

            public void setSupplier_id(int supplier_id) {
                this.supplier_id = supplier_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<OptionBean> getOption() {
                return option;
            }

            public void setOption(List<OptionBean> option) {
                this.option = option;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(cart_id);
                dest.writeInt(goods_id);
                dest.writeString(name);
                dest.writeString(model);
                dest.writeInt(shipping);
                dest.writeString(image);
                dest.writeInt(quantity);
                dest.writeInt(minimum);
                dest.writeInt(subtract);
                dest.writeInt(price);
                dest.writeInt(total);
                dest.writeInt(pay_points);
                dest.writeInt(total_pay_points);
                dest.writeInt(total_return_points);
                dest.writeInt(points);
                dest.writeInt(weight);
                dest.writeInt(weight_class_id);
                dest.writeString(length);
                dest.writeString(width);
                dest.writeString(height);
                dest.writeInt(length_class_id);
                dest.writeByte((byte) (stock ? 1 : 0));
                dest.writeInt(supplier_id);
                dest.writeInt(type);
                dest.writeByte((byte) (isChoosed ? 1 : 0));
                dest.writeByte((byte) (isCheck ? 1 : 0));
            }

            public static class OptionBean implements Parcelable {
                @Override
                public String toString() {
                    return "OptionBean{" +
                            "goods_option_id=" + goods_option_id +
                            ", goods_option_value_id=" + goods_option_value_id +
                            ", option_id=" + option_id +
                            ", option_value_id=" + option_value_id +
                            ", name='" + name + '\'' +
                            ", value='" + value + '\'' +
                            ", type='" + type + '\'' +
                            ", quantity=" + quantity +
                            ", subtract=" + subtract +
                            ", price='" + price + '\'' +
                            ", price_prefix='" + price_prefix + '\'' +
                            ", weight='" + weight + '\'' +
                            ", weight_prefix='" + weight_prefix + '\'' +
                            '}';
                }

                /**
                 * goods_option_id : 1
                 * goods_option_value_id : 2
                 * option_id : 2
                 * option_value_id : 4
                 * name : 款式
                 * value : 陀罗边
                 * type : radio
                 * quantity : 250
                 * subtract : 1
                 * price : 0.00
                 * price_prefix : +
                 * weight : 0.00000000
                 * weight_prefix : +
                 */


                private int goods_option_id;
                private int goods_option_value_id;
                private int option_id;
                private int option_value_id;
                private String name;
                private String value;
                private String type;
                private int quantity;
                private int subtract;
                private String price;
                private String price_prefix;
                private String weight;
                private String weight_prefix;

                protected OptionBean(Parcel in) {
                    goods_option_id = in.readInt();
                    goods_option_value_id = in.readInt();
                    option_id = in.readInt();
                    option_value_id = in.readInt();
                    name = in.readString();
                    value = in.readString();
                    type = in.readString();
                    quantity = in.readInt();
                    subtract = in.readInt();
                    price = in.readString();
                    price_prefix = in.readString();
                    weight = in.readString();
                    weight_prefix = in.readString();
                }

                public static final Creator<OptionBean> CREATOR = new Creator<OptionBean>() {
                    @Override
                    public OptionBean createFromParcel(Parcel in) {
                        return new OptionBean(in);
                    }

                    @Override
                    public OptionBean[] newArray(int size) {
                        return new OptionBean[size];
                    }
                };

                public int getGoods_option_id() {
                    return goods_option_id;
                }

                public void setGoods_option_id(int goods_option_id) {
                    this.goods_option_id = goods_option_id;
                }

                public int getGoods_option_value_id() {
                    return goods_option_value_id;
                }

                public void setGoods_option_value_id(int goods_option_value_id) {
                    this.goods_option_value_id = goods_option_value_id;
                }

                public int getOption_id() {
                    return option_id;
                }

                public void setOption_id(int option_id) {
                    this.option_id = option_id;
                }

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

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(goods_option_id);
                    dest.writeInt(goods_option_value_id);
                    dest.writeInt(option_id);
                    dest.writeInt(option_value_id);
                    dest.writeString(name);
                    dest.writeString(value);
                    dest.writeString(type);
                    dest.writeInt(quantity);
                    dest.writeInt(subtract);
                    dest.writeString(price);
                    dest.writeString(price_prefix);
                    dest.writeString(weight);
                    dest.writeString(weight_prefix);
                }
            }
        }
    }


/*
    private int id;
    private String imageUrl;
    private String shoppingName;

    private int dressSize;
    private String attribute;

    private double price;

    public boolean isChoosed;
    public boolean isCheck = false;
    private int count;



    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public ShoppingCartBean() {
    }

    public ShoppingCartBean(int id, String shoppingName, String attribute, int dressSize,
                            double price, int count) {
        this.id = id;
        this.shoppingName = shoppingName;
        this.attribute = attribute;
        this.dressSize = dressSize;
        this.price = price;
        this.count = count;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChoosed() {
        return isChoosed;
    }

    public void setChoosed(boolean choosed) {
        isChoosed = choosed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShoppingName() {
        return shoppingName;
    }

    public void setShoppingName(String shoppingName) {
        this.shoppingName = shoppingName;
    }


    public int getDressSize() {
        return dressSize;
    }

    public void setDressSize(int dressSize) {
        this.dressSize = dressSize;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }*/


}
