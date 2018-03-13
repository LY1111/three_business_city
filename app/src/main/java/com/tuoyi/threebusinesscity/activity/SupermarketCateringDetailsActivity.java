package com.tuoyi.threebusinesscity.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.LeftMarketDetailsAdapter;
import com.tuoyi.threebusinesscity.adapter.RightMarketDetailsAdapter;
import com.tuoyi.threebusinesscity.bean.LeftMarketDetailsBean;
import com.tuoyi.threebusinesscity.bean.RightMarketDetailsBean;
import com.tuoyi.threebusinesscity.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 超市餐饮类详情
 */
public class SupermarketCateringDetailsActivity extends AppCompatActivity implements RightMarketDetailsAdapter.OnAddItemClickListener,
        RightMarketDetailsAdapter.OnItemClickListener, RightMarketDetailsAdapter.OnRemoveItemClickListener {

    @BindView(R.id.market_back)
    ImageView marketBack;
    @BindView(R.id.market_search)
    ImageView marketSearch;
    @BindView(R.id.market_iv_bg)
    ImageView marketIvBg;
    @BindView(R.id.market_tv_name)
    TextView marketTvName;
    @BindView(R.id.market_tv_ID)
    TextView marketTvID;
    @BindView(R.id.market_tv_star)
    RatingBar marketTvStar;
    @BindView(R.id.market_tv_money)
    TextView marketTvMoney;
    @BindView(R.id.market_profile_image)
    CircleImageView marketProfileImage;
    @BindView(R.id.market_tv_location)
    TextView marketTvLocation;
    @BindView(R.id.market_tv_phone)
    TextView marketTvPhone;
    @BindView(R.id.market_callPhone_btn)
    TextView marketCallPhoneBtn;
    @BindView(R.id.market_tv_content)
    TextView marketTvContent;
    @BindView(R.id.market_sao_btn)
    TextView marketSaoBtn;
    @BindView(R.id.market_list_left)
    RecyclerView leftMarketList;
    @BindView(R.id.market_list_right)
    RecyclerView rightMarketList;
    @BindView(R.id.market_tv_shopNum)
    TextView marketTvShopNum;
    @BindView(R.id.market_tv_total)
    TextView marketTvTotal;
    private double mMoney = 0.00;
    private double mNum0 = 0.00;
    private double mTotal = 0.00;
    private double mTotal0 = 0.00;
    private RightMarketDetailsAdapter rightAdapter;
    private LeftMarketDetailsAdapter leftAdapter;
    private List<LeftMarketDetailsBean> leftBeen = new ArrayList<>();
    private List<RightMarketDetailsBean> rightBeen = new ArrayList<>();
    private int leftPotion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermarket_catering_details);
        ButterKnife.bind(this);
        initLeftList();
        initList();
        initListener();
        rightAdapter.setOnItemClickListener(this);
        rightAdapter.setOnAddClickListener(this);
        rightAdapter.setOnRemoveClickListener(this);
    }

    private void initLeftList() {
        leftBeen = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            leftBeen.add(new LeftMarketDetailsBean("分类" + i));
        }
        leftMarketList.setLayoutManager(new LinearLayoutManager(this));
        leftAdapter = new LeftMarketDetailsAdapter(leftBeen);
        leftMarketList.setAdapter(leftAdapter);
    }

    private void initListener() {
        leftMarketList.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                leftAdapter.setSelectPos(position);
                leftPotion = position;
                initRightList();
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }
        }));
    }

    private void initRightList() {
        rightBeen = new ArrayList<>();
        for (int i = 0; i < 2 + leftPotion; i++) {
            rightBeen.add(new RightMarketDetailsBean(0, "10"));
        }
        for (int i = 0; i < rightBeen.size(); i++) {
            RightMarketDetailsBean rightBean = rightBeen.get(i);
            mMoney = Double.valueOf(rightBean.getMoney());
            mNum0 = rightBean.getNum();
            mTotal0 = mMoney * mNum0;
            mTotal = mTotal + mTotal0;
        }
        rightAdapter.notifyAdapter(rightBeen, false);
        marketTvTotal.setText("共计：" + mTotal + " 元");
    }

    private void initList() {
        for (int i = 0; i < 2; i++) {
            rightBeen.add(new RightMarketDetailsBean(0, "10"));
        }
        rightMarketList.setLayoutManager(new LinearLayoutManager(SupermarketCateringDetailsActivity.this));
        rightAdapter = new RightMarketDetailsAdapter(SupermarketCateringDetailsActivity.this);
        rightMarketList.setAdapter(rightAdapter);
        rightAdapter.getMyLiveList();
        rightAdapter.notifyAdapter(rightBeen, false);
    }

    @OnClick({R.id.market_back, R.id.market_search, R.id.market_callPhone_btn, R.id.market_sao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.market_back:
                break;
            case R.id.market_search:
                break;
            case R.id.market_callPhone_btn:
                break;
            case R.id.market_sao_btn:
                break;
        }
    }

    @Override
    public void onItemClickListener(int pos, List<RightMarketDetailsBean> mRightBean) {
        Log.e("asd", "onItemClickListener: ");
    }

    @Override
    public void onAddItemClickListener(int pos, int count, TextView view, List<RightMarketDetailsBean> mRightBean) {
        RightMarketDetailsBean mBean = mRightBean.get(pos);
        int mCount = mBean.getNum();//根据下标获取实体类选中状态
        if (mCount < count) {
            mMoney = Double.valueOf(mBean.getMoney());
            mNum0 = mBean.getNum();
            mTotal0 = mMoney * mNum0;
            mTotal = mTotal - mTotal0;
            mTotal0 = mMoney * count;
            mTotal = mTotal + mTotal0;
        }
        mBean.setNum(count);
        marketTvTotal.setText("共计：" + mTotal + " 元");
        marketTvShopNum.setText("您已选择" + count + "件商品");
        rightAdapter.notifyDataSetChanged();
        Log.e("asd", "onAddItemClickListener: ");
    }

    @Override
    public void onRemoveItemClickListener(int pos, int count, TextView view, List<RightMarketDetailsBean> mRightBean) {
        RightMarketDetailsBean mBean = mRightBean.get(pos);
        int mCount = mBean.getNum();//根据下标获取实体类选中状态
        if (mCount != 0) {
            mMoney = Double.valueOf(mBean.getMoney());
            mNum0 = mBean.getNum();
            mTotal0 = mMoney * mNum0;
            mTotal = mTotal - mTotal0;
            mTotal0 = mMoney * count;
            mTotal = mTotal + mTotal0;
            mBean.setNum(count);
            marketTvTotal.setText("共计：" + mTotal + " 元");
            marketTvShopNum.setText("您已选择" + count + "件商品");
            rightAdapter.notifyDataSetChanged();
        } else {
            mBean.setNum(mCount);
            marketTvShopNum.setText("您已选择" + count + "件商品");
            rightAdapter.notifyDataSetChanged();
        }

        Log.e("asd", "onRemoveItemClickListener: ");
    }
}
