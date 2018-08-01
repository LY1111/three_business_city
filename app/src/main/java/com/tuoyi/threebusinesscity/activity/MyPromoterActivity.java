package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
import com.tuoyi.threebusinesscity.adapter.MyIncomeAdapter;
import com.tuoyi.threebusinesscity.adapter.MyPromoterAdapter;
import com.tuoyi.threebusinesscity.bean.MyIncomeBean;
import com.tuoyi.threebusinesscity.bean.MyPromoterBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.bean.UserInformationBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.widget.AutoLinearLayoutManager;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的推广商家
 */
public class MyPromoterActivity extends AppCompatActivity {

    @BindView(R.id.my_promoter_back)
    ImageView mBack;
    @BindView(R.id.my_promoter_image)
    CircleImageView mImage;
    @BindView(R.id.my_promoter_title)
    TextView myPromoterTitle;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.recyclerview_list)
    RecyclerView mRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private List<MyPromoterBean.DataBean.BusinessListBean> beanList=new ArrayList<>();
    private MyPromoterAdapter mAdapter;
    private int page = 1;
    private boolean isClear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_promoter);
        ButterKnife.bind(this);

        initRecyclerView();
        initListener();
        GetUserInfo();
        okGoRecords();
    }

    private void initRecyclerView() {
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new AutoLinearLayoutManager(this));

        mAdapter = new MyPromoterAdapter(R.layout.item_my_promoter, beanList);
        mRecyclerview.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Intent intent=new Intent(NewsListActivity.this,NewsDetailsActivity.class);
//                intent.putExtra("id",mList.get(position).id);
//                startActivity(intent);
//            }
//        });
    }

    private void initListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                isClear = true;
                okGoRecords();

                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                isClear = false;
                page++;

                refreshlayout.finishLoadMore();
            }
        });
    }

    //    token获取用户信息
    private void GetUserInfo() {
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        UserInformationBean bean = gson.fromJson(response.body(), UserInformationBean.class);
                        if (bean.getCode() == 200) {
                            RequestOptions options=new RequestOptions().placeholder(R.drawable.demo_img).error(R.drawable.demo_img);
                            Glide.with(mImage).load(Config.IMGS +bean.getData().getUserpic())
                                    .apply(options)//图片加载失败后，显示的图片
                                    .into(mImage);
                        } else if (bean.getCode() == 400) {
                           RxToast.error(bean.getMessage());
                        }
                    }
                });

    }

    private void okGoRecords() {
        OkGo.<String>post(Config.s + "api/AppProve/recommending_business")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("page", page)
                .params("num", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        MyPromoterBean baseBean = gson.fromJson(response.body(), MyPromoterBean.class);
                        if (baseBean.getCode() == 200) {
                            tv_number.setText(baseBean.getData().getCount()+"");
                            if (isClear){
                                beanList.clear();
                            }
                            beanList.addAll(baseBean.getData().getBusiness_list());
                            mAdapter.notifyDataSetChanged();

                        } else {
                            RxToast.error(baseBean.getMessage());
                        }
                    }
                });
    }


    @OnClick(R.id.my_promoter_back)
    public void onViewClicked() {
        finish();
    }
}
