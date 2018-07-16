package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.MainActivity;
import com.tuoyi.threebusinesscity.activity.PutForwardActivity;
import com.tuoyi.threebusinesscity.adapter.MyGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.NoticeMF;
import com.tuoyi.threebusinesscity.bean.MyGridMenuBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.bean.UserInformationBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 */
public class MyFragment extends Fragment {

    @BindView(R.id.mainm_iv_bg)
    ImageView mainmIvBg;
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.mainm_withdrawals_btn)
    TextView mainmWithdrawalsBtn;
    @BindView(R.id.userQuotient)
    TextView userQuotient;
    @BindView(R.id.userMoney)
    TextView userMoney;
    @BindView(R.id.mainm_profile_image)
    CircleImageView mainmProfileImage;
    @BindView(R.id.mainm_marqueeView)
    MarqueeView mainmMarqueeView;
    @BindView(R.id.mainm_marqueeView_tv_right)
    TextView mainmMarqueeViewTvRight;
    @BindView(R.id.mainm_grid_list)
    RecyclerView mainmGridList;
    @BindView(R.id.btn_quit)
    Button btnQuit;
    private View view;
    Unbinder unbinder;
    private LinkedList<MyGridMenuBean> headGridList;
    private MyGridMenuAdapter gridMenuadapter;
    private ArrayList<String> marqueeList;
    private String money;
    //    底部导航list
    private String[] menuTvList = {"个人信息", "消费记录", "我的收入", "分享推荐", "我的伙伴", "我推荐的商家", "我的店铺", "合作商家入口", "代理商入口", "供货商家入口"};

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initMenuList();
        initMarqueeView();
        //GetUserInfo();
    }

    private void initView() {
        // mainmIvbg.setImageBitmap(ImageUtil.getDrawableBitmap(getContext(), R.drawable.bg_info));
        mainmIvBg.setImageResource(R.drawable.bg_info);
    }

    //    token获取用户信息
    private void GetUserInfo() {
        OkGo.<String>post(Config.s + "api/AppProve/member_info")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        UserInformationBean bean = gson.fromJson(response.body(), UserInformationBean.class);
                        if (bean.getCode() == 200) {
                            tvUserName.setText(bean.getData().getUsername());
                            userMoney.setText("我的余额：￥" + bean.getData().getTotal_bonus());
                            money = bean.getData().getTotal_bonus();
                            userQuotient.setText("积分：" + bean.getData().getPoints());
                            Glide.with(getContext()).load(Config.IMGS + bean.getData().getUserpic())
                                    .error(R.drawable.demo_img)//图片加载失败后，显示的图片
                                    .into(mainmProfileImage);
                            btnQuit.setText("退出");
                        } else if (bean.getCode() == 400) {
                            //Toast.makeText(getContext(), bean.getMessage(), Toast.LENGTH_SHORT).show();
                            tvUserName.setText("未登录");
                            UserBean.removeToken(getActivity());
                            mainmWithdrawalsBtn.setVisibility(View.GONE);
                            btnQuit.setText("登陆");
                        }
                    }
                });

    }


    /**
     * 滚动广告条设置
     */
    private void initMarqueeView() {
        marqueeList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            marqueeList.add("这是第" + i + "条滚动条。滚动吧！小傻子。。。");
        }
        //文字向上翻转
        MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        //MarqueeView设置Factory
        mainmMarqueeView.setMarqueeFactory(marqueeFactory);
        //开始翻转
        mainmMarqueeView.startFlipping();
        //设置item的监听
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
            }
        });
        //设置数据
        marqueeFactory.setData(marqueeList);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainmMarqueeView.startFlipping();
        GetUserInfo();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainmMarqueeView.stopFlipping();

    }


    /**
     * 初始化网格菜单
     */
    private void initMenuList() {
        headGridList = new LinkedList<>();
        for (int i = 0; i < menuTvList.length; i++) {
            switch (i) {
                case 0:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_1, menuTvList[0]));
                    break;
                case 1:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_2, menuTvList[1]));
                    break;
                case 2:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_3, menuTvList[2]));
                    break;
                case 3:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_4, menuTvList[3]));
                    break;
                case 4:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_5, menuTvList[4]));
                    break;
                case 5:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_6, menuTvList[5]));
                    break;
                case 6:
                    //我的店铺
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_7, menuTvList[6]));
                    break;
                case 7:
                    //合作商家
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_8, menuTvList[7]));
                    break;
                case 8:
                    //供货商
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_9, menuTvList[9]));
                    break;
                case 9:
                    //代理商
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_9, menuTvList[8]));
                    break;

            }
//            headGridList.add(new MyGridMenuBean(R.mipmap.ic_launcher, menuTvList[i]));
        }
        mainmGridList.setNestedScrollingEnabled(false);
        mainmGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 3));
        gridMenuadapter = new MyGridMenuAdapter(headGridList);
        mainmGridList.setAdapter(gridMenuadapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mainm_withdrawals_btn, R.id.btn_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainm_withdrawals_btn:
                Bundle bundle = new Bundle();
                bundle.putString("money", money);
                RxActivityTool.skipActivity(getContext(), PutForwardActivity.class, bundle);
                break;
            case R.id.btn_quit:
                UserBean.setToken(getActivity(), "");
                RxActivityTool.skipActivityAndFinish(getActivity(), MainActivity.class);
                break;
        }
    }
}
