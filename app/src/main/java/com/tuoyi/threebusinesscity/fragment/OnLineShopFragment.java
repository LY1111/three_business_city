package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.onLineShop.GoodsListActivity;
import com.tuoyi.threebusinesscity.activity.onLineShop.SearchGoodsListActivity;
import com.tuoyi.threebusinesscity.adapter.OnLineShopMenuAdapter;
import com.tuoyi.threebusinesscity.bean.BannerBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopListBean;
import com.tuoyi.threebusinesscity.bean.OnLineShopMenuBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.KeyBoardUtils;
import com.tuoyi.threebusinesscity.util.RushBuyCountDownTimerView;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by glp on 2018/5/15.
 * 描述：
 */

public class OnLineShopFragment extends Fragment implements AMapLocationListener {

    private static final String TAG = "线上商城";
    Unbinder unbinder;
    @BindView(R.id.mLocation_tv)
    TextView mLocationTv;
    @BindView(R.id.mSearch)
    EditText mSearch;
    @BindView(R.id.mBanner)
    Banner mBanner;
    @BindView(R.id.mImg1)
    ImageView mImg1;
    @BindView(R.id.mImg2)
    ImageView mImg2;
    @BindView(R.id.mImg3)
    ImageView mImg3;
    @BindView(R.id.mHandPick1_pic)
    ImageView mHandPick1Pic;
    @BindView(R.id.mHandPick1_text)
    TextView mHandPick1Text;
    @BindView(R.id.mHandPick1_point)
    TextView mHandPick1Point;
    @BindView(R.id.timeView1)
    RushBuyCountDownTimerView timeView1;
    @BindView(R.id.mHandPick2_pic)
    ImageView mHandPick2Pic;
    @BindView(R.id.mHandPick2_text)
    TextView mHandPick2Text;
    @BindView(R.id.mHandPick2_point)
    TextView mHandPick2Point;
    @BindView(R.id.mHandPick3_pic)
    ImageView mHandPick3Pic;
    @BindView(R.id.mHandPick3_text)
    TextView mHandPick3Text;
    @BindView(R.id.mHandPick3_point)
    TextView mHandPick3Point;
    @BindView(R.id.mHandPick4_pic)
    ImageView mHandPick4Pic;
    @BindView(R.id.mHandPick4_text)
    TextView mHandPick4Text;
    @BindView(R.id.mHandPick4_point)
    TextView mHandPick4Point;
    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    @BindView(R.id.mLocation)
    LinearLayout mLocation;
    @BindView(R.id.timeView2)
    RushBuyCountDownTimerView timeView2;
    @BindView(R.id.timeView3)
    RushBuyCountDownTimerView timeView3;
    @BindView(R.id.timeView4)
    RushBuyCountDownTimerView timeView4;
    @BindView(R.id.mainf_head_grid_list)
    RecyclerView mainfHeadGridList;
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
    private List<OnLineShopListBean> beanList = new ArrayList<>();
    private OnLineShopListBean bean;
    private OnLineShopMenuAdapter menuAdapter;
    private List<OnLineShopMenuBean.DataBean> headGridList;
    private int mPosition = 0;
    private int mListType = 0;
    private String sSearch;

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
        mRecycler.setNestedScrollingEnabled(false);
        /* 设置倒计时 单位秒 */
        int sum = 6000;
        timeView1.addTime(sum);
        timeView1.start();
        timeView2.addTime(sum);
        timeView2.start();
        timeView3.addTime(sum);
        timeView3.start();
        timeView4.addTime(sum);
        timeView4.start();

        mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
//                    Toast.makeText(getContext(), "你点击了搜索键。。。。", Toast.LENGTH_SHORT).show();
                    sSearch = mSearch.getText().toString().trim();
                    if (!TextUtils.isEmpty(sSearch)) {
                        mListType = 2;
                        Bundle bundle = new Bundle();
                        bundle.putString("search",sSearch);
                        bundle.putString("s","搜索");
                        JumpUtil.newInstance().jumpRight(getContext(),SearchGoodsListActivity.class,bundle);
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
        return view;
    }

