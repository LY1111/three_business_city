package com.tuoyi.threebusinesscity.activity.business;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.BusinesAdminAdapter;
import com.tuoyi.threebusinesscity.bean.BusineseeAdminBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.vondear.rxtools.RxBarTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessAdministrationActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.img_add)
    ImageView imgAdd;
   /* @BindView(R.id.commodity_list)
    SwipeMenuRecyclerView commodityList;*/
    @BindView(R.id.commodity_list)
    RecyclerView commodityList;
    @BindView(R.id.commodity_XRefreshView)
    XRefreshView mRefreshView;
    private List<BusineseeAdminBean.DataBean> beanList=new ArrayList<>();
    private int page = 1;
    private BusinesAdminAdapter adminAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessadministration);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
        initRefreshView();
        mRefreshView.startRefresh();
    }

    @OnClick({R.id.leftBack, R.id.img_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.img_add:
                RxActivityTool.skipActivity(BusinessAdministrationActivity.this,BusinessAddGoodsActivity.class);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    //获取店铺商品列表
    private void initData() {
        OkGo.<String>post(Config.s+"api/AppBusinessProve/get_business_goods")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("商品数据", "onSuccess11111: " + response.body());
                        Gson gson = new Gson();
                        final BusineseeAdminBean bean = gson.fromJson(response.body(), BusineseeAdminBean.class);
                        if (bean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (page == 1){
                                beanList = bean.getData();
                            }else {
                                beanList.addAll(bean.getData());
                            }
                            mRefreshView.stopRefresh();
                            if (beanList != null) {
                                commodityList.setLayoutManager(new LinearLayoutManager(BusinessAdministrationActivity.this));
                                adminAdapter = new BusinesAdminAdapter(BusinessAdministrationActivity.this,beanList);
                                commodityList.setAdapter(adminAdapter);

                                adminAdapter.setDeleteInterface(new BusinesAdminAdapter.DeleteInterface() {
                                    @Override
                                    public void detele(int position) {
                                        beanList.remove(position);
                                        adminAdapter.notifyDataSetChanged();
                                        OkGo.<String>post(Config.s+"api/AppBusinessProve/business_goods_del")
                                                .tag(this)
                                                .params("business_token", UserBean.getBusineToken(BusinessAdministrationActivity.this))
                                                .params("goods_id", beanList.get(position).getGoods_id())
                                                .execute(new StringCallback() {
                                                    @Override
                                                    public void onSuccess(Response<String> response) {
                                                        Log.d("删除商品数据", "onSuccess11111: " + response.body());
                                                        Gson gson = new Gson();
                                                        BusineseeAdminBean bean = gson.fromJson(response.body(), BusineseeAdminBean.class);
                                                        if (bean.getCode() == 200) {
                                                            ToastUtil.show(BusinessAdministrationActivity.this, "删除成功");
                                                        } else {
                                                            ToastUtil.show(BusinessAdministrationActivity.this, bean.getMessage());
                                                        }

                                                    }
                                                });
                                    }

                                    @Override
                                    public void modify(int position) {
                                        Intent intent=new Intent(BusinessAdministrationActivity.this, ModifyGoodsActivity.class);
                                        Bundle bundle=new Bundle();
                                        bundle.putSerializable("bean", (Serializable) beanList.get(position));
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            }else {
                                ToastUtil.show(BusinessAdministrationActivity.this,bean.getMessage());
                            }
                        } else {
                            mRefreshView.setLoadComplete(true);
                            ToastUtil.show(BusinessAdministrationActivity.this, bean.getMessage());
                        }

                    }
                });
    }

    private void initRefreshView() {
        mRefreshView.setPinnedTime(500);//设置刷新后头布局停留的时间
        mRefreshView.setMoveForHorizontal(true);//设置不获取横向滑动的焦点手势
        mRefreshView.setPullLoadEnable(false);//是否支持上拉加载
        mRefreshView.setPullRefreshEnable(true);//是否支持下拉刷新
        mRefreshView.setAutoLoadMore(false);//滑动到底部是否自动加载更多
        mRefreshView.setPinnedContent(true);//刷新时，不让里面的列表上下滑动
        mRefreshView.setCustomHeaderView(new XRefreshViewHeader(this, null));//设置头布局
        mRefreshView.setCustomFooterView(new XRefreshViewFooter(this, null));//设置脚布局虽然没啥用
        mRefreshView.enableReleaseToLoadMore(true);
        mRefreshView.enableRecyclerViewPullUp(true);
        mRefreshView.setSilenceLoadMore(false);//是否静默加载
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                page = 1;
                initData();

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //page++;
                initData();
            }
        });
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode){
            case 1:
                mRefreshView.startRefresh();
                break;
            case 2:
                mRefreshView.startRefresh();
                break;
            default:
                break;
        }
    }*/
}
