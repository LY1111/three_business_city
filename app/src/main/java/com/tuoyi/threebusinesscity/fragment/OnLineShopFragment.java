package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.onLineShop.GoodDetailsActivity;
import com.tuoyi.threebusinesscity.activity.onLineShop.SearchGoodsListActivity;
import com.tuoyi.threebusinesscity.adapter.KillAdapter1;
import com.tuoyi.threebusinesscity.adapter.OnLineShopMenuAdapter;
import com.tuoyi.threebusinesscity.bean.BannerBean;
import com.tuoyi.threebusinesscity.bean.KillBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopAdBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopListBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopMenuBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.KeyBoardUtils;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.tuoyi.threebusinesscity.widget.AutoLinearLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.widget.LinearLayout.HORIZONTAL;
import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by glp on 2018/5/15.
 * 描述：
 */

public class OnLineShopFragment extends Fragment implements AMapLocationListener {

    private static final String TAG = "线上商城";
    Unbinder unbinder;
    @BindView(R.id.mLocation_tv)
    TextView mLocationTv;
    @BindView(R.id.mLocation)
    LinearLayout mLocation;
    @BindView(R.id.mSearch)
    EditText mSearch;
    @BindView(R.id.mBanner1)
    Banner mBanner1;
    @BindView(R.id.mainf_list)
    RecyclerView mainfHeadGridList;
    @BindView(R.id.mImg1)
    ImageView mImg1;
    @BindView(R.id.mImg2)
    ImageView mImg2;
    @BindView(R.id.mImg3)
    ImageView mImg3;
    @BindView(R.id.integral_recyclerView)
    RecyclerView integralRecyclerView;
    @BindView(R.id.LatestGoods_Recyclerview)
    RecyclerView LatestGoodsRecyclerview;

    private View view;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private double lat, lon;

    private List<String> images = new ArrayList<>();

    private MyAdapter adapter;
    private List<OnLineShopListBean.DataBean> beanList = new ArrayList<>();
    private OnLineShopListBean bean;
    private OnLineShopMenuAdapter menuAdapter;
    private List<OnLineShopMenuBean.DataBean> headGridList;
    private int mPosition = 0;
    private int mListType = 0;
    private String sSearch;
    private String sImg_id;//积分活动点击id
    private List<OnLineShopAdBean.DataBean> dataBean = new ArrayList<>();
    private List<KillBean.DataBean> killbeanList = new ArrayList<>();
    private KillAdapter1 mKillAdapter1;