    /* 获取顶部分类 */
    private void initMenu() {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/get_category")
                .tag(this)
                .params("num","10")
                .params("page",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        OnLineShopMenuBean menuBean = gson.fromJson(response.body(),OnLineShopMenuBean.class);
                        headGridList = menuBean.getData();
                        initMenuList();
                    }
                });
    }

    /* 添加数据 */
    private void initData() {
        for (int i = 0; i < 10; i++) {
            bean = new OnLineShopListBean();
            bean.setPic(R.mipmap.ic_launcher);
            bean.setMoney("199");
            bean.setName("这是一个超级好的商品名");
            bean.setPoint("积分：123");
            beanList.add(bean);
        }
        adapter = new MyAdapter(beanList);
        mRecycler.setAdapter(adapter);
        final GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.isHeader(position) ? layoutManager.getSpanCount() : 1;
            }
        });
        mRecycler.setLayoutManager(layoutManager);
    }

    /* 顶部广告 */
    private void initMenuList() {
        mainfHeadGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 5));
        menuAdapter = new OnLineShopMenuAdapter(getContext(),headGridList);
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
                            }
                            initBanner2();
                        }
                    }
                });
    }

    /**
     * 轮播图设置
     */
    private void initBanner2() {
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
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

    @OnClick({R.id.mLocation, R.id.mSort1, R.id.mSort2, R.id.mSort3, R.id.mSort4, R.id.mImg1, R.id.mImg2, R.id.mImg3, R.id.mHandPick1, R.id.mHandPick2, R.id.mHandPick3, R.id.mHandPick4})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("s","分类");
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
                bundle.putString("type","1");
                JumpUtil.newInstance().jumpRight(getContext(),SearchGoodsListActivity.class,bundle);
                break;
            case R.id.mSort2:
                bundle.putString("type","2");
                JumpUtil.newInstance().jumpRight(getContext(),SearchGoodsListActivity.class,bundle);
                break;
            case R.id.mSort3:
                bundle.putString("type","3");
                JumpUtil.newInstance().jumpRight(getContext(),SearchGoodsListActivity.class,bundle);
                break;
            case R.id.mSort4:
                bundle.putString("type","4");
                JumpUtil.newInstance().jumpRight(getContext(),SearchGoodsListActivity.class,bundle);
                break;
            case R.id.mImg1:
                break;
            case R.id.mImg2:
                break;
            case R.id.mImg3:
                break;
            case R.id.mHandPick1:
                break;
            case R.id.mHandPick2:
                break;
            case R.id.mHandPick3:
                break;
            case R.id.mHandPick4:
                break;
        }
    }



    public class MyAdapter extends RecyclerView.Adapter {
        //先定义两个ItemViewType，0代表头，1代表表格中间的部分
        private static final int ITEM_VIEW_TYPE_HEADER = 0;
        private static final int ITEM_VIEW_TYPE_ITEM = 1;
        //数据源
        private List<OnLineShopListBean> dataList;

        //构造函数
        public MyAdapter(List<OnLineShopListBean> dataList) {
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
                ((BodyViewHolder) viewHolder).getIv().setImageResource(dataList.get(position - 1).getPic());
                ((BodyViewHolder) viewHolder).getmPoint().setText(dataList.get(position - 1).getPoint());
                ((BodyViewHolder) viewHolder).getmMoney().setText(dataList.get(position - 1).getMoney());
                ((BodyViewHolder) viewHolder).getmDetail().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.show(getContext(), "点击了" + position);
//                        JumpUtil.newInstance().jumpRight(getContext(), GoodDetailsActivity.class);
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
            private TextView mPoint;
            private TextView mMoney;
            private LinearLayout mDetail;

            public BodyViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.mMoney);
                iv = itemView.findViewById(R.id.mPic);
                mPoint = itemView.findViewById(R.id.mPoint);
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

            public TextView getmPoint() {
                return mPoint;
            }

            public TextView getmMoney() {
                return mMoney;
            }
        }
    }

}
