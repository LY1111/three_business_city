package com.tuoyi.threebusinesscity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.Confirmation_OrderActivity;
import com.tuoyi.threebusinesscity.adapter.ShoppingCartAdapter;
import com.tuoyi.threebusinesscity.bean.DeleteCarBean;
import com.tuoyi.threebusinesscity.bean.OptionListBean;
import com.tuoyi.threebusinesscity.bean.ShoppingCartBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class OnLineCarFragment extends android.support.v4.app.Fragment implements View.OnClickListener, ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {
    private static final String TAG = "购物车";
    private View view;

    //全选
    CheckBox ckAll;
    //总额
    TextView tvShowPrice;
    //积分
    TextView tvShowIntegral;
    //结算
    TextView tvSettlement;
    //编辑
    TextView btnEdit;//tv_edit

    ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    //private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private double integral1 = 0.00;//购买商品所需积分
    private List<ShoppingCartBean.DataBean.GoodsBean> dataBean = new ArrayList<>();
    private List<ShoppingCartBean.DataBean.GoodsBean.OptionBean> optionBean = new ArrayList<>();
    private List<OptionListBean> optionListBeans = new ArrayList<>();
    private int car_id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_shopping_cart, null);
        }
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        initView();
        return view;
    }

    private void initView() {
        ckAll = (CheckBox) view.findViewById(R.id.ck_all);
        tvShowPrice = (TextView) view.findViewById(R.id.tv_show_price);
        tvShowIntegral = (TextView) view.findViewById(R.id.tv_show_integral);
        tvSettlement = (TextView) view.findViewById(R.id.tv_settlement);
        btnEdit = (TextView) view.findViewById(R.id.bt_header_right);
        list_shopping_cart = (ListView) view.findViewById(R.id.list_shopping_cart);

        btnEdit.setOnClickListener(this);
        ckAll.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        Log.d(TAG, "initView: ====" + UserBean.getToken(getContext()));
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //全选按钮
            case R.id.ck_all:
                if (dataBean.size() != 0) {
                    if (ckAll.isChecked()) {
                        for (int i = 0; i < dataBean.size(); i++) {
                            //shoppingCartBeanList.get(i).setChoosed(true);
                            dataBean.get(i).setChoosed(true);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < dataBean.size(); i++) {
                            //shoppingCartBeanList.get(i).setChoosed(false);
                            dataBean.get(i).setChoosed(false);
                        }
                        shoppingCartAdapter.notifyDataSetChanged();
                    }
                }
                statistics();
                break;
            case R.id.bt_header_right:
                flag = !flag;
                if (flag) {
                    btnEdit.setText("完成");
                    shoppingCartAdapter.isShow(false);
                } else {
                    btnEdit.setText("编辑");
                    shoppingCartAdapter.isShow(true);
                }
                break;
            case R.id.tv_settlement: //结算
                lementOnder();
                break;
        }
    }

    //初始化数据
    protected void initData() {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/get_cart")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "获取购物车数据: " + response.body());
                        Gson gson = new Gson();
                        ShoppingCartBean bean = gson.fromJson(response.body(), ShoppingCartBean.class);
                        if (bean.getCode() == 200) {
                            dataBean = bean.getData().getGoods();
                           /* for (int i = 0; i <dataBean.size() ; i++) {
                                optionBean.addAll(bean.getData().getGoods().get(i).getOption());
                            }*/
                            shoppingCartAdapter = new ShoppingCartAdapter(getContext());
                            shoppingCartAdapter.setCheckInterface(OnLineCarFragment.this);
                            shoppingCartAdapter.setModifyCountInterface(OnLineCarFragment.this);
                            list_shopping_cart.setAdapter(shoppingCartAdapter);
                            Collections.reverse(dataBean);
                            shoppingCartAdapter.setShoppingCartBeanList(dataBean);
                        }
                    }
                });
    }

    /**
     * 结算订单、支付
     */
    private void lementOnder() {
        List<ShoppingCartBean.DataBean.GoodsBean> goodsBean = new ArrayList<>();
        //选中的需要提交的商品清单
        for (ShoppingCartBean.DataBean.GoodsBean bean : dataBean) {
            boolean choosed = bean.isChoosed();
            if (choosed) {
                /*String shoppingName = bean.getName();   //商品名字
                int count = bean.getQuantity();         //商品数量
                double price = bean.getPrice();         //价钱
                //int size = bean.getDressSize();
                String attribute = bean.getAttribute(); //属性
                optionBean = bean.getOption();
                int id = bean.getGoods_id();            //商品ID
                Log.d(TAG, id + "----id---" + shoppingName + "---" + count + "---" + price + "--size----" + size + "--attr---" + attribute);*/
                goodsBean.add(bean);
            }
        }
        OptionListBean optionListBean = null;
        optionListBeans=new ArrayList<>();
        for (int i = 0; i <dataBean.size() ; i++) {
            optionBean = new ArrayList<>();
            boolean choosed = dataBean.get(i).isChoosed();
            if (choosed) {
                //goodsBean.add(dataBean.get(i));
//                optionBean.addAll(dataBean.get(i).getOption());
                optionListBean= new OptionListBean(dataBean.get(i).getOption());
                optionListBeans.add(optionListBean);
            }
        }
        //ToastUtil.show(getContext(), "总价：" + totalPrice+"======="+integral1);
       // ToastUtil.show(getContext(), "总价" +optionBean.size());
        if (goodsBean.size()>0){
            Intent intent=new Intent(getActivity(),Confirmation_OrderActivity.class);
            intent.putParcelableArrayListExtra("goodsBean", (ArrayList<? extends Parcelable>) goodsBean);
            intent.putParcelableArrayListExtra("optionBean", (ArrayList<? extends Parcelable>) optionListBeans);
            intent.putExtra("totalPrice",totalPrice);
            intent.putExtra("tvShowIntegral",integral1);
            startActivity(intent);
        }else {
            ToastUtil.show(getActivity(),"请选择要结算商品");
        }

       /* Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("goodsBean", (ArrayList<? extends Parcelable>) goodsBean);
        RxActivityTool.skipActivity(getActivity(),Confirmation_OrderActivity.class);*/
        //跳转到支付界面
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        //shoppingCartBeanList.get(position).setChoosed(isChecked);
        dataBean.get(position).setChoosed(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
   /* private boolean isAllCheck() {

        for (ShoppingCartBean group : shoppingCartBeanList) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }*/
    private boolean isAllCheck() {

        for (ShoppingCartBean.DataBean.GoodsBean group : dataBean) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
   /* public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
            }
        }
        tvShowPrice.setText("合计:" + totalPrice);
        tvSettlement.setText("结算(" + totalCount + ")");
    }*/
    public void statistics() {
        totalCount = 0;
        totalPrice = 0.00;
        integral1= 0.00;
        for (int i = 0; i < dataBean.size(); i++) {
            ShoppingCartBean.DataBean.GoodsBean shoppingCartBean = dataBean.get(i);
            if (shoppingCartBean.isChoosed()) {
                totalCount++;
                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getQuantity();
                integral1 += shoppingCartBean.getTotal_pay_points() * shoppingCartBean.getQuantity();
            }
        }
        tvShowPrice.setText("合计:¥" + totalPrice);
        tvShowIntegral.setText("积分:" + integral1);
        tvSettlement.setText("结算(" + totalCount + ")");
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
  /*  @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        currentCount++;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }*/
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean.DataBean.GoodsBean shoppingCartBean = dataBean.get(position);
        int currentCount = shoppingCartBean.getQuantity();
        currentCount++;
        shoppingCartBean.setQuantity(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
        car_id=shoppingCartBean.getCart_id();
        upDateCratNum(currentCount,car_id);
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
   /* @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
        int currentCount = shoppingCartBean.getCount();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }*/
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShoppingCartBean.DataBean.GoodsBean shoppingCartBean = dataBean.get(position);
        int currentCount = shoppingCartBean.getQuantity();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        shoppingCartBean.setQuantity(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
        car_id=shoppingCartBean.getCart_id();
        upDateCratNum(currentCount,car_id);
    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position, int car_id) {
        dataBean.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();

        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/remove_cart")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .params("cart_id", car_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "获取购物车数据: " + response.body());
                        Gson gson = new Gson();
                        DeleteCarBean bean = gson.fromJson(response.body(), DeleteCarBean.class);
                        if (bean.getCode() == 200) {

                        }
                        ToastUtil.show(getContext(), bean.getMessage());

                    }
                });
    }

    /**
     * 更新购物车数量
     * @param num
     */
    public void upDateCratNum(int num,int car_id){
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/update_cart")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .params("quantity", num+"")
                .params("cart_id", car_id+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "更新购物车数据: " + response.body());
                      /*  Gson gson = new Gson();
                        DeleteCarBean bean = gson.fromJson(response.body(), DeleteCarBean.class);
                        if (bean.getCode() == 200) {

                        }
                        ToastUtil.show(getContext(), bean.getMessage());*/

                    }
                });
    }

    @Override
    public void onStop() {
        //flag=true;
        super.onStop();
        Log.e("onStop", String.valueOf(flag));
    }

    @Override
    public void onStart() {
        super.onStart();
        //flag=true;
        Log.e("onstart", String.valueOf(flag));
        btnEdit.setText("编辑");
    }
}
