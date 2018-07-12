package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.AddressListAdapter;
import com.tuoyi.threebusinesscity.bean.AdressListBen;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.util.LogUtils;

public class MyLocationActivity extends AppCompatActivity {
    @BindView(R.id.list_location)
    ListView list_location;
    @BindView(R.id.btn_add)
    Button btn_add;

    private AddressListAdapter mAddressAdapter;
    private String where;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mylocation);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
        where=getIntent().getStringExtra("where");
        getLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getLocation();
    }

    public void getLocation() {
        OkGo.<String>post(Config.s + "api/AppProve/get_address")
                .tag(this)
                .params("token", UserBean.getToken(MyLocationActivity.this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogUtils.debug(response.body());
                        Gson gson = new Gson();
                        final AdressListBen adressListBen = gson.fromJson(response.body(), AdressListBen.class);
                        if (adressListBen.getCode() == 200) {
                            mAddressAdapter = new AddressListAdapter(MyLocationActivity.this, adressListBen.getData());
                            list_location.setAdapter(mAddressAdapter);
                            list_location.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    if ("Confirmation_OrderActivity".equals(where)){
                                        Intent intent=new Intent();
                                        intent.putExtra("address_id",adressListBen.getData().get(position).getAddress_id());
                                        intent.putExtra("name",adressListBen.getData().get(position).getName());
                                        intent.putExtra("phone",adressListBen.getData().get(position).getTelephone());
                                        intent.putExtra("address",adressListBen.getData().get(position).getAddress());
                                        setResult(2,intent);
                                        finish();
                                    }
                                }
                            });
                            //默认地址
                           /* mAddressAdapter.setCheckClickListener(new AddressListAdapter.CheckInterface() {
                                @Override
                                public void check(int position) {
                                    ToastUtil.show(getApplication(), "点击了第" + position + "项的默认地址");
                                    if ("Confirmation_OrderActivity".equals(where)){
                                        Intent intent=new Intent();
                                        intent.putExtra("address_id",adressListBen.getData().get(position).getAddress_id());
                                        intent.putExtra("name",adressListBen.getData().get(position).getName());
                                        intent.putExtra("phone",adressListBen.getData().get(position).getTelephone());
                                        intent.putExtra("address",adressListBen.getData().get(position).getAddress());
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }
                                }
                            });*/

                           //暂时没有删除地址接口
                           /*//删除地址
                            mAddressAdapter.setDeleteClickListener(new AddressListAdapter.DeleteInterface() {
                                @Override
                                public void delete(int position) {
                                    //ToastUtil.show(getApplication(), "点击了第" + position + "项的删除");
                                    adressListBen.getData().remove(position);
                                    mAddressAdapter.notifyDataSetChanged();
                                }
                            });*/

                            //编辑地址
                            mAddressAdapter.setEditClickListener(new AddressListAdapter.EditInterface() {
                                @Override
                                public void edit(int position) {
                                    //ToastUtil.show(getApplication(), "点击了第" + position + "项的编辑");
                                }
                            });

                        }
                        //Toast.makeText(MyLocationActivity.this, adressListBen.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @OnClick({R.id.btn_add,R.id.leftBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                JumpUtil.newInstance().jumpRight(this, AddMyLocationActivity.class);
                break;
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;
        }
    }
}
