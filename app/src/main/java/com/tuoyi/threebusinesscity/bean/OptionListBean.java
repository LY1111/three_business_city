package com.tuoyi.threebusinesscity.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OptionListBean extends ShoppingCartBean implements Parcelable {


    public OptionListBean(List<DataBean.GoodsBean.OptionBean> optionBeans) {
        this.optionBeans = optionBeans;
    }

    protected OptionListBean(Parcel in) {
        optionBeans = in.createTypedArrayList(DataBean.GoodsBean.OptionBean.CREATOR);
    }

    public static final Creator<OptionListBean> CREATOR = new Creator<OptionListBean>() {
        @Override
        public OptionListBean createFromParcel(Parcel in) {
            return new OptionListBean(in);
        }

        @Override
        public OptionListBean[] newArray(int size) {
            return new OptionListBean[size];
        }
    };

    public List<ShoppingCartBean.DataBean.GoodsBean.OptionBean> getOptionBeans() {
        return optionBeans;
    }

    public void setOptionBeans(List<ShoppingCartBean.DataBean.GoodsBean.OptionBean> optionBeans) {
        this.optionBeans = optionBeans;
    }

    private List<ShoppingCartBean.DataBean.GoodsBean.OptionBean> optionBeans;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(optionBeans);
    }
}
