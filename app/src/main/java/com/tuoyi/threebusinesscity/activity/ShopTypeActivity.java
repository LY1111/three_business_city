package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.ShopTypeAdapter;
import com.tuoyi.threebusinesscity.bean.ShopTypeBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopTypeActivity extends AppCompatActivity {

    private static final String TAG = "商家类型";
    @BindView(R.id.mList)
    ListView mList;
    @BindView(R.id.mOk)
    Button mOk;

    private ShopTypeAdapter adapter;
    private List<ShopTypeBean.DataBean> beanList = new ArrayList<>();
    private List<String> type_ids = new ArrayList<>();
    private LinkedHashMap<Integer, Boolean> map = new LinkedHashMap <>();

    private String type_id;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_type);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        OkGo.<String>post("http://sszl.tuoee.com/api/member/get_business_commission")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.d(response.body());
//                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        ShopTypeBean bean = gson.fromJson(response.body(), ShopTypeBean.class);
                        if (bean.getCode() == 200) {
                            beanList = bean.getData();
                            Log.d(TAG, "handleMessage: " + beanList);
                            for (int i = 0; i < beanList.size(); i++) {
                                type_ids.add(String.valueOf(beanList.get(i).getId()));
                            }
                            adapter = new ShopTypeAdapter(ShopTypeActivity.this, beanList);

                            mList.setAdapter(adapter);
                        } else {
                            Toast.makeText(ShopTypeActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick(R.id.mOk)
    public void onViewClicked() {
        type_id = null;
        type = null;
        map = adapter.getMap();
        for (int position : map.keySet()) {
            if (map.get(position)) {
                if (type_id == null) {
                    type_id = type_ids.get(position);
                } else {
                    type_id = type_ids.get(position) + "," + type_id;
                }
                if (type == null){
                    type = beanList.get(position).getName();
                }else {
                    type = beanList.get(position).getName() + "," + type;
                }
            }
            Log.e(TAG, "onViewClicked: " + position );
        }
        Log.i(TAG, "onViewClicked: " + type_id);
        Log.d(TAG, "onViewClicked: " + type);
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        bundle.putString("type_id",type_id);
        JumpUtil.newInstance().finishRightTrans(this,bundle,001);
//        RxActivityTool.skipActivityAndFinish(ShopTypeActivity.this,MyBusinessActivity.class,bundle);
    }
}
