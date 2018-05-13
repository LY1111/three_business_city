package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.RxIntentTool;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/* 我是商家 */
public class MyBusinessActivity extends AppCompatActivity {

    @BindView(R.id.mPhone)
    EditText mPhone;
    @BindView(R.id.mCode)
    EditText mCode;
    @BindView(R.id.mGetCode)
    TextView mGetCode;
    @BindView(R.id.mPasswordA)
    EditText mPasswordA;
    @BindView(R.id.mPasswordB)
    EditText mPasswordB;
    @BindView(R.id.mInvite)
    EditText mInvite;
    @BindView(R.id.mType)
    TextView mType;
    @BindView(R.id.mSort)
    TextView mSort;
    @BindView(R.id.mCardNum)
    EditText mCardNum;
    @BindView(R.id.mCardName)
    EditText mCardName;
    @BindView(R.id.mShopName)
    EditText mShopName;
    @BindView(R.id.mShopAddress)
    TextView mShopAddress;

    private String type,type_id;
    private String sort,sort_id;
    private String lon,lat,address,sheng,shi,xian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.mGetCode, R.id.mType, R.id.mSort, R.id.mShopAddress, R.id.mBtn, R.id.mLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mGetCode:
                //获取验证码
                break;
            case R.id.mType:
                //商家类型
//                RxActivityTool.skipActivityForResult(this,ShopTypeActivity.class,001);
                JumpUtil.newInstance().jumpRight(this,ShopTypeActivity.class,001);
                break;
            case R.id.mSort:
                //商家分类
                JumpUtil.newInstance().jumpRight(this,ShopSortActivity.class,002);
                break;
            case R.id.mShopAddress:
                //商家地址
                JumpUtil.newInstance().jumpRight(this,MapAddressActivity.class,003);
                break;
            case R.id.mBtn:
                //注册
                register();
                break;
            case R.id.mLogin:
                //去往登录
                JumpUtil.newInstance().jumpRight(MyBusinessActivity.this,CompLoginActivity.class);
                break;
        }
    }

    private void register() {
        OkGo.<String>post("http://sszl.tuoee.com/api/member/business_register")
                .params("commission_id",type_id)
                .params("classification",sort)
                .params("pid",mInvite.getText().toString())
                .params("abc",mCardNum.getText().toString())
                .params("username",mCardName.getText().toString())
                .params("password",mPasswordA.getText().toString())
                .params("telephone",mPhone.getText().toString())
                .params("shop_name",mShopName.getText().toString())
                .params("address",address)
                .params("longitude",lon)
                .params("latitude",lat)
                .params("province",sheng)
                .params("city",shi)
                .params("district",xian)
                .params("is_classification",1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        /**
                         {
                         "status":1,
                         "code":200,
                         "message":"新增成功",
                         "data":{
                         "token":"79OocwCY7QNLW2niDZu8ljILAgSefISlBk6Gr5CrGrCTHXpSc3H5sgs2g7gmOHSv1hiz2uhlMZqYUCrCUDVO0msTBjc1EJmWr82Cm1qW2OysYBMcQSB9h7Howgkmxapv6M56UGMbqkApqWBRz8QVciH75jjtqbZivjEIE9rN"
                         }
                         }
                         */

                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            if (jsonObject.getInt("code") == 200){
                                Toast.makeText(MyBusinessActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                                String data = jsonObject.getString("data");
                                JSONObject object = new JSONObject(data);
                                UserBean.setToken(MyBusinessActivity.this,object.getString("token"));
                            }else {
                                Toast.makeText(MyBusinessActivity.this,jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            switch (requestCode) {
                case 001:
                    type = bundle.getString("type");
                    type_id = bundle.getString("type_id");
                    mType.setText(type);
                    break;
                case 002:
                    sort = bundle.getString("sort");
                    sort_id = bundle.getString("sort_id");
                    mSort.setText(sort);
                    break;
                case 003:
                    lon = bundle.getString("经度");
                    lat = bundle.getString("纬度");
                    sheng = bundle.getString("省");
                    shi = bundle.getString("市");
                    xian = bundle.getString("县");
                    address = bundle.getString("address");
                    mShopAddress.setText(address);
                    break;
            }
        }
    }
}
