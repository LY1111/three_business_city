package com.tuoyi.threebusinesscity.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.BusinessjsonBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpriseCertificationActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.tv_enterpriseName)
    EditText tvEnterpriseName;
    @BindView(R.id.tv_Name)
    EditText tvName;
    @BindView(R.id.tv_phoneNum)
    EditText tvPhoneNum;
    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.tv_bankname)
    EditText tvBankname;
    @BindView(R.id.tv_cardNo)
    EditText tvCardNo;
    @BindView(R.id.et_bankNo)
    EditText et_bankNo;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_sub)
    TextView tvSub;
    @BindView(R.id.tv_Openingbankname)
    EditText tvOpeningbankname;
    @BindView(R.id.et_businessLicense)
    EditText etBusinessLicense;
    @BindView(R.id.et_organizationCode)
    EditText etOrganizationCode;
    @BindView(R.id.et_taxRegister)
    EditText etTaxRegister;
    private List<String> options1Items = new ArrayList<>();
    private List<BusinessjsonBean> options1Items1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private String province;            //省
    private String city;                //市

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        RxActivityTool.addActivity(this);
        ButterKnife.bind(this);
        initJsonData1();
    }

    @OnClick({R.id.leftBack, R.id.tv_sub, R.id.tv_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;
            case R.id.tv_sub:
                setEnterpriseMsg();
                break;
            case R.id.tv_address:
                showPickerView();
                break;
        }
    }

    //上传企业信息
    private void setEnterpriseMsg() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/setting_up_enterprise_information")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("companyName", tvEnterpriseName.getText().toString().trim())
                .params("legalName", tvName.getText().toString().trim())
                .params("legalIds", tvCardNo.getText().toString().trim())
                .params("legalPhone", tvPhoneNum.getText().toString().trim())
                .params("accountNo", tvAccount.getText().toString().trim())
                .params("parentBankName", tvBankname.getText().toString().trim())
                .params("bankName", tvOpeningbankname.getText().toString().trim())
                .params("unionBank", et_bankNo.getText().toString().trim())
                .params("province", province)
                .params("city", city)
                .params("businessLicense", etBusinessLicense.getText().toString().trim())
                .params("organizationCode", etOrganizationCode.getText().toString().trim())
                .params("taxRegister", etTaxRegister.getText().toString().trim())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean msgBean = gson.fromJson(response.body(), BaseBean.class);
                        if (msgBean.getCode() == 200) {
                            RxToast.success(msgBean.getMessage());
                            RxActivityTool.finishActivity(EnterpriseCertificationActivity.this);
                        } else {
                            RxToast.error(msgBean.getMessage());
                        }

                    }
                });
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items1.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(options2);
                province = options1Items1.get(options1).getArea_name();
                city = options1Items1.get(options1).getChild().get(options2).getArea_name();

                tv_address.setText(tx);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器*/
        pvOptions.setPicker(options1Items, options2Items);//二级选择器
        //pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData1() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = getJson(this, "Businessjson.json");//获取assets目录下的json文件数据

        ArrayList<BusinessjsonBean> jsonBean = parseData1(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items1 = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> provilist = new ArrayList<>();
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            provilist.add(jsonBean.get(i).getArea_name());

            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {//遍历该省份的所有城市
//
                CityList.add(jsonBean.get(i).getChild().get(c).getArea_name());//添加城市

            }
            /**
             * 添加省数据
             */
            options1Items.addAll(provilist);
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

        }


    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public ArrayList<BusinessjsonBean> parseData1(String result) {//Gson 解析
        ArrayList<BusinessjsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                BusinessjsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), BusinessjsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }
}
