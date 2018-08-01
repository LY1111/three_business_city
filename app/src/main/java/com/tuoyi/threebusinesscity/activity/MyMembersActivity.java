package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.utils.LogUtils;
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
import com.tuoyi.threebusinesscity.adapter.MyMembersAdapter;
import com.tuoyi.threebusinesscity.bean.MyIncomeBean;
import com.tuoyi.threebusinesscity.bean.MyMembersBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.bean.UserInformationBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的会员
 */
public class MyMembersActivity extends AppCompatActivity {

    @BindView(R.id.my_menbers_back)
    ImageView mBack;
    @BindView(R.id.my_menbers_menber)
    TextView msMenber;
    @BindView(R.id.my_menbers_image)
    CircleImageView mImage;
    @BindView(R.id.my_menbers_list)
    RecyclerView mList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private MyMembersAdapter myMembersAdapter;
    private List<MyMembersBean.DataBean.UpperMemberBean> beanList = new ArrayList<>();

    private int page = 1;
    private boolean isClear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_members);
        ButterKnife.bind(this);

        initRecyclerView();
        initListener();
        okGoRecords();
        GetUserInfo();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initRecyclerView() {
        mList.setNestedScrollingEnabled(false);
        mList.setLayoutManager(new LinearLayoutManager(this));

        myMembersAdapter = new MyMembersAdapter(beanList);
        mList.setAdapter(myMembersAdapter);
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
                okGoRecords();
                refreshlayout.finishLoadMore();
            }
        });
    }

    @OnClick(R.id.my_menbers_back)
    public void onViewClicked() {
        finish();
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
        OkGo.<String>post(Config.s + "api/AppProve/membership")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("page", page)
                .params("num", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        MyMembersBean baseBean = gson.fromJson(response.body(), MyMembersBean.class);
                        if (baseBean.getCode() == 200) {
                            msMenber.setText(baseBean.getData().getCount() + "");
                            if (isClear) {
                                beanList.clear();
                            }
                            beanList.addAll(baseBean.getData().getUpper_member());
                            myMembersAdapter.notifyDataSetChanged();

                        } else {
                            RxToast.error(baseBean.getMessage());
                        }
                    }
                });
    }
}