    public static OnLineShopFragment newInstance() {
        OnLineShopFragment fragment = new OnLineShopFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_online_shop_fragment, null);
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LatestGoodsRecyclerview.setNestedScrollingEnabled(false);
        integralRecyclerView.setNestedScrollingEnabled(false);
        /* 设置倒计时 单位秒 */
      /*  int sum = 6000;
        timeView1.addTime(sum);
        timeView1.start();
        timeView2.addTime(sum);
        timeView2.start();
        timeView3.addTime(sum);
        timeView3.start();
        timeView4.addTime(sum);
        timeView4.start();*/

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
//                    Toast.makeText(getContext(), "你点击了搜索键。。。。", Toast.LENGTH_SHORT).show();
                    sSearch = mSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(sSearch)) {
                        mListType = 2;
                        Bundle bundle = new Bundle();
                        bundle.putString("search", sSearch);
                        bundle.putString("s", "搜索");
                        JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                    } else {
                        Toast.makeText(getContext(), "请输入后搜索商品", Toast.LENGTH_SHORT).show();
                    }
                    KeyBoardUtils.closeKeybord(mSearch, getContext());
                    return true;
                }
                return false;
            }
        });


        initLocation();
        initBanner();
        initMenu();
        initData();
        initAd();
        initKill();
    }

    /* 积分秒杀前4个 */
    private void initKill() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_integral_second_kill")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "onSuccess积分秒杀: " + response.body());
                        Gson gson = new Gson();
                        KillBean bean = gson.fromJson(response.body(), KillBean.class);
                        if (bean.getCode() == 200) {
                            killbeanList = bean.getData();
                           /* Glide.with(getContext()).load(IMGS + killbeanList.get(0).getImage()).into(mHandPick1Pic);
                            mHandPick1Text.setText(killbeanList.get(0).getName());
                            mHandPick1Point.setText("积分" + killbeanList.get(0).getPrice());

                            Glide.with(getContext()).load(IMGS + killbeanList.get(1).getImage()).into(mHandPick2Pic);
                            mHandPick2Text.setText(killbeanList.get(1).getName());
                            mHandPick2Point.setText("积分" + killbeanList.get(1).getPrice());

                            Glide.with(getContext()).load(IMGS + killbeanList.get(2).getImage()).into(mHandPick3Pic);
                            mHandPick3Text.setText(killbeanList.get(2).getName());
                            mHandPick3Point.setText("积分" + killbeanList.get(2).getPrice());

                            Glide.with(getContext()).load(IMGS + killbeanList.get(3).getImage()).into(mHandPick4Pic);
                            mHandPick4Text.setText(killbeanList.get(3).getName());
                            mHandPick4Point.setText("积分" + killbeanList.get(3).getPrice());*/

                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            integralRecyclerView.setLayoutManager(manager);
                            Collections.reverse(killbeanList);
                            mKillAdapter1 = new KillAdapter1(R.layout.item_receive, killbeanList);
                            integralRecyclerView.setAdapter(mKillAdapter1);
                            mKillAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("goods_id", killbeanList.get(position).getGoods_id() + "");
                                    JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class, bundle);
                                }
                            });
                        } else {
                            ToastUtil.show(getContext(), bean.getMessage());
                        }
                    }
                });
    }

    /* 获取积分活动-3张图 */
    private void initAd() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_ad")
                .tag(this)
                .params("num", "10")
                .params("page", 1)
                .params("type", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.i(TAG, "onSuccess积分活动: " + response.body());
                        Gson gson = new Gson();
                        OnLineShopAdBean bean = gson.fromJson(response.body(), OnLineShopAdBean.class);
                        if (bean.getCode() == 200) {
                            dataBean = bean.getData();
                            RequestOptions options=new RequestOptions().placeholder(R.drawable.s_img);
                            Glide.with(getActivity()).load(IMGS + dataBean.get(0).getPicurl()).apply(options).into(mImg1);
                            Glide.with(getActivity()).load(IMGS + dataBean.get(1).getPicurl()).apply(options).into(mImg2);
                            Glide.with(getActivity()).load(IMGS + dataBean.get(2).getPicurl()).apply(options).into(mImg3);
                            initAdClick();
                        } else {
                            ToastUtil.show(getActivity(), bean.getMessage());
                        }
                    }
                });
    }

    /* 积分活动点击 跳转到商品详情*/
    private void initAdClick() {
        final Bundle bundle = new Bundle();
        mImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ======" + dataBean.get(0).getValue());
                sImg_id = dataBean.get(0).getValue();
                bundle.putString("goods_id", sImg_id);
                JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class, bundle);
            }
        });
        mImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sImg_id = dataBean.get(1).getValue();
                bundle.putString("goods_id", sImg_id);
                JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class, bundle);
            }
        });
        mImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sImg_id = dataBean.get(2).getValue();
                bundle.putString("goods_id", sImg_id);
                JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class, bundle);
            }
        });
    }

    /* 获取顶部分类 */
    private void initMenu() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_category")
                .tag(this)
                .params("num", "10")
                .params("page", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        OnLineShopMenuBean menuBean = gson.fromJson(response.body(), OnLineShopMenuBean.class);
                        headGridList = menuBean.getData();
                        initMenuList();
                    }
                });
    }

    /* 添加数据 */
    private void initData() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_latest_goods")
                .tag(this)
                .params("num", "10")
                .params("page", "1")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        OnLineShopListBean goodsList = gson.fromJson(response.body(), OnLineShopListBean.class);
                        if (goodsList.getCode() == 200) {
                            beanList = goodsList.getData();
                            adapter = new MyAdapter(beanList);
                            LatestGoodsRecyclerview.setAdapter(adapter);
                            final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                                @Override
                                public int getSpanSize(int position) {
                                    return adapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
                                }
                            });
                            LatestGoodsRecyclerview.setLayoutManager(layoutManager);
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.show(getContext(), goodsList.getMessage());
                        }
                    }
                });

    }

    /* 顶部广告 */
    private void initMenuList() {
        mainfHeadGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 5));

        menuAdapter = new OnLineShopMenuAdapter(getContext(), headGridList);
        mainfHeadGridList.setAdapter(menuAdapter);
        menuAdapter.setOnItemClickListener(new OnLineShopMenuAdapter.MyOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
                mListType = 1;
                mPosition = position;
            }
        });
    }

    /* banner广告 */
    private void initBanner() {
        OkGo.<String>post("http://sszl.tuoee.com/api/app/link")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());

                        Gson gson = new Gson();
                        BannerBean bannerBean = gson.fromJson(response.body(), BannerBean.class);
                        if (bannerBean.getCode() == 200) {
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            images = new ArrayList<>();
                            if (data != null) {
                                for (int i = 0; i < bannerBean.getData().size(); i++) {
                                    images.add(data.get(i).getPicurl());
                                }
                                initBanner2();
                            }

                        }
                    }
                });
    }

    /**
     * 轮播图设置
     */
    private void initBanner2() {
        //设置图片加载器
        mBanner1.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner1.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner1.start();
        mBanner1.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    mBanner1.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner1.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /* 定位 */
    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }


    //定位回调监听器
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析aMapLocation获取相应内容。
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                aMapLocation.getAoiName();//获取当前定位点的AOI信息
                aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                aMapLocation.getFloor();//获取当前室内定位的楼层
                aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                //获取定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);
                lon = aMapLocation.getLongitude();//获取经度
                lat = aMapLocation.getLatitude();//获取纬度
                if (TextUtils.isEmpty(aMapLocation.getCity().trim())) {
                    if (mLocationClient.isStarted()) {
                        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁

                    }
                    mLocationClient.startLocation();
                } else {
                    mLocationTv.setText(aMapLocation.getCity().trim());
                    if (mLocationClient.isStarted()) {
                        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                        mLocation.setEnabled(true);
                    }
//                    UserBean.setLat(getContext(), String.valueOf(lat));
//                    UserBean.setLng(getContext(), String.valueOf(lon));
//                    Toast.makeText(getContext(), "当前位置：" + aMapLocation.getCity().trim(), Toast.LENGTH_SHORT).show();
                }

            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                mLocation.setEnabled(true);
