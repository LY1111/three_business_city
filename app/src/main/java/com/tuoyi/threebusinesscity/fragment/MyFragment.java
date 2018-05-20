package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MyGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.NoticeMF;
import com.tuoyi.threebusinesscity.bean.MyGridMenuBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.ImageUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.tuoyi.threebusinesscity.url.Config.S;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 */
public class MyFragment extends Fragment {
    @BindView(R.id.mainm_withdrawals_btn)
    TextView mainmWithdrawalsBtn;
    @BindView(R.id.mainm_profile_image)
    CircleImageView mainmProfileImage;
    @BindView(R.id.mainm_marqueeView)
    MarqueeView mainmMarqueeView;
    @BindView(R.id.mainm_marqueeView_tv_right)
    TextView mainmMarqueeViewTvRight;
    @BindView(R.id.mainm_grid_list)
    RecyclerView mainmGridList;
    @BindView(R.id.mainm_iv_bg)
    ImageView mainmIvbg;
    Unbinder unbinder;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userQuotient)
    TextView userQuotient;
    @BindView(R.id.userMoney)
    TextView userMoney;
    private View view;
    private LinkedList<MyGridMenuBean> headGridList;
    private MyGridMenuAdapter gridMenuadapter;
    private ArrayList<String> marqueeList;
    //    底部导航list
    private String[] menuTvList = {"商城订单", "消费记录", "推荐奖", "分享推荐", "我发展的下线用户", "我的店中店", "合作商家入口", "代理商入口", "供货商家入口"};

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
        GetUserInfo();
    }

    private void initView() {
        mainmIvbg.setImageBitmap(ImageUtil.getDrawableBitmap(getContext(), R.drawable.demo_img));
    }

    //    token获取用户信息
    private void GetUserInfo() {
        OkGo.<String>post(S + "api/AppProve/member_info")
                .tag(this)
                .params("token", UserBean.getToken(getContext()))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            String message = jsonObject.getString("message");
                            if (jsonObject.getString("code").equals("200")) {
                                JSONObject object = jsonObject.getJSONObject("data");
                                userName.setText(object.getString("username"));
                                userMoney.setText("佣金：￥" + object.getString("total_bonus"));
                                userQuotient.setText("三商豆：" + object.getString("points"));
                            } else if (jsonObject.getString("code").equals("400")) {
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.getString("code").equals("401")) {
                                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                userName.setText("未登录");
                                UserBean.removeToken(getActivity());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
            switch (i){
                case 0:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_1,menuTvList[0]));
                    break;
                case 1:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_2,menuTvList[1]));
                    break;
                case 2:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_3,menuTvList[2]));
                    break;
                case 3:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_4,menuTvList[3]));
                    break;
                case 4:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_5,menuTvList[4]));
                    break;
                case 5:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_6,menuTvList[5]));
                    break;
                case 6:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_7,menuTvList[6]));
                    break;
                case 7:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_8,menuTvList[7]));
                    break;
                case 8:
                    headGridList.add(new MyGridMenuBean(R.mipmap.p_9,menuTvList[8]));
                    break;
            }
//            headGridList.add(new MyGridMenuBean(R.mipmap.ic_launcher, menuTvList[i]));
        }

        mainmGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 3));
        gridMenuadapter = new MyGridMenuAdapter(headGridList);
        mainmGridList.setAdapter(gridMenuadapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @OnClick({R.id.mainm_withdrawals_btn, R.id.mainm_marqueeView_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainm_withdrawals_btn:
                break;
            case R.id.mainm_marqueeView_tv_right:
                break;
        }
    }
}
