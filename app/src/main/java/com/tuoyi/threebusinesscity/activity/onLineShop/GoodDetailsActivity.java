package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andview.refreshview.utils.LogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ListTypeAdapter1;
import com.tuoyi.threebusinesscity.bean.AddCarBean;
import com.tuoyi.threebusinesscity.bean.OnLineGoodsDetailsBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.fragment.GlideImageLoader;
import com.tuoyi.threebusinesscity.util.CommonUtils;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.RxSPTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodDetailsActivity extends AppCompatActivity {

    private static final String TAG = "商品详情";
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
    /* @BindView(R.id.mFrame)
     FrameLayout  mFrame;*/
    @BindView(R.id.ll_type)
    LinearLayout ll_type;
    @BindView(R.id.wv_detail)
    WebView mWv_detail;
    @BindView(R.id.Details_mCar)
    ImageView DetailsMCar;
    private String goods_id;//商品id
    private int number;//商品数量
    private String sNumber;
    private List<String> images = new ArrayList<>();
    private List<OnLineGoodsDetailsBean.DataBean.OptionsBean> mOptionsBeanList = new ArrayList<>();
    private List<String> mPicDetails = new ArrayList<>();
    private List<String> bigMap = new ArrayList<>();
    private List<String> smallMap = new ArrayList<>();
    private String mGoodsId = "";
    private Map<String, String> mGoodsMap = new HashMap<>();
    private String mOptionStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_details);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        Log.d(TAG, "onClick: ======" + goods_id);
        goods_id = bundle.getString("goods_id");
        initData();
    }

    /* 获取商品详情 */
    private void initData() {
        OkGo.<String>post(com.tuoyi.threebusinesscity.url.Config.s+"api/app/get_goods_one")
                .tag(this)
                .params("goods_id", goods_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "c: " + response.body());
                        mOptionsBeanList = new ArrayList<>();
                        mPicDetails = new ArrayList<>();
                        Gson gson = new Gson();
                        OnLineGoodsDetailsBean bean = gson.fromJson(response.body(), OnLineGoodsDetailsBean.class);
                        if (bean.getCode() == 200) {
                            mGoodsId = String.valueOf(bean.getData().getGoods().getGoods_id());
                            mOptionsBeanList = bean.getData().getOptions();
                            if (mOptionsBeanList.size() > 0) {
                                ll_type.setVisibility(View.VISIBLE);
                            } else {
                                ll_type.setVisibility(View.GONE);
                            }
                            mPicDetails.add(bean.getData().getGoods().getImage());
                            OnLineGoodsDetailsBean.DataBean beanData = bean.getData();
                            OnLineGoodsDetailsBean.DataBean.GoodsBean goodsBean = beanData.getGoods();
                            //1:积分兑换2:现金+积分3:购物送积分4:积分秒杀
                            if (beanData.getGoods().getType() == 1) {
                                mPrice.setText("所需积分：" + goodsBean.getPay_points());
                                mName.setText(goodsBean.getName());
                                sNumber = String.valueOf(goodsBean.getQuantity());
                                mAll.setText("库存 " + sNumber);
                            } else if (beanData.getGoods().getType() == 2) {
                                mPrice.setText("¥" + goodsBean.getPrice() + "         " + goodsBean.getPay_points() + "积分");
                                mName.setText(goodsBean.getName());
                                sNumber = String.valueOf(goodsBean.getQuantity());
                                mAll.setText("库存 " + sNumber);
                            } else if (beanData.getGoods().getType() == 3) {
                                mPrice.setText("¥" + goodsBean.getPrice() + "  可得" + goodsBean.getPoints() + "积分");
                                mName.setText(goodsBean.getName());
                                sNumber = String.valueOf(goodsBean.getQuantity());
                                mAll.setText("库存 " + sNumber);
                            } else if (beanData.getGoods().getType() == 4) {
                                mPrice.setText("所需积分：" + goodsBean.getSeckill_pay_points());
                                mName.setText(goodsBean.getName());
                                sNumber = String.valueOf(goodsBean.getQuantity());
                                mAll.setText("库存 " + sNumber);
                            }
                            images = new ArrayList<>();
                            //设置banner
                            if (beanData.getImage() != null) {
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
                            //商品图文详情
                            LogUtils.e("111111111111" + bean.getData().getGoods().getDescription());
                            mWv_detail.loadData(getHtmlData(bean.getData().getGoods().getDescription()), "text/html; charset=UTF-8", null);
                            WebSettings mWebSettings = mWv_detail.getSettings();
                            mWebSettings.setUseWideViewPort(true);
                            mWebSettings.setLoadWithOverviewMode(true);
                            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

                        }

                    }
                });

    }

    @OnClick({R.id.mCollect, R.id.iv_sub, R.id.iv_add, R.id.Details_mCar, R.id.mAddCar, R.id.mBuy, R.id.ll_type})
    public void onViewClicked(View view) {
        number = Integer.parseInt(mNumber.getText().toString());
        switch (view.getId()) {
            case R.id.mCollect:
                break;
            case R.id.iv_sub:
                if (number == 1) {

                } else {
                    --number;
                    mNumber.setText(String.valueOf(number));
                }
                break;
            case R.id.iv_add:
                if (number == Integer.valueOf(sNumber)) {

                } else {
                    ++number;
                    mNumber.setText(String.valueOf(number));
                }
                break;
            case R.id.Details_mCar:
                RxSPTool.putString(this, "where", "GoodDetailsActivity");
                RxActivityTool.skipActivity(this, OnLineShopActivity.class);
                break;
            case R.id.mAddCar:
                if (!TextUtils.isEmpty(mGoodsId)) {
                    AssembleOOption();
                    addCar();
                }
                break;
            case R.id.mBuy:

                break;
            case R.id.ll_type:
                bottomDialog();
                break;
        }
    }


    /**
     * 组装加入购物车接口的option参数
     */
    public void AssembleOOption() {
//        bigMap = new ArrayList<>();
        mGoodsMap = new HashMap<>();
        for (int i = 0; i < mOptionsBeanList.size(); i++) {
            for (int j = 0; j < mOptionsBeanList.get(i).getGoods_option_value().size(); j++) {
                smallMap = new ArrayList<>();
                if (mOptionsBeanList.get(i).getGoods_option_value().get(j).isCheck()) {
                    String s = mGoodsId + "," + mOptionsBeanList.get(i).getOption_id();
                    mGoodsMap.put(s.trim(), String.valueOf(mOptionsBeanList.get(i).getGoods_option_value().get(j).getOption_value_id()).trim());
                }
            }
        }
        Gson gson = new Gson();
        mOptionStr = gson.toJson(mGoodsMap);
        Log.e(TAG, "onViewClicked: " + mOptionStr);
    }

    private void bottomDialog() {
        final Dialog bottomDialog = new Dialog(GoodDetailsActivity.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(GoodDetailsActivity.this).inflate(R.layout.dialog_head, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - CommonUtils.dp2px(GoodDetailsActivity.this, 0f);
        /*params.bottomMargin = CommonUtils.dp2px(GoodDetailsActivity.this, 8f);*/
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

        ListView list_type = bottomDialog.findViewById(R.id.list_type);
        //final ListTypeAdapter mAdapter = new ListTypeAdapter(GoodDetailsActivity.this, mOptionsBeanList);
        final ListTypeAdapter1 mAdapter = new ListTypeAdapter1(GoodDetailsActivity.this, mOptionsBeanList);
        list_type.setAdapter(mAdapter);
        mAdapter.setRadioButtonClickListener(new ListTypeAdapter1.RadioButtonClickListener() {
            @Override
            public void OnRadioButtonClickListener(OnLineGoodsDetailsBean.DataBean.OptionsBean detail, int position) {
                Log.e("8888888888", position + "");
                for (int i = 0; i < detail.getGoods_option_value().size(); i++) {
                    if (i == position) {
//                        if (detail.getGoods_option_value().get(i).isCheck()) {
//                            detail.getGoods_option_value().get(position).setCheck(false);
//                        } else {
                        detail.getGoods_option_value().get(position).setCheck(true);
//                        }
                    } else {
                        detail.getGoods_option_value().get(i).setCheck(false);
                    }
                }
//                mAdapter.setNotfiySmallList(detail);
            }
        });
        //加入购物车
        TextView tv_car = (TextView) bottomDialog.findViewById(R.id.tv_car);
        tv_car.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mGoodsId)) {
                    AssembleOOption();
                    addCar();
                }
                bottomDialog.dismiss();
            }
        });
        //立即购买
        TextView tv_buy = bottomDialog.findViewById(R.id.tv_buy);
        tv_buy.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {

                bottomDialog.dismiss();
            }
        });
        bottomDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                initData();
            }
        });
    }

    /* 加入购物车 */
    private void addCar() {
        OkGo.<String>post(com.tuoyi.threebusinesscity.url.Config.s+"api/AppProve/add_cart")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("goods_id", goods_id)
                .params("quantity", number)
                //选项 非必填
                .params("option", mOptionStr)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess加入购物车: " + response.body());
                        Gson gson = new Gson();
                        AddCarBean bean = gson.fromJson(response.body(), AddCarBean.class);
                        if (bean.getCode() == 200) {
                            ToastUtil.show(GoodDetailsActivity.this, "加入成功");
                            initData();
                        } else {
                            ToastUtil.show(GoodDetailsActivity.this, bean.getMessage());
                        }

                    }
                });
    }


    /**
     * 商品图文详情
     *
     * @param
     */
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }


    private void showPopupWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.popup_add_car, null);
    }

    @OnClick(R.id.Details_mCar)
    public void onViewClicked() {
    }
}
