package com.tuoyi.threebusinesscity.fragment.OrderForm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.OrederAdapter;
import com.tuoyi.threebusinesscity.base.BaseLazyFragment;
import com.tuoyi.threebusinesscity.bean.GetWholeOrderBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WholeFragment extends BaseLazyFragment {

    @BindView(R.id.mainf_head_grid_list)
    RecyclerView mainfHeadGridList;
    @BindView(R.id.mainf_XRefreshView)
    XRefreshView mRefreshView;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;
    Unbinder unbinder;
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_whole, container, false);
        unbinder = ButterKnife.bind(this, view);
        type = getArguments().getInt("type");

        initRefreshView();
        mRefreshView.startRefresh();
        return view;
    }

    /* 获取订单列表 */
    private void initData(final int t) {
        OkGo.<String>post("http://sszl.tuoee.com/api/AppProve/get_order")
                .tag(this)
                .params("token", UserBean.getToken(getParentFragment().getActivity()))
                .params("num","10")
                .params("page",page)
                .params("order_status_id",t+"")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("订单数据", "onSuccess11111: " + response.body());
                        Gson gson = new Gson();
                        GetWholeOrderBean bean = gson.fromJson(response.body(), GetWholeOrderBean.class);
                        if (bean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (page == 1){
                                beanList = bean.getData();
                            }else {
                                beanList.addAll(bean.getData());
                            }
                            mRefreshView.stopRefresh();
                            if (beanList != null) {
                                mainfHeadGridList.setLayoutManager(new LinearLayoutManager(getActivity()));
                                adapter = new OrederAdapter(beanList);
                                mainfHeadGridList.setAdapter(adapter);
                            }else {
                                ToastUtil.show(getActivity(),bean.getMessage());
                            }
                        } else {
                            mRefreshView.setLoadComplete(true);
                            ToastUtil.show(getActivity(), bean.getMessage());
                        }

                    }
                });
    }

    private void initRefreshView() {
        mRefreshView.setPinnedTime(500);//设置刷新后头布局停留的时间
        mRefreshView.setMoveForHorizontal(true);//设置不获取横向滑动的焦点手势
        mRefreshView.setPullLoadEnable(true);//是否支持上拉加载
        mRefreshView.setPullRefreshEnable(true);//是否支持下拉刷新
        mRefreshView.setAutoLoadMore(false);//滑动到底部是否自动加载更多
        mRefreshView.setPinnedContent(true);//刷新时，不让里面的列表上下滑动
        mRefreshView.setCustomHeaderView(new XRefreshViewHeader(getContext(), null));//设置头布局
        mRefreshView.setCustomFooterView(new XRefreshViewFooter(getContext(), null));//设置脚布局虽然没啥用
        mRefreshView.enableReleaseToLoadMore(true);
        mRefreshView.enableRecyclerViewPullUp(true);
        mRefreshView.setSilenceLoadMore(false);//是否静默加载
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                page = 1;
                initData(type);
                /*switch (mListType) {
                    case 1:

                        break;
                    case 2:

                        break;
                    default:

                        break;
                }*/
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
               /* switch (mListType) {
                    case 1:

                        break;
                    case 2:

                        break;
                    default:

                        break;
                }*/
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