//                Toast.makeText(getContext(), "ErrCode:" + aMapLocation.getErrorCode() + "\n" + "errInfo:" + aMapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mLocationClient.isStarted()) {
            mLocationClient.startLocation();
        }
    }

    @OnClick({R.id.mLocation, R.id.mSort1, R.id.mSort2, R.id.mSort3, R.id.mSort4})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("s", "分类");
        switch (view.getId()) {
            case R.id.mLocation:
                mLocation.setEnabled(false);
                if (mLocationClient.isStarted()) {
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                    mLocationClient.startLocation();
                } else {
                    mLocationClient.startLocation();
                }
                break;
            case R.id.mSort1:
                bundle.putString("type", "1");
                JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                break;
            case R.id.mSort2:
                bundle.putString("type", "2");
                JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                break;
            case R.id.mSort3:
                bundle.putString("type", "3");
                JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                break;
            case R.id.mSort4:
                bundle.putString("type", "4");
                JumpUtil.newInstance().jumpRight(getContext(), SearchGoodsListActivity.class, bundle);
                break;
        }
    }


    public class MyAdapter extends RecyclerView.Adapter {
        //先定义两个ItemViewType，0代表头，1代表表格中间的部分
        private static final int ITEM_VIEW_TYPE_HEADER = 0;
        private static final int ITEM_VIEW_TYPE_ITEM = 1;
        //数据源
        private List<OnLineShopListBean.DataBean> dataList;

        //构造函数
        public MyAdapter(List<OnLineShopListBean.DataBean> dataList) {
            this.dataList = dataList;
        }

        /**
         * 判断当前position是否处于第一个
         *
         * @param position
         * @return
         */
        public boolean isHeader(int position) {
            return position == 0;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            //在onCreateViewHolder方法中，我们要根据不同的ViewType来返回不同的ViewHolder
            if (viewType == ITEM_VIEW_TYPE_HEADER) {
                //对于Header，我们应该返回填充有Header对应布局文件的ViewHolder（再次我们返回的都是一个布局文件，请根据不同的需求做相应的改动）
                return new HeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_online_shop_title, null));
            } else {
                //对于Body中的item，我们也返回所对应的ViewHolder
                return new BodyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_online_shop, null));
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
            if (isHeader(position)) {
                //大家在这里面处理头，这里只有一个TextView，大家可以根据自己的逻辑做修改
                ((HeaderViewHolder) viewHolder).getTextView().setText("最新商品");
            } else {
                //其他条目中的逻辑在此
                ((BodyViewHolder) viewHolder).getTextView().setText(dataList.get(position - 1).getName());
                RequestOptions options=new RequestOptions().placeholder(R.drawable.s_img);
                Glide.with(getContext()).load(IMGS + dataList.get(position - 1).getImage()).apply(options).into(((BodyViewHolder) viewHolder).getIv());
//                ((BodyViewHolder) viewHolder).getmPoint().setText(dataList.get(position - 1).getPoint());
                ((BodyViewHolder) viewHolder).getmMoney().setText("¥" + dataList.get(position - 1).getPrice());
                ((BodyViewHolder) viewHolder).getmDetail().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        ToastUtil.show(getContext(), "点击了" + position);
                        Bundle bundle = new Bundle();
                        bundle.putString("goods_id", String.valueOf(dataList.get(position - 1).getGoods_id()));
                        JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class, bundle);
                    }
                });
            }
        }

        /**
         * 总条目数量是数据源数量+1，因为我们有个Header
         *
         * @return
         */
        @Override
        public int getItemCount() {
            return dataList.size() + 1;
        }

        /**
         * 复用getItemViewType方法，根据位置返回相应的ViewType
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            //如果是0，就是头，否则则是其他的item
            return isHeader(position) ? ITEM_VIEW_TYPE_HEADER : ITEM_VIEW_TYPE_ITEM;
        }

        /**
         * 给头部专用的ViewHolder，大家根据需求自行修改
         */
        public class HeaderViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.mTitle);
            }

            public TextView getTextView() {
                return textView;
            }
        }

        /**
         * 给GridView中的条目用的ViewHolder，里面只有一个TextView
         */
        public class BodyViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            private ImageView iv;
            //            private TextView mPoint;
            private TextView mMoney;
            private LinearLayout mDetail;

            public BodyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.mMoney);
                iv = itemView.findViewById(R.id.mPic);
//                mPoint = itemView.findViewById(R.id.mPoint);
                mMoney = itemView.findViewById(R.id.mMoney);
                mDetail = itemView.findViewById(R.id.mDetail);
            }

            public LinearLayout getmDetail() {
                return mDetail;
            }

            public TextView getTextView() {
                return textView;
            }

            public ImageView getIv() {
                return iv;
            }

//            public TextView getmPoint() {
//                return mPoint;
//            }

            public TextView getmMoney() {
                return mMoney;
            }
        }
    }

}
