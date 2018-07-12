package com.tuoyi.threebusinesscity.activity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.Json1Bean;
import com.tuoyi.threebusinesscity.bean.LoginBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddMyLocationActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.et_Name)
    EditText etName;
    @BindView(R.id.et_Phone)
    EditText etPhone;
    @BindView(R.id.tv_Choice)
    TextView tvChoice;
    @BindView(R.id.tv_sub)
    TextView tvSub;
    @BindView(R.id.rl_ChoiceLocation)
    RelativeLayout rlChoiceLocation;
    @BindView(R.id.et_daress)
    EditText etDaress;
    private String areaid = "0";
    private int province;            //省
    private int city;                //市
    private int county;             //县
    private List<String> options1Items = new ArrayList<>();
    private List<Json1Bean> options1Items1 = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<Json1Bean.ChildBeanX> options2Items2 = new ArrayList<>();
    private ArrayList<Json1Bean.ChildBeanX.ChildBean> options2Items3 = new ArrayList<>();

    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData1();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;
                case MSG_LOAD_FAILED:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlocation);
        ButterKnife.bind(this);
        handler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @OnClick({R.id.leftBack, R.id.rl_ChoiceLocation, R.id.tv_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.rl_ChoiceLocation:
                if (isLoaded) {
                    showPickerView();
                } else {
                    Toast.makeText(AddMyLocationActivity.this, "城市数据初始化中...", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_sub:
                OkGo.<String>post(Config.s + "api/AppProve/add_address")
                        .tag(this)
                        .params("token", UserBean.getToken(AddMyLocationActivity.this))
                        .params("name", etName.getText().toString().trim())
                        .params("telephone", etPhone.getText().toString().trim())
                        .params("province_id", province)
                        .params("city_id", city)
                        .params("country_id", county)
                        .params("address", etDaress.getText().toString().trim())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Logger.json(response.body());
                                Gson gson = new Gson();
                                LoginBean loginBean = gson.fromJson(response.body(), LoginBean.class);

                                if (loginBean.getCode() == 200) {

                                }
                                Toast.makeText(AddMyLocationActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                break;
        }
    }


    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items1.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                province=options1Items1.get(options1).getArea_id();
                city=options1Items1.get(options1).getChild().get(options2).getArea_id();
                county=options1Items1.get(options1).getChild().get(options2).getChild().get(options3).getArea_id();
                //Toast.makeText(AddMyLocationActivity.this, tx, Toast.LENGTH_SHORT).show();
                tvChoice.setText(tx);

            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
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


   /*private void initJsonData() {//解析数据

        *//**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * *//*
        String JsonData = getJson(this, "json.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        *//**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         *//*
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            *//**
             * 添加城市数据
             *//*
            options2Items.add(CityList);

          *//**
             * 添加地区数据
             *//*
            options3Items.add(Province_AreaList);
        }

        handler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }*/

    private void initJsonData1() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = getJson(this, "json.json");//获取assets目录下的json文件数据

        ArrayList<Json1Bean> jsonBean = parseData1(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items1 = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> provilist=new ArrayList<>();
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            provilist.add(jsonBean.get(i).getArea_name());

            for (int c = 0; c < jsonBean.get(i).getChild().size(); c++) {//遍历该省份的所有城市
//                String CityName = ;
                CityList.add(jsonBean.get(i).getChild().get(c).getArea_name());//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getChild().get(c).getChild() == null
                        || jsonBean.get(i).getChild().get(c).getChild().size() == 0) {
                    City_AreaList.add("");
                } else {
                    for (int j = 0; j < jsonBean.get(i).getChild().get(c).getChild().size(); j++) {
                        City_AreaList.add(jsonBean.get(i).getChild().get(c).getChild().get(j).getArea_name());
                    }

                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            options1Items.addAll(provilist);
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        handler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<Json1Bean> parseData1(String result) {//Gson 解析
        ArrayList<Json1Bean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                Json1Bean entity = gson.fromJson(data.optJSONObject(i).toString(), Json1Bean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            handler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
