package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ListDetailsAdapter;
import com.tuoyi.threebusinesscity.bean.AdressListBen;
import com.tuoyi.threebusinesscity.bean.OptionListBean;
import com.tuoyi.threebusinesscity.bean.ShoppingCartBean;
import com.tuoyi.threebusinesscity.bean.SubmitOrderBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.util.LogUtils;


/**
 * 确认订单
 */
public class Confirmation_OrderActivity extends AppCompatActivity {


    @BindView(R.id.leftBack) ImageView leftBack;
    @BindView(R.id.tab) RelativeLayout tab;
    @BindView(R.id.tubiao) ImageView tubiao;
    @BindView(R.id.tv_address_name) TextView tvAddressName;
    @BindView(R.id.tv_address_phone) TextView tvAddressPhone;
    @BindView(R.id.tv_address_addresss) TextView tvAddressAddresss;
    @BindView(R.id.ll_address_1) LinearLayout llAddress1;
    @BindView(R.id.ll_address_2) LinearLayout llAddress2;
    @BindView(R.id.iv_gogogo) ImageView ivGogogo
    ;@BindView(R.id.ll_right) RelativeLayout llRight;
    @BindView(R.id.list_details) com.tuoyi.threebusinesscity.widget.NoScrollListView listDetails;
    @BindView(R.id.et_Remarks) EditText etRemarks
    ;@BindView(R.id.tv_total) TextView tvTotal;
    @BindView(R.id.integral) TextView integral;
    @BindView(R.id.tv_sub) TextView tvSub;private ListDetailsAdapter adapter;
    private List<ShoppingCartBean.DataBean.GoodsBean> goodsBean=new ArrayList<>();
    private List<OptionListBean> optionBean=new ArrayList<>();
    private double ShowIntegral; //总积分
    private double totalPrice;     //总价
    private int  address_id;      //地址ID
    private String relCarts;      //购物车ID
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_order);
        ButterKnife.bind(this);
        goodsBean = new ArrayList<>();
        optionBean=getIntent().getParcelableArrayListExtra("optionBean");
        goodsBean = getIntent().getParcelableArrayListExtra("goodsBean");
        ShowIntegral=getIntent().getDoubleExtra("tvShowIntegral",0);
        totalPrice=getIntent().getDoubleExtra("totalPrice",0);
        Log.e("111111111111111", goodsBean.size() + "========="+optionBean.size());

        String carts = "";
        for (int i = 0; i < goodsBean.size(); i++) {

                carts = carts + goodsBean.get(i).getCart_id() + ",";

        }
        if (carts.length() > 0) {
            relCarts  = carts.substring(0, carts.length() - 1);
            Log.d("ad", "onClick: carts: " + relCarts);
        }

        getLocation();
        cOmmodityDetails();

        integral.setText(ShowIntegral+"");
        tvTotal.setText(totalPrice+"");
    }

    @OnClick({R.id.leftBack, R.id.tv_sub,R.id.ll_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.tv_sub:
                submitOrder();
                break;
            case R.id.ll_right:
                //RxActivityTool.skipActivity(Confirmation_OrderActivity.this,MyLocationActivity.class);
                Intent intent=new Intent(Confirmation_OrderActivity.this,MyLocationActivity.class);
                intent.putExtra("where","Confirmation_OrderActivity");
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1){
            if (resultCode==2){

                tvAddressName.setText(data.getStringExtra("name"));
                tvAddressPhone.setText(data.getStringExtra("phone"));
                tvAddressAddresss.setText(data.getStringExtra("address"));
                address_id=data.getIntExtra("address_id",0);
                Log.e("", "onActivityResult: "+ tvAddressName+"====="+tvAddressPhone+"===="+tvAddressAddresss+"===="+address_id);
            }
        }
    }

    //获取第一条地址
    public void getLocation() {
        OkGo.<String>post(Config.s + "api/AppProve/get_address")
                .tag(this)
                .params("token", UserBean.getToken(Confirmation_OrderActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.debug(response.body());
                        Gson gson = new Gson();
                        AdressListBen adressListBen = gson.fromJson(response.body(), AdressListBen.class);
                        if (adressListBen.getCode() == 200&&adressListBen.getData().size()>0) {
                            llAddress1.setVisibility(View.VISIBLE);
                            llAddress2.setVisibility(View.GONE);
                            address_id=adressListBen.getData().get(0).getAddress_id();
                            tvAddressName.setText(adressListBen.getData().get(0).getName());
                            tvAddressPhone.setText(adressListBen.getData().get(0).getTelephone());
                            tvAddressAddresss.setText(adressListBen.getData().get(0).getAddress());
                        }else {
                            llAddress2.setVisibility(View.VISIBLE);
                            llAddress1.setVisibility(View.GONE);
                        }
                       // Toast.makeText(Confirmation_OrderActivity.this, adressListBen.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //提交订单
    public void submitOrder(){
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/add_order")
                .tag(this)
                .params("token", UserBean.getToken(Confirmation_OrderActivity.this))
                .params("address_id", address_id+"")
                .params("carts", relCarts)
                .params("comment",etRemarks.getText().toString().trim() )
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.debug(response.body());
                        Gson gson = new Gson();
                        SubmitOrderBean submitOrderBean = gson.fromJson(response.body(), SubmitOrderBean.class);
                        if (submitOrderBean.getCode() == 200) {
                            finish();
                        }else {

                        }
                         Toast.makeText(Confirmation_OrderActivity.this, submitOrderBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //商品详情
    public void cOmmodityDetails(){
        adapter=new ListDetailsAdapter(Confirmation_OrderActivity.this,goodsBean,optionBean);
        listDetails.setAdapter(adapter);
    }
}