package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MyMembersAdapter;
import com.tuoyi.threebusinesscity.bean.MyMembersBean;

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
    @BindView(R.id.my_menbers_money)
    TextView mMoney;
    @BindView(R.id.my_menbers_list)
    RecyclerView mList;
    private MyMembersAdapter myMembersAdapter;
    private List<MyMembersBean> beanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_members);
        ButterKnife.bind(this);
        initList();
    }

    /**
     * 我的会员列表
     */
    private void initList() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            beanList.add(new MyMembersBean("用户" + i));
        }
        mList.setLayoutManager(new LinearLayoutManager(this));
        myMembersAdapter = new MyMembersAdapter(beanList);
        mList.setAdapter(myMembersAdapter);
    }

    @OnClick(R.id.my_menbers_back)
    public void onViewClicked() {
    }
}
