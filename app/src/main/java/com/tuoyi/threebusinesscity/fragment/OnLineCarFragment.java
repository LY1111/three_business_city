package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ShoppingCartAdapter;
import com.tuoyi.threebusinesscity.bean.ShoppingCartBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class OnLineCarFragment extends android.support.v4.app.Fragment implements View.OnClickListener, ShoppingCartAdapter.CheckInterface, ShoppingCartAdapter.ModifyCountInterface {
    private static final String TAG = "购物车" ;
    private View view;

    //全选
    CheckBox ckAll;
    //总额
    TextView tvShowPrice;
    //结算
    TextView tvSettlement;
    //编辑
    TextView btnEdit;//tv_edit

    ListView list_shopping_cart;
    private ShoppingCartAdapter shoppingCartAdapter;
    private boolean flag = false;
    private List<ShoppingCartBean> shoppingCartBeanList = new ArrayList<>();
    private boolean mSelect;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.activity_shopping_cart,null);
        }
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
        initView();
        return view;
    }

    private void initView() {
        ckAll = (CheckBox) view.findViewById(R.id.ck_all);
        tvShowPrice = (TextView) view.findViewById(R.id.tv_show_price);
        tvSettlement = (TextView)  view.findViewById(R.id.tv_settlement);
        btnEdit = (TextView)  view.findViewById(R.id.bt_header_right);
        list_shopping_cart = (ListView)  view.findViewById(R.id.list_shopping_cart);

        btnEdit.setOnClickListener(this);
        ckAll.setOnClickListener(this);
        tvSettlement.setOnClickListener(this);
        initData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //全选按钮
            case R.id.ck_all:
//                if (shoppingCartBeanList.size() != 0) {
//                    if (ckAll.isChecked()) {
//                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                            shoppingCartBeanList.get(i).setChoosed(true);
//                        }
//                        shoppingCartAdapter.notifyDataSetChanged();
//                    } else {
//                        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//                            shoppingCartBeanList.get(i).setChoosed(false);
//                        }
//                        shoppingCartAdapter.notifyDataSetChanged();
//                    }
//                }
//                statistics();
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
        OkGo.<String>post("http://wakuang.triphee.com/api/AppProve/get_cart")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "onSuccess: " + response.body());
                    }
                });

//        for (int i = 0; i < 5; i++) {
//            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
//            shoppingCartBean.setShoppingName("上档次的T桖");
//            shoppingCartBean.setDressSize(20);
//            shoppingCartBean.setId(i);
//            shoppingCartBean.setPrice(30.6);
//            shoppingCartBean.setCount(1);
//            shoppingCartBean.setImageUrl("https://img.alicdn.com/bao/uploaded/i2/TB1YfERKVXXXXanaFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg");
//            shoppingCartBeanList.add(shoppingCartBean);
//        }
//        for (int i = 0; i < 5; i++) {
//            ShoppingCartBean shoppingCartBean = new ShoppingCartBean();
//            shoppingCartBean.setShoppingName("瑞士正品夜光男女士手表情侣精钢带男表防水石英学生非天王星机械");
//            shoppingCartBean.setAttribute("黑白色");
//            shoppingCartBean.setPrice(89);
//            shoppingCartBean.setId(i + 2);
//            shoppingCartBean.setCount(3);
//            shoppingCartBean.setImageUrl("https://gd1.alicdn.com/imgextra/i1/2160089910/TB2M_NSbB0kpuFjSsppXXcGTXXa_!!2160089910.jpg");
//            shoppingCartBeanList.add(shoppingCartBean);
//        }
//        shoppingCartAdapter = new ShoppingCartAdapter(getContext());
//        shoppingCartAdapter.setCheckInterface(this);
//        shoppingCartAdapter.setModifyCountInterface(this);
//        list_shopping_cart.setAdapter(shoppingCartAdapter);
//        shoppingCartAdapter.setShoppingCartBeanList(shoppingCartBeanList);
    }
    /**
     * 结算订单、支付
     */
    private void lementOnder() {
//        //选中的需要提交的商品清单
//        for (ShoppingCartBean bean : shoppingCartBeanList) {
//            boolean choosed = bean.isChoosed();
//            if (choosed) {
//                String shoppingName = bean.getShoppingName();
//                int count = bean.getCount();
//                double price = bean.getPrice();
//                int size = bean.getDressSize();
//                String attribute = bean.getAttribute();
//                int id = bean.getId();
////                Log.d(TAG,id+"----id---"+shoppingName+"---"+count+"---"+price+"--size----"+size+"--attr---"+attribute);
//            }
//        }
//        ToastUtil.show(getContext(), "总价：" + totalPrice);
//
//        //跳转到支付界面
    }

    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
//        shoppingCartBeanList.get(position).setChoosed(isChecked);
//        if (isAllCheck())
//            ckAll.setChecked(true);
//        else
//            ckAll.setChecked(false);
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();
    }

    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

//        for (ShoppingCartBean group : shoppingCartBeanList) {
//            if (!group.isChoosed())
//                return false;
//        }
        return true;
    }

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
//        totalCount = 0;
//        totalPrice = 0.00;
//        for (int i = 0; i < shoppingCartBeanList.size(); i++) {
//            ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(i);
//            if (shoppingCartBean.isChoosed()) {
//                totalCount++;
//                totalPrice += shoppingCartBean.getPrice() * shoppingCartBean.getCount();
//            }
//        }
//        tvShowPrice.setText("合计:" + totalPrice);
//        tvSettlement.setText("结算(" + totalCount + ")");
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
//        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
//        int currentCount = shoppingCartBean.getCount();
//        currentCount++;
//        shoppingCartBean.setCount(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
//        ShoppingCartBean shoppingCartBean = shoppingCartBeanList.get(position);
//        int currentCount = shoppingCartBean.getCount();
//        if (currentCount == 1) {
//            return;
//        }
//        currentCount--;
//        shoppingCartBean.setCount(currentCount);
//        ((TextView) showCountView).setText(currentCount + "");
//        shoppingCartAdapter.notifyDataSetChanged();
//        statistics();
    }

    /**
     * 删除
     *
     * @param position
     */
    @Override
    public void childDelete(int position) {
        shoppingCartBeanList.remove(position);
        shoppingCartAdapter.notifyDataSetChanged();
        statistics();
    }

}
