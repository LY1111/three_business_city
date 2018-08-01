package com.tuoyi.threebusinesscity.fragment.OrderForm;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.PaymentActivity;
import com.tuoyi.threebusinesscity.adapter.OrederAdapter;
import com.tuoyi.threebusinesscity.base.BaseLazyFragment;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.GetWholeOrderBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WholeFragment extends BaseLazyFragment {

    @BindView(R.id.mainf_head_grid_list)
    RecyclerView mainfHeadGridList;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout refreshlayout;
    private boolean isClear = true;
    private int page = 1;
    private int type;
    private List<GetWholeOrderBean.DataBean> beanList = new ArrayList<>();
    private OrederAdapter adapter;

    public static WholeFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        WholeFragment pageFragment = new WholeFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_whole);
        type = getArguments().getInt("type");
        initRefreshView();
        initView();
        initData(type);
    }


    private void initView() {
        mainfHeadGridList.setNestedScrollingEnabled(false);
        mainfHeadGridList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new OrederAdapter(beanList);
        mainfHeadGridList.setAdapter(adapter);
        //取消订单
        adapter.setmOnCancleClickListener(new OrederAdapter.OnItemCancleClickListener() {
            @Override
            public void onItemClick(View view, final int positino) {
                OkGo.<String>post(Config.s + "api/AppProve/cancellation_of_order")
                        .tag(this)
                        .params("order_num_alias", beanList.get(positino).getOrder().getOrder_num_alias())
                        .params("token", UserBean.getToken(mActivity))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                BaseBean bean = gson.fromJson(response.body(), BaseBean.class);
                                if (bean.getCode() == 200) {
                                    beanList.remove(positino);
                                    adapter.notifyDataSetChanged();
                                    RxToast.success(bean.getMessage());
                                } else {
                                    RxToast.error(bean.getMessage());
                                }
                            }
                        });
//
            }
        });
        //确认支付
        adapter.setmOnConfirmClickListener(new OrederAdapter.OnItemConfirmClickListener() {
            @Override
            public void onItemClick(View view, int positino) {
                OkGo.<String>post(Config.s + "api/AppProve/confirmation_of_receipt")
                        .tag(this)
                        .params("order_num_alias", beanList.get(positino).getOrder().getOrder_num_alias())
                        .params("token", UserBean.getToken(mActivity))
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                BaseBean bean = gson.fromJson(response.body(), BaseBean.class);
                                if (bean.getCode() == 200) {
                                    RxToast.success(bean.getMessage());
                                } else {
                                    RxToast.error(bean.getMessage());
                                }

                            }
                        });
            }
        });

        //立即支付
        adapter.setOnItemPayClickListener(new OrederAdapter.OnItemPayClickListener() {
            @Override
            public void onItemClick(View view, int positino) {
                if ("0.00".equals(beanList.get(positino).getOrder().getTotal())||"0".equals(beanList.get(positino).getOrder().getTotal())){
                    myDialog(beanList.get(positino).getOrder().getOrder_num_alias(),positino);
                }else {
                    Bundle bundle = new Bundle();
                    bundle.putString("where", "OrederAdapter");
                    bundle.putString("money", beanList.get(positino).getOrder().getTotal());
                    bundle.putString("orderno", beanList.get(positino).getOrder().getOrder_num_alias());
                    RxActivityTool.skipActivity(mActivity, PaymentActivity.class, bundle);
                }
            }
        });
    }


    //积分兑换弹窗
    private void myDialog(final String orderNo, final int pos) {
        final Dialog bottomDialog = new Dialog(getContext());
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.pay_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
//        params.width = getResources().getDisplayMetrics().widthPixels - CommonUtils.dp2px(getContext(), 80f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(false);
        bottomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bottomDialog.show();

        final TextView tv_sure = bottomDialog.findViewById(R.id.tv_sure);
        final TextView tv_cancle = bottomDialog.findViewById(R.id.tv_cancle);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });

        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initOkGoList(orderNo,pos);
                bottomDialog.dismiss();
            }
        });
    }

    //积分兑换商品
    private void initOkGoList(String order_num_alias, final int pos) {
        OkGo.<String>post(Config.s + "api/AppProve/exchange")
                .tag(this)
                .params("order_num_alias", order_num_alias)
                .params("token", UserBean.getToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean bean = gson.fromJson(response.body(), BaseBean.class);
                        if (bean.getCode() == 200) {
                            initData(type);
                            RxToast.success(bean.getMessage());
                        } else {
                            RxToast.error(bean.getMessage());
                        }
                    }
                });
    }


    /* 获取订单列表 */
    private void initData(final int t) {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/get_order")
                .tag(this)
                .params("token", UserBean.getToken(getParentFragment().getActivity()))
                .params("num", "10")
                .params("page", page)
                .params("order_status_id", t + "")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("订单数据", "onSuccess11111: " + response.body());
                        Gson gson = new Gson();
                        GetWholeOrderBean bean = gson.fromJson(response.body(), GetWholeOrderBean.class);
                        if (bean.getCode() == 200) {
                            if (isClear){
                                beanList.clear();
                            }
                            beanList.addAll(bean.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            RxToast.error(bean.getMessage());
                        }

                    }
                });
    }

    private void initRefreshView() {
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                isClear = true;
                //网络请求
                initData(type);
                refreshLayout.finishRefresh();
            }
        });

        refreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isClear = false;
                page++;
                //网络请求
                initData(type);
                refreshLayout.finishLoadMore();
            }
        });
    }

}
