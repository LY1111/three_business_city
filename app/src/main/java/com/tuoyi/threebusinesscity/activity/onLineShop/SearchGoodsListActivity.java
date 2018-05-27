package com.tuoyi.threebusinesscity.activity.onLineShop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.SearchGoodsListAdapter;
import com.tuoyi.threebusinesscity.bean.SearchGoodsListBean;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGoodsListActivity extends AppCompatActivity {

    private static final String TAG = "商品列表";
    @BindView(R.id.mSearch)
    EditText mSearch;
    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    @BindView(R.id.xRefreshView)
    XRefreshView mRefreshView;
    private String search;
    private String s;
    private String t;
    private String sort_id;
    private int page = 1 ;
    private int mListType;
    private SearchGoodsListAdapter adapter;
    private List<SearchGoodsListBean.DataBean> beanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_list);
        ButterKnife.bind(this);
        s = getIntent().getExtras().getString("s");
        if (s.equals("搜索")) {
            mListType = 1;
            search = getIntent().getExtras().getString("search");
            mSearch.setText(search);
            mSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_SEARCH) {
                        if (!search.isEmpty()) {
                            initSearch(search);
                        } else {
                            ToastUtil.show(SearchGoodsListActivity.this, "请输入后搜索商品");
                        }
                        return true;
                    }
                    return false;
                }
            });
            initSearch(search);
        }else if (s.equals("分类")){
            mListType = 2;
            t = getIntent().getExtras().getString("type");
            initData(t);
        }else if (s.equals("10分类")){
            mListType = 3;
            sort_id = getIntent().getExtras().getString("sort_id");
            initData(sort_id);
        }
        initRefreshView();
    }

    /*  */
    private void initList(String sort_id) {

    }

    /* 获取商品列表 */
    private void initData(String t) {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/goods_type")
                .tag(this)
                .params("num","10")
                .params("page",page)
                .params("type",t)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess: " + response.body());
                        Gson gson = new Gson();
                        SearchGoodsListBean bean = gson.fromJson(response.body(), SearchGoodsListBean.class);
                        if (bean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (page == 1){
                                beanList = bean.getData();
                            }else {
                                beanList.addAll(bean.getData());
                            }
                            mRefreshView.stopRefresh();
                            if (beanList != null) {
                                mRecycler.setLayoutManager(new LinearLayoutManager(SearchGoodsListActivity.this));
                                adapter = new SearchGoodsListAdapter(SearchGoodsListActivity.this, beanList);
                                mRecycler.setAdapter(adapter);
                            }else {
                                ToastUtil.show(SearchGoodsListActivity.this,bean.getMessage());
                            }
                        } else {
                            mRefreshView.setLoadComplete(true);
                            ToastUtil.show(SearchGoodsListActivity.this, bean.getMessage());
                        }

                    }
                });
    }

    /* 搜索商品 */
    private void initSearch(String sSearch) {
        OkGo.<String>post("http://sszl.tuoee.com/api/App/search_goods")
                .tag(this)
                .params("name", sSearch)
                .params("page", 1)
                .params("num", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d(TAG, "onSuccess搜索商品: " + response.body());
                        com.orhanobut.logger.Logger.d(response.body());
                        Gson gson = new Gson();
                        SearchGoodsListBean bean = gson.fromJson(response.body(), SearchGoodsListBean.class);
                        if (bean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                           if (page == 1){
                               beanList = bean.getData();
                           }else {
                               beanList.addAll(bean.getData());
                           }
                           mRefreshView.stopRefresh();

                        } else {
                            mRefreshView.setLoadComplete(true);
                            ToastUtil.show(SearchGoodsListActivity.this, bean.getMessage());
                        }
                        if (beanList != null) {
                            mRecycler.setLayoutManager(new LinearLayoutManager(SearchGoodsListActivity.this));
                            adapter = new SearchGoodsListAdapter(SearchGoodsListActivity.this, beanList);
                            mRecycler.setAdapter(adapter);
                        }else {
                            ToastUtil.show(SearchGoodsListActivity.this,bean.getMessage());
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
        mRefreshView.setCustomHeaderView(new XRefreshViewHeader(this, null));//设置头布局
        mRefreshView.setCustomFooterView(new XRefreshViewFooter(this, null));//设置脚布局虽然没啥用
        mRefreshView.enableReleaseToLoadMore(true);
        mRefreshView.enableRecyclerViewPullUp(true);
        mRefreshView.setSilenceLoadMore(false);//是否静默加载
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                page = 1;
                Log.d(TAG, "onRefresh类型: " + mListType);
                switch (mListType) {
                    case 1:
                        initSearch(search);
                        break;
                    case 2:
                        Log.d(TAG, "initData第几个类型: " + t);
                        initData(t);
                        break;
                    case 3:
                        initData(sort_id);
                        break;
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                page++;
                Log.d(TAG, "onRefresh类型: " + mListType);
                switch (mListType) {
                    case 1:
                        initSearch(search);
                        break;
                    case 2:
                        initData(s);
                        break;
                    case 3:
                        initData(sort_id);
                        break;
                }
            }
        });
    }
}
