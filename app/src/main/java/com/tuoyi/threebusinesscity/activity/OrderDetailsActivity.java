package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.OrderDetailBean;
import com.tuoyi.threebusinesscity.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.util.LogUtils;


/* 订单详情 */
public class OrderDetailsActivity extends AppCompatActivity {

    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.tab)
    RelativeLayout tab;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);

        getOrderDetail(id);
    }

    private void getOrderDetail(int id) {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/get_order_one")
                .tag(this)
                .params("token", UserBean.getToken(OrderDetailsActivity.this))
                .params("id", id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.debug(response.body());
                        Gson gson = new Gson();
                        OrderDetailBean orderDetailBean = gson.fromJson(response.body(), OrderDetailBean.class);
                        if (orderDetailBean.getCode() == 200) {

                        } else {

                        }
                        Toast.makeText(OrderDetailsActivity.this, orderDetailBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick({R.id.leftBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;

        }
    }
}
