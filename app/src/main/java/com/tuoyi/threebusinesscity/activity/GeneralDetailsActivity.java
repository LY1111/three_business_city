package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.GeneralDetailsAdapter;
import com.tuoyi.threebusinesscity.bean.GeneralDetailsBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * 商家详情
 */
public class GeneralDetailsActivity extends AppCompatActivity {

    @BindView(R.id.general_title)
    TextView mTitle;
    @BindView(R.id.general_iv_bg)
    ImageView mIvBg;
    @BindView(R.id.general_profile_image)
    CircleImageView mProfileImage;
    @BindView(R.id.general_callPhone_btn)
    TextView mCallPhoneBtn;
    @BindView(R.id.general_sao_btn)
    TextView mSaoBtn;
    @BindView(R.id.general_RecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.general_turnover)
    TextView mTurnover;
    @BindView(R.id.general_address)
    TextView mAddress;
    @BindView(R.id.general_distance)
    TextView mDistance;
    @BindView(R.id.general_phone_tv)
    TextView mPhoneTv;
    @BindView(R.id.general_activity)
    TextView mActivity;
    private List<GeneralDetailsBean.DataBean.GoodsBean> list = new ArrayList<>();
    private GeneralDetailsAdapter adapter;
    private String mPhone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_details);
        ButterKnife.bind(this);
        initList();
        initOkGoList();

    }

    private void initOkGoList() {
        OkGo.<String>post(Config.s + "api/app/get_business_one")
                .tag(this)
                .params("id", UserBean.getPosID(this))
                .params("lng", UserBean.getLng(this))
                .params("lat", UserBean.getLat(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        GeneralDetailsBean bean = gson.fromJson(response.body(), GeneralDetailsBean.class);
                        if (bean.getCode() == 200) {
                            GeneralDetailsBean.DataBean.BusinessBean business = bean.getData().getBusiness();
                            list = bean.getData().getGoods();
                            mTitle.setText(business.getShop_name());
                            mAddress.setText("地址：" + business.getAddress());
                            mDistance.setText(business.getDistance() + "m");
                            mPhone = business.getTelephone();
                            mPhoneTv.setText("电话：" + mPhone);
                            mTurnover.setText("￥交易额：" + business.getTotal_sales()+"");
                            mActivity.setText("促销活动：会员到店消费奖励" + business.getTelephone() + "%三商豆");
//                            mIvBg.setImageBitmap(ImageUtil.getURLBitmap(GeneralDetailsActivity.this, IMGS +business.getImage()));
                            Glide.with(GeneralDetailsActivity.this).load(IMGS + business.getImage()).into(mProfileImage);
                            initList();
                        } else {
                            Toast.makeText(GeneralDetailsActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initList() {
        adapter = new GeneralDetailsAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick({R.id.general_callPhone_btn, R.id.general_sao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.general_callPhone_btn:
                if (!TextUtils.isEmpty(mPhone)) {
                    initCallPhone(mPhone);
                }
                break;
            case R.id.general_sao_btn:
                RxActivityTool.skipActivity(this,PaymentActivity.class);
                break;
        }
    }

    /**
     * 拨打电话
     */
    private void initCallPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phone));
        this.startActivity(intent);
    }
}
