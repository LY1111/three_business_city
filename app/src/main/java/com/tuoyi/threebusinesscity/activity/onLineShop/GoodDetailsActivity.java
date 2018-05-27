package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.ShoppingCartActivity;
import com.tuoyi.threebusinesscity.bean.OnLineGoodsDetailsBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.fragment.GlideImageLoader;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodDetailsActivity extends AppCompatActivity {

    private static final String TAG = "商品详情" ;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mPrice)
    TextView mPrice;
    @BindView(R.id.mName)
    TextView mName;
    @BindView(R.id.mCollect)
    TextView mCollect;
    @BindView(R.id.mAll)
    TextView mAll;
    @BindView(R.id.iv_sub)
    TextView ivSub;
    @BindView(R.id.mNumber)
    TextView mNumber;
    @BindView(R.id.iv_add)
    TextView ivAdd;
    @BindView(R.id.mFrame)
    FrameLayout mFrame;
    private String goods_id;//商品id
    private String sNumber;//商品数量
    private List<String> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_details);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        goods_id = bundle.getString("goods_id");
//        goods_id = "12";
        initData();
    }

    /* 获取商品详情 */
    private void initData() {
        OkGo.<String>post("http://sszl.tuoee.com/api/app/get_goods_one")
                .tag(this)
                .params("goods_id",goods_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        OnLineGoodsDetailsBean bean = gson.fromJson(response.body(),OnLineGoodsDetailsBean.class);
                        if (bean.getCode() == 200){
                           OnLineGoodsDetailsBean.DataBean beanData = bean.getData();
                           //设置banner
                           if (beanData.getImage() !=null ){
                               for (int i = 0; i < beanData.getImage().size(); i++) {
                                   images.add(beanData.getImage().get(i).getImage());
                               }
                               //设置图片加载器
                               mBanner.setImageLoader(new GlideImageLoader());
                               //设置图片集合
                               mBanner.setImages(images);
                               //banner设置方法全部调用完毕时最后调用
                               mBanner.start();
                           }
                           //其他信息
                            OnLineGoodsDetailsBean.DataBean.GoodsBean goodsBean = beanData.getGoods();
                           mName.setText(goodsBean.getName());
                           mPrice.setText(goodsBean.getPrice());
                           mAll.setText(String.valueOf(goodsBean.getQuantity()));
                        }

                        }
                });

    }

    @OnClick({R.id.mCollect, R.id.iv_sub, R.id.iv_add, R.id.mCar, R.id.mAddCar, R.id.mBuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mCollect:
                break;
            case R.id.iv_sub:
                break;
            case R.id.iv_add:
                break;
            case R.id.mCar:
                JumpUtil.newInstance().jumpRight(this, ShoppingCartActivity.class);
                break;
            case R.id.mAddCar:
                addCar();
                break;
            case R.id.mBuy:
                break;
        }
    }

    /* 加入购物车 */
    private void addCar() {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/add_cart")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("goods_id",goods_id)
                .params("quantity",sNumber)
                //选项 非必填
                .params("option","")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess加入购物车: " + response.body());

                    }
                });
    }

    private void showPopupWindow(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.popup_add_car,null);
    }
}
